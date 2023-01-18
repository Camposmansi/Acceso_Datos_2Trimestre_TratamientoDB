package JASPERREPORTS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static Connection conn;
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
}
