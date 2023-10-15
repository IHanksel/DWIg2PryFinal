package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Alumno;
import util.MySQLConexion;

public class AlumnoDAO {

    public void guardar(Alumno alumno) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO alumno (Codigo, Nombres, Apellidos, DNI, Direccion, Telefono, Nombre_carrera_profesional) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, alumno.getCodigo());
                statement.setString(2, alumno.getNombres());
                statement.setString(3, alumno.getApellidos());
                statement.setString(4, alumno.getDni());
                statement.setString(5, alumno.getDireccion());
                statement.setString(6, alumno.getTelefono());
                statement.setString(7, alumno.getCarrera());
                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
    }

    public List<Alumno> filtrarPorNombreAlumno(String consulta) {
        List<Alumno> listaAlumnos = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT Codigo, Nombres, Apellidos, DNI, Direccion, Telefono, Nombre_carrera_profesional FROM alumno WHERE Nombres LIKE ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, consulta + "%"); // Usamos la variable "consulta"

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setCodigo(rs.getString(1));
                alumno.setNombres(rs.getString(2));
                alumno.setApellidos(rs.getString(3));
                alumno.setDni(rs.getString(4));
                alumno.setDireccion(rs.getString(5));
                alumno.setTelefono(rs.getString(6));
                alumno.setCarrera(rs.getString(7));

                listaAlumnos.add(alumno);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

        return listaAlumnos;
    }

    public void eliminarRegistroAlumno(String codigo) {
        Connection cn = MySQLConexion.getConexion();
        String sql = "delete from alumno where Codigo = ?";
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, codigo);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //listado de alumnos
    public List<Alumno> listado() {
        Connection cn = MySQLConexion.getConexion();
        String sql = "SELECT Codigo, Nombres, Apellidos, DNI, Direccion, Telefono, Nombre_carrera_profesional FROM alumno";
        List<Alumno> lista = new ArrayList();
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setCodigo(rs.getString(1));
                alumno.setNombres(rs.getString(2));
                alumno.setApellidos(rs.getString(3));
                alumno.setDni(rs.getString(4));
                alumno.setDireccion(rs.getString(5));
                alumno.setTelefono(rs.getString(6));
                alumno.setCarrera(rs.getString(7));
                lista.add(alumno);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // Método para contar alumnos por carrera
    public Map<String, Integer> contarAlumnosPorCarrera() {
        Connection con = MySQLConexion.getConexion();
        Map<String, Integer> carreraAlumnoCount = new HashMap<>();

        try {
            String sql = "SELECT Nombre_carrera_profesional, COUNT(*) FROM alumno GROUP BY Nombre_carrera_profesional";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String carrera = resultSet.getString(1);
                int cantidad = resultSet.getInt(2);
                carreraAlumnoCount.put(carrera, cantidad);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }

        return carreraAlumnoCount;
    }

    // Método para modificar datos de alumno registrado
    public void modificarRegistroAlumno(Alumno alumno) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement(
                    "UPDATE alumno SET Nombres=?, Apellidos=?, DNI=?, Direccion=?, Telefono=?, Nombre_carrera_profesional=? WHERE Codigo=?")) {

                statement.setString(1, alumno.getNombres());
                statement.setString(2, alumno.getApellidos());
                statement.setString(3, alumno.getDni());
                statement.setString(4, alumno.getDireccion());
                statement.setString(5, alumno.getTelefono());
                statement.setString(6, alumno.getCarrera());
                statement.setString(7, alumno.getCodigo());
                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
    }

}
