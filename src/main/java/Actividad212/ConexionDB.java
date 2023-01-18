package Actividad212;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    Connection conexion;

    public Connection conexion(){
        try {
            String conector = "com.mysql.cj.jdbc.Driver";
            String jdbc = "jdbc";
            String db = "mysql";
            String servidorDB = "localhost" + "/";
            String rutaDB = servidorDB + "poo-consultorio";
            String usuarioDB = "root";
            String passwordDB = "";

            Class.forName(conector);
            conexion = DriverManager.getConnection(jdbc + ":" + db + "://" + rutaDB + "?allowMultiQueries=true", usuarioDB, passwordDB);

            System.out.println("Conexion con MySQL establecida\n");
            //JOptionPane.showMessageDialog(null, "Se ha conectado a la DB", "Conexion", JOptionPane.INFORMATION_MESSAGE);
        }catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error en Base de datos\n\n" + e, "Conexion", JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    }
}
