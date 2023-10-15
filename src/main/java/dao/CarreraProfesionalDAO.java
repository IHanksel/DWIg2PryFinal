package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.CarreraProfesional;
import util.MySQLConexion;

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
}
