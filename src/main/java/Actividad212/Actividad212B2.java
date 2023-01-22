package Actividad212;

import java.sql.*;

public class Actividad212B2 {
    static Connection conexion = null;

    public static void main(String[] args) throws SQLException {
        /**Llamamos a la conexion**/
        ConexionDB msql = new ConexionDB();
        conexion = msql.conexion();

        // select:
        String texto;
        String texto2;
        int numeroDept = Integer.parseInt(args[0]);
        System.out.println("MOSTRAR DATOS: \n");
        System.out.println("=============================================================================== \n");
        String sql1 = "select * from departamentos where dept_no = ?";
        PreparedStatement ps1 = conexion.prepareStatement(sql1);
        ps1.setInt(1, numeroDept);
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()) {
            System.out.println("Departamento: " +
                    rs1.getInt("dept_no") + ",  " +
                    rs1.getString("dnombre") + ",  " +
                    rs1.getString("loc"));
        }

        /** Llamamos al procedimiento en OUT **/
        String sql = "{call mostrarInfo(?,?,?)}";

        CallableStatement cs = conexion.prepareCall(sql);
        cs.setInt(1, numeroDept);
        cs.registerOutParameter(2, Types.INTEGER);
        cs.registerOutParameter(3, Types.FLOAT);
        cs.execute();

        texto = cs.getString(2);
        texto2 = cs.getString(3);

        System.out.println("Empleados: " + texto + ", " + "Salaerio Medio: " + texto2);

        System.out.println("\n===============================================================================");

        cs.close();
        conexion.close();
    }
}
