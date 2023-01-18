package Actividad212;

import java.sql.*;

public class Actividad212B {
    static Connection conexion = null;

    public static void main(String[] args) {
        try {
            /**Llamamos a la conexion**/
            ConexionDB msql = new ConexionDB();
            conexion = msql.conexion();

            // select:
            String numeroDept = args[0];
            System.out.println("MOSTRAR DATOS: ");
            System.out.println("===============================================================================");

            /** Realizamos la consulta a departamentos**/
            String sql1 = "select * from departamentos where dept_no = ?";
            PreparedStatement ps1 = conexion.prepareStatement(sql1);
            ps1.setInt(1, Integer.parseInt(numeroDept));
            ResultSet rs1 = ps1.executeQuery();

            /** Mostramos los datos que queremos del departamento **/
            while (rs1.next()) {
                System.out.println("Departamento: " + rs1.getInt("dept_no") + ",  " + rs1.getString("dnombre") + ",  " + rs1.getString("loc"));
            }

            /** Llamamos al procedimiento **/
            String sql = "{ call mostrar_info (?) }";
            CallableStatement cs = conexion.prepareCall(sql);
            cs.setInt(1, Integer.parseInt(numeroDept));
            ResultSet rs = cs.executeQuery();

            /** Mostramos los datos que queremos **/
            while (rs.next()) {
                System.out.println("Numero de Empleados: " + rs.getString("empleados") + ",   " + "Salario Medio: " + rs.getFloat("salario"));
            }

            System.out.println("===============================================================================");

            cs.close();
            conexion.close();
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}