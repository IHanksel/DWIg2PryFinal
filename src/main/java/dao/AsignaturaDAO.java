/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
}
