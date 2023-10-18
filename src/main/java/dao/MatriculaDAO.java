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

    public void guardar(Matricula matricula) {
        Connection con = MySQLConexion.getConexion();
        try {
            try ( PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO matricula (id, Codigo_Alumno, Semestre, Nombre_Asignatura, Turno, Fecha_de_matricula) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, matricula.getId());
                statement.setString(2, matricula.getCodigoAlumno());
                statement.setInt(3, matricula.getSemestre());
                statement.setString(4, matricula.getNombreAsignatura());
                statement.setString(5, matricula.getTurno());
                statement.setDate(6, java.sql.Date.valueOf(matricula.getFechaMatricula()));
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Matricula> ListMatri() {
        Connection cn = MySQLConexion.getConexion();
        String sql = "SELECT A.Codigo,Nombres,Apellidos,C.Nombre_de_carrera,id,Semestre,Nombre_asignatura,Turno,fecha_de_matricula FROM alumno A JOIN matricula B ON A.Codigo=B.Codigo_alumno\n"
                + "LEFT JOIN carrera_profesional C ON A.Nombre_carrera_profesional=C.Codigo;";

        List<Matricula> lista = new ArrayList();
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setCodigo(rs.getString(1));
                matricula.setNombres(rs.getString(2));
                matricula.setApellidos(rs.getString(3));
                matricula.setNombreAsignatura(rs.getString(4));
                matricula.setId(rs.getInt(5));
                matricula.setSemestre(rs.getInt(6));
                matricula.setCarrera(rs.getString(7));
                matricula.setTurno(rs.getString(8));

                // Convertir la fecha de String a LocalDate
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


}
