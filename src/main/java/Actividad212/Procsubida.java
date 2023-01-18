package Actividad212;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class Procsubida {
    static Connection conexion = null;


    public static void main(String[] args) {
        try {
            ConexionDB msql = new ConexionDB();
            conexion = msql.conexion();

            String numeroDept = args[0];
            String subida = args[1];

            String sql = "{call subir_salario(?,?)}";

            CallableStatement cs = conexion.prepareCall(sql);
            cs.setInt(1, Integer.parseInt(numeroDept));
            cs.setFloat(2, Float.parseFloat(subida));

            cs.executeUpdate();

            System.out.println("Se ha subido el salario " + args[1] + "€");

            cs.close();
            conexion.close();
        } catch (SQLException e){e.getMessage();}
    }


}
