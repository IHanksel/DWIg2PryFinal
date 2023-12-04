/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;
import beans.*;
import util.MySQLConexion;

public class CertificacionDAO {

    public List<Certificacion> ListCertificacion() {
        List<Certificacion> lista = new ArrayList();
        String sql = "SELECT id, nombre, empresa, costo, certificado, duracion_mes FROM curso_extracurricular";
        try (PreparedStatement st = MySQLConexion.getConexion().prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Certificacion c = new Certificacion(
                    rs.getString("id"), 
                    rs.getString("nombre"), 
                    rs.getString("empresa"), 
                    rs.getDouble("costo"), 
                    rs.getString("certificado"), 
                    rs.getInt("duracion_mes")
                );
                lista.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void graba(Certificacion c) {
        String sql = "INSERT INTO curso_extracurricular (nombre, empresa, costo, certificado, duracion_mes) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = MySQLConexion.getConexion();
             PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, c.getNombre());
            st.setString(2, c.getEmpresa());
            st.setDouble(3, c.getCosto());
            st.setString(4, c.getCertificado());
            st.setInt(5, c.getDuracion());
            

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar la certificación, ninguna fila afectada.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    c.setId(generatedKeys.getString(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado automáticamente.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
