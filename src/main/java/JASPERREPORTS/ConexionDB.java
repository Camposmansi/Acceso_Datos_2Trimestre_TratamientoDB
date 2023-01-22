package JASPERREPORTS;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static Connection conn;
    static Connection conexion;

    public static Connection conConnection() {
        try {
            String conector = "com.mysql.cj.jdbc.Driver";
            String jdbc = "jdbc";
            String db = "mysql";
            String rutaDB = "localhost/actividad30";
            String usuarioDB = "root";
            String passwordDB = "";

            Class.forName(conector);
            //conexion = DriverManager.getConnection(jdbc + ":" + db + "://" + rutaDB, usuarioDB, passwordDB);

            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/actividad30", "root", "");

            System.out.println("Conexion con MySQL establecida\n");
        }catch (SQLException e){
            System.out.println("Error al ejecutar sentencia SQL");
        }catch (ClassNotFoundException e){
            System.out.println("Error de Driver.");
        }

        return null;
    }

    public Connection conexion(){
        try {
            String conector = "com.mysql.cj.jdbc.Driver";
            String jdbc = "jdbc";
            String db = "mysql";
            String servidorDB = "localhost" + "/";
            String rutaDB = servidorDB + "Actividad30";
            String usuarioDB = "root";
            String passwordDB = "";

            Class.forName(conector);
            conexion = DriverManager.getConnection(jdbc + ":" + db + "://" + rutaDB + "?allowMultiQueries=true", usuarioDB, passwordDB);

            System.out.println("Conexion con MySQL establecida\n");
            //JOptionPane.showMessageDialog(null, "Se ha conectado a la DB", "Conexion", JOptionPane.INFORMATION_MESSAGE);
        }catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error en Base de datos\n\n" + e.getMessage(), "Conexion", JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    }

}
