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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelo.Matricula;
import util.MySQLConexion;

/**
 *
 * @author abdel
 */
public class MatriculaDAO {

    private final Connection conexion;

    public MatriculaDAO() {
        this.conexion = MySQLConexion.getConexion();
    }

    public void guardar(Matricula matricula) {
        try {
            String sql = "CALL sp_insertar_matricula(?, ?, ?, ?, ?)";

            try ( CallableStatement statement = conexion.prepareCall(sql)) {
                statement.setString(1, matricula.getCodigoAlumno());
                statement.setInt(2, matricula.getSemestre());
                statement.setString(3, matricula.getNombreAsignatura());
                statement.setString(4, matricula.getTurno());
                statement.setDate(5, java.sql.Date.valueOf(matricula.getFechaMatricula()));
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Matricula> ListMatri() {
        List<Matricula> lista = new ArrayList();
        String sql = "SELECT A.Codigo, Nombres, Apellidos, C.Nombre_de_carrera, id, Semestre, Nombre_asignatura, Turno, fecha_de_matricula FROM alumno A JOIN matricula B ON A.Codigo = B.Codigo_alumno\n"
                + "LEFT JOIN carrera_profesional C ON A.Nombre_carrera_profesional = C.Codigo;";

        try ( PreparedStatement st = conexion.prepareStatement(sql);  ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setCodigo(rs.getString(1));
                matricula.setNombres(rs.getString(2));
                matricula.setApellidos(rs.getString(3));
                matricula.setCarrera(rs.getString(4));
                matricula.setId(rs.getInt(5));
                matricula.setSemestre(rs.getInt(6));
                matricula.setNombreAsignatura(rs.getString(7));
                matricula.setTurno(rs.getString(8));

                String fechaMatriculaDB = rs.getString(9);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaMatriculaLocalDate = LocalDate.parse(fechaMatriculaDB, formatter);
                matricula.setFechaMatricula(fechaMatriculaLocalDate);

                lista.add(matricula);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void modificarMatricula(Matricula matricula) {
        try {
            String sql = "CALL sp_modificar_matricula(?, ?, ?, ?, ?, ?)";

            try ( CallableStatement statement = conexion.prepareCall(sql)) {
                statement.setInt(1, matricula.getId());
                statement.setString(2, matricula.getCodigoAlumno());
                statement.setInt(3, matricula.getSemestre());
                statement.setString(4, matricula.getNombreAsignatura());
                statement.setString(5, matricula.getTurno());
                statement.setDate(6, java.sql.Date.valueOf(matricula.getFechaMatricula()));

                statement.execute();
            }
        } catch (SQLException ex) {
            System.out.println("Error al modificar matrícula: " + ex.getMessage());
        }
    }

    public void eliminarMatricula(int idMatricula) {
        try {
            String sql = "CALL sp_eliminar_matricula(?)";

            try ( CallableStatement statement = conexion.prepareCall(sql)) {
                statement.setInt(1, idMatricula);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Matricula consulta(int idMatricula) {
        Connection cn = MySQLConexion.getConexion();
        String sql = "SELECT id, Codigo_alumno, Semestre, Nombre_asignatura, Turno, Fecha_de_matricula FROM matricula WHERE id = ?";
        Matricula matricula = null;

        try {
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, idMatricula);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                matricula = new Matricula();
                matricula.setId(rs.getInt("id"));
                matricula.setCodigoAlumno(rs.getString("Codigo_alumno"));
                matricula.setSemestre(rs.getInt("Semestre"));
                matricula.setNombreAsignatura(rs.getString("Nombre_asignatura"));
                matricula.setTurno(rs.getString("Turno"));
                matricula.setFechaMatricula(rs.getDate("Fecha_de_matricula").toLocalDate());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return matricula;
    }

}
