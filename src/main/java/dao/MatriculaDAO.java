package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Matricula;
import util.MySQLConexion;

public class MatriculaDAO {

    public void guardar(Matricula matricula) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement(
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

}
