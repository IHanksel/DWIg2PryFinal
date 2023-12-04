/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Asignatura;
import util.MySQLConexion;

/**
 *
 * @author abdel
 */
public class AsignaturaDAO {

    public List<Asignatura> obtenerAsignaturas() {
        List<Asignatura> asignaturas = new ArrayList<>();
        Connection con = MySQLConexion.getConexion();

        try {
            String sql = "SELECT Codigo, Nombre_carrera_profesional, Nombre_asignatura, Creditos FROM asignatura";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setCodigo(resultSet.getString("Codigo"));
                asignatura.setCarrera(resultSet.getString("Nombre_carrera_profesional"));
                asignatura.setNombre(resultSet.getString("Nombre_asignatura"));
                asignatura.setCreditos(resultSet.getInt("Creditos"));
                asignaturas.add(asignatura);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asignaturas;
    }

    public void insertar(Asignatura asignatura) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (CallableStatement statement = con.prepareCall(
                    "{CALL sp_insertar_asignatura(?, ?, ?, ?)}")) {

                statement.setString(1, asignatura.getCodigo());
                statement.setString(2, asignatura.getCarrera());
                statement.setString(3, asignatura.getNombre());
                statement.setInt(4, asignatura.getCreditos());

                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarAsignatura(String codigoAsignatura) {
        Connection cn = MySQLConexion.getConexion();
        try {
            // Llamada al procedimiento almacenado
            String storedProcedureCall = "{CALL sp_eliminar_asignatura_por_ID(?)}";
            CallableStatement cs = cn.prepareCall(storedProcedureCall);
            cs.setString(1, codigoAsignatura);
            cs.execute();
            System.out.println("Procedimiento almacenado ejecutado con éxito");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar la conexión
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Consulta de una Asignatura por su código:
    public Asignatura consulta(String codigo) {
        Connection cn = MySQLConexion.getConexion();
        String sql = "SELECT Codigo, Nombre_carrera_profesional, Nombre_asignatura, Creditos FROM asignatura WHERE Codigo = ?";
        Asignatura asignatura = null;
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, codigo);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setCodigo(rs.getString(1));
                asignatura.setCarrera(rs.getString(2));
                asignatura.setNombre(rs.getString(3));
                asignatura.setCreditos(rs.getInt(4));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return asignatura;
    }

    public void modificarAsignatura(Asignatura asignatura) {
        Connection con = null;
        try {
            con = MySQLConexion.getConexion();
            con.setAutoCommit(false);

            String spModificarAsignatura = "{CALL sp_modificar_asignatura(?, ?, ?, ?)}";
            try (CallableStatement cs = con.prepareCall(spModificarAsignatura)) {
                cs.setString(1, asignatura.getCodigo());
                cs.setString(2, asignatura.getCarrera());
                cs.setString(3, asignatura.getNombre());
                cs.setInt(4, asignatura.getCreditos());

                cs.executeUpdate();
            }

            con.commit();
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException commitEx) {
                    commitEx.printStackTrace();
                }
            }
        }
    }

}
