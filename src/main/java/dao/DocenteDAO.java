package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Docente;
import util.MySQLConexion;

public class DocenteDAO {

    public List<Docente> filtrarPorNombreDocente(String consulta) {
        Connection con = MySQLConexion.getConexion();
        List<Docente> listaDocentes = new ArrayList<>();
        try {
            try (PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM docente WHERE Nombres LIKE ?")) {
                statement.setString(1, consulta + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Docente docente = new Docente(
                                resultSet.getString("Codigo"),
                                resultSet.getString("Nombres"),
                                resultSet.getString("Apellidos"),
                                resultSet.getString("DNI"),
                                resultSet.getString("Especialidad"),
                                resultSet.getString("Correo")
                        );
                        listaDocentes.add(docente);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
        return listaDocentes;
    }

    public void guardar(Docente docente) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO docente (Codigo, Nombres, Apellidos, DNI, Especialidad, Correo) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, docente.getCodigo());
                statement.setString(2, docente.getNombres());
                statement.setString(3, docente.getApellidos());
                statement.setString(4, docente.getDni());
                statement.setString(5, docente.getEspecialidad());
                statement.setString(6, docente.getCorreo());
                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
    }

    public void actualizar(Docente docente) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement(
                    "UPDATE docente SET Nombres=?, Apellidos=?, DNI=?, Especialidad=?, Correo=? WHERE Codigo=?")) {

                statement.setString(1, docente.getNombres());
                statement.setString(2, docente.getApellidos());
                statement.setString(3, docente.getDni());
                statement.setString(4, docente.getEspecialidad());
                statement.setString(5, docente.getCorreo());
                statement.setString(6, docente.getCodigo());
                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
    }

    public void eliminarRegistroDocente(String codigo) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement("DELETE FROM docente WHERE Codigo=?")) {
                statement.setString(1, codigo);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
    }

    public Docente obtener(String codigo) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement("SELECT * FROM docente WHERE Codigo=?")) {
                statement.setString(1, codigo);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Docente(
                                resultSet.getString("Codigo"),
                                resultSet.getString("Nombres"),
                                resultSet.getString("Apellidos"),
                                resultSet.getString("DNI"),
                                resultSet.getString("Especialidad"),
                                resultSet.getString("Correo")
                        );
                    } else {
                        return null;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
    }

    public List<Docente> obtenerTodos() {
        Connection con = MySQLConexion.getConexion();
        List<Docente> docentes = new ArrayList<>();
        try {
            try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM docente")) {

                while (resultSet.next()) {
                    Docente docente = new Docente(
                            resultSet.getString("Codigo"),
                            resultSet.getString("Nombres"),
                            resultSet.getString("Apellidos"),
                            resultSet.getString("DNI"),
                            resultSet.getString("Especialidad"),
                            resultSet.getString("Correo")
                    );
                    docentes.add(docente);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error durante la operación en la base de datos: " + e.getMessage(), e);
        } finally {
        }
        return docentes;
    }

    public void modificarRegistroDocente(Docente docente) {
        Connection con = MySQLConexion.getConexion();
        try {
            try (PreparedStatement statement = con.prepareStatement(
                    "UPDATE docente SET Nombres=?, Apellidos=?, DNI=?, Especialidad=?, Correo=? WHERE Codigo=?")) {

                statement.setString(1, docente.getNombres());
                statement.setString(2, docente.getApellidos());
                statement.setString(3, docente.getDni());
                statement.setString(4, docente.getEspecialidad());
                statement.setString(5, docente.getCorreo());
                statement.setString(6, docente.getCodigo());
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
