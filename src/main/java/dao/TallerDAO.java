/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;
import beans.*;
import util.MySQLConexion;

/**
 *
 * @author Samanta
 */
public class TallerDAO {


    public List<Taller> LisTaller() {
        List<Taller> lista = new ArrayList();
        String sql = "select id, nombre, duracion_mes, horario, costo from taller";
        try {
            PreparedStatement st = MySQLConexion.getConexion().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Taller a = new Taller(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
                lista.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void graba(Taller a) {
        String sql = "insert into taller (nombre, duracion_mes, horario, costo) values (?, ?, ?, ?)";
        try {
            PreparedStatement st = MySQLConexion.getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getNombre());
            st.setString(2, a.getDuracion());
            st.setString(3, a.getHorario());
            st.setDouble(4, a.getMonto());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el taller, ninguna fila afectada.");
            }

            // Recuperar el ID generado automáticamente
            try ( ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    a.setId(generatedKeys.getString(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado automáticamente.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
