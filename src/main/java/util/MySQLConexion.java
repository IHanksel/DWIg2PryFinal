package util;

import java.sql.*;

public class MySQLConexion {

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/bd_universidad";
            String usr = "root";
            String psw = "";
            con = DriverManager.getConnection(url, usr, psw);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException ex) {
            System.out.println("No hay Driver");
        } catch (SQLException ex) {
            System.out.println("Error con la Base de Datos");
        }
        return con;
    }
}
