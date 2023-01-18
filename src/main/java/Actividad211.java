import java.sql.*;
import java.text.DecimalFormat;

public class Actividad211 {
    public static Connection conexion;
    public static int codigoDept;
    public static ResultSet rs;

    public static void main(String[] args) throws ClassNotFoundException {

        /** Oficios **/         /** Departamentos **/
        // analista                 contabilidad     10
        // director                 investigacion    20
        // empleado                 ventas           30
        // presidente               produccion       40
        // vendedor

        try {
            conConnection();//Cargamos la conexion

            codigoDept = Integer.parseInt(args[0]);

            /** Realizamos la consulta a Departamentos **/
            departamentos();


            /** Si no debuelve ningun dato, el departamento es incorrecto **/
            if (!rs.next()) {
                throw new departamentoIncorrecto("El departamento con el numero " + codigoDept + " que has introducido no existe");
                //System.out.println("El numero de Departamento no existe");
            } else {

                System.out.println("==============================================================");

                System.out.println("CODIGO DEPARTAMENTO:");
                departamentos(); //Cargamos la consulta del departamento

                while (rs.next()) {
                    System.out.print("Departamento: " + rs.getInt("dept_no") + " ==> " + rs.getString("dnombre") + "\n");
                }

                System.out.println("==============================================================");

                System.out.println("EMPLEADOS:");
                empleados(); //Cargamos la consulta de los Empleados

                int contador = 0; //Añadimos un contador para contar los empleados
                while (rs.next()) {
                    contador++;
                    System.out.println("Apellido:" + rs.getString("apellido") + "\t|\tSalario: " + rs.getFloat("salario") + "\t|\toficio: " + rs.getString("oficio"));
                }
                System.out.println("==============================================================");

                salarioMedio(); //Cargamos la consulta salario Medio

                while (rs.next()) {
                    DecimalFormat formato = new DecimalFormat("##,###.00");
                    String valorFormateado = formato.format(rs.getFloat("salario"));
                    System.out.println("Salario Medio: " + valorFormateado);
                }

                totalEmpleados(); //Cargamos la consulta total de empleados

                while (rs.next()) {
                    System.out.println("Numero de empleados: " + rs.getString("apellido"));
                }

                System.out.println("==============================================================");

                System.out.println("Numero de empleados: " + contador); //Cargamos el contenedor sumado anteriormente
            }

        } catch (NumberFormatException e1) {
            System.err.println(e1.getMessage() + " El parametro introducido no es un numero");
        } catch (SQLException e) {
            System.err.println("\nError de SQL, Comprueba la conexión o la sentencia SQL");
        } catch (departamentoIncorrecto sql) {
            System.err.println("\n" + sql.getMessage());
        }
    }
    public static void conConnection() throws ClassNotFoundException, SQLException {
        String conector = "com.mysql.cj.jdbc.Driver";
        String jdbc = "jdbc";
        String db = "mysql";
        String rutaDB = "localhost/actividad30";
        String usuarioDB = "root";
        String passwordDB = "";

        Class.forName(conector);
        conexion =
                DriverManager.getConnection(jdbc + ":" + db + "://" + rutaDB + "?allowMultiQueries=true", usuarioDB, passwordDB);

        System.out.println("Conexion con MySQL establecida");
    }

    public static void departamentos() throws SQLException {
        /** Consulta Departamento **/
        String sql = "select * from departamentos where dept_no = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, codigoDept);

        rs = ps.executeQuery();
    }

    public static void empleados() throws SQLException {
        /** Consulta Empleados **/
        String sql = "select apellido,salario,oficio from empleados inner join departamentos on empleados.dept_no = departamentos.dept_no where departamentos.dept_no=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, codigoDept);

        rs = ps.executeQuery();
    }

    public static void salarioMedio() throws SQLException {
        String sql = "select AVG(salario) as salario from empleados join departamentos on empleados.dept_no = departamentos.dept_no where departamentos.dept_no=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, codigoDept);

        rs = ps.executeQuery();
    }

    public static void totalEmpleados() throws SQLException {
        String sql = "select COUNT(apellido) as apellido from empleados join departamentos on empleados.dept_no = departamentos.dept_no where departamentos.dept_no=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, codigoDept);

        rs = ps.executeQuery();
    }

    public static class departamentoIncorrecto extends Exception {
        public departamentoIncorrecto(String mensaje) {
            super(mensaje);
        }
    }
}
