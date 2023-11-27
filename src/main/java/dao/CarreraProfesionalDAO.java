/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.CarreraProfesional;
import modelo.Matricula;
import util.MySQLConexion;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author abdel
 */
public class CarreraProfesionalDAO {

    public List<CarreraProfesional> obtenerCarreras() {
        List<CarreraProfesional> carreras = new ArrayList<>();
        Connection con = MySQLConexion.getConexion();

        try {
            String sql = "SELECT Codigo, Nombre_de_carrera FROM carrera_profesional";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CarreraProfesional carrera = new CarreraProfesional();
                carrera.setCodigo(resultSet.getString("Codigo"));
                carrera.setNombre(resultSet.getString("Nombre_de_carrera"));
                carreras.add(carrera);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carreras;
    }

    public List<CarreraProfesional> listado() {
        List<CarreraProfesional> carreras = new ArrayList<>();
        Connection con = MySQLConexion.getConexion();

        try {
            String sql = "SELECT Codigo, Nombre_de_carrera, Modalidad FROM carrera_profesional";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CarreraProfesional carrera = new CarreraProfesional();
                carrera.setCodigo(resultSet.getString("Codigo"));
                carrera.setNombre(resultSet.getString("Nombre_de_carrera"));
                carrera.setModalidad(resultSet.getString("Modalidad"));
                carreras.add(carrera);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carreras;
    }

    public void guardar(CarreraProfesional carreraProfesional) {
        Connection con = MySQLConexion.getConexion();
        try {
            try ( PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO carrera_profesional (Codigo, Nombre_de_carrera, Modalidad) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, carreraProfesional.getCodigo());
                statement.setString(2, carreraProfesional.getNombre());
                statement.setString(3, carreraProfesional.getModalidad());
                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarCarreraProfesional(String codigoCarreraProfesional) {
        Connection cn = MySQLConexion.getConexion();
        try {

            String deleteRelatedAsignaturasSQL = "DELETE FROM asignatura WHERE Nombre_carrera_profesional = ?";
            PreparedStatement deleteRelatedAsignaturasStatement = cn.prepareStatement(deleteRelatedAsignaturasSQL);
            deleteRelatedAsignaturasStatement.setString(1, codigoCarreraProfesional);
            deleteRelatedAsignaturasStatement.executeUpdate();

            String deleteCarreraProfesionalSQL = "DELETE FROM carrera_profesional WHERE Codigo = ?";
            PreparedStatement deleteCarreraProfesionalStatement = cn.prepareStatement(deleteCarreraProfesionalSQL);
            deleteCarreraProfesionalStatement.setString(1, codigoCarreraProfesional);
            deleteCarreraProfesionalStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CarreraProfesional consulta(String codigo) {
        Connection cn = MySQLConexion.getConexion();
        String sql = "SELECT Codigo, Nombre_de_carrera, Modalidad FROM carrera_profesional WHERE Codigo = ?";
        CarreraProfesional carreraProfesional = null;
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, codigo);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                carreraProfesional = new CarreraProfesional();
                carreraProfesional.setCodigo(rs.getString(1));
                carreraProfesional.setNombre(rs.getString(2));
                carreraProfesional.setModalidad(rs.getString(3));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return carreraProfesional;
    }

    public void modificarCarreraProfesional(CarreraProfesional carreraProfesional) {
        Connection cn = null;
        try {
            cn = MySQLConexion.getConexion();
            cn.setAutoCommit(false);

            String updateCarreraProfesionalSQL = "UPDATE carrera_profesional SET Codigo = ?, Nombre_de_carrera = ?, Modalidad = ? WHERE Codigo = ?";
            PreparedStatement updateCarreraProfesionalStatement = cn.prepareStatement(updateCarreraProfesionalSQL);
            updateCarreraProfesionalStatement.setString(1, carreraProfesional.getCodigo());
            updateCarreraProfesionalStatement.setString(2, carreraProfesional.getNombre());
            updateCarreraProfesionalStatement.setString(3, carreraProfesional.getModalidad());
            updateCarreraProfesionalStatement.setString(4, carreraProfesional.getCodigo());

            updateCarreraProfesionalStatement.executeUpdate();

            cn.commit();
        } catch (Exception ex) {

            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {

            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                } catch (SQLException commitEx) {
                    commitEx.printStackTrace();
                }
            }
        }
    }

    public List<CarreraProfesional> SeleccionarCarreraPorModalidad(String modalidad) {
        List<CarreraProfesional> lis = new ArrayList<>();
        Connection cn = MySQLConexion.getConexion();
        try {
            
            String sql = "SELECT Codigo, Nombre_de_carrera FROM carrera_profesional WHERE Modalidad = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, modalidad);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                CarreraProfesional carreraProfesional = new CarreraProfesional();
                carreraProfesional.setCodigo(rs.getString(1));
                carreraProfesional.setNombre(rs.getString(2));
                lis.add(carreraProfesional);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lis;
    }

    public List<CarreraProfesional> SeleccionarModalidad() {
        List<CarreraProfesional> lis = new ArrayList();
        Set<String> modalidadesUnicas = new HashSet<>();

        Connection cn = MySQLConexion.getConexion();
        try {
            String sql = "SELECT DISTINCT Modalidad, Codigo FROM carrera_profesional";
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String modalidad = rs.getString(1);
                String codigo = rs.getString(2);

                
                if (modalidadesUnicas.add(modalidad)) {
                    CarreraProfesional carreraProfesional = new CarreraProfesional();
                    carreraProfesional.setModalidad(modalidad);
                    carreraProfesional.setCodigo(codigo);
                    lis.add(carreraProfesional);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lis;
    }

}
