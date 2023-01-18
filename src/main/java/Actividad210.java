import java.sql.*;

public class Actividad210 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/actividad30?allowMultiQueries=true", "root", "");

        /** Oficios **/
        // analista
        // director
        // empleado
        // presidente
        // vendedor

        /** Departamentos **/
        // contabilidad
        // investigacion
        // produccion
        // ventas

        String oficio = args[0];
        String nombreDepartamento = args[1];

        System.out.println("\nConsulta del departamento " + args[1] + " y del oficio " + args[0] + ":");
        String sqltotal = "select apellido,salario from empleados join departamentos on empleados.dept_no = departamentos.dept_no where empleados.oficio = ? and departamentos.dnombre = ?";
        PreparedStatement ps = conexion.prepareStatement(sqltotal);
        ps.setString(1, oficio);
        ps.setString(2, nombreDepartamento);

        ResultSet rs = ps.executeQuery();

        int contador = 0;

        while (rs.next()) {
            contador++;
            System.out.println("Apellido " + contador + ": " + rs.getString("apellido") + "\t|\tSalario " + contador + ": " + rs.getFloat("salario") + "€");
        }


    }

}

