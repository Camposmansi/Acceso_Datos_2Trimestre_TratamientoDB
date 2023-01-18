import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad29 {
    public static void main(String[] args) {

        File scriptFile = new File("D:\\Clase\\2-DAM\\Acceso a Datos\\2Trimestre\\AccesoDatos2T\\src\\main\\java\\scriptmysql.sql" ) ;
        System.out.println("\n\nFichero de consulta" + scriptFile.getName());
        System.out.println("Convirtiendo el fichero a cadena ... ");
        BufferedReader entrada = null;
        try {
            entrada= new BufferedReader(new FileReader(scriptFile )) ;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR NO HAY FILE: "+ e.getMessage ( )) ;
        }
        String linea = null;
        StringBuilder stringBuilder = new StringBuilder();
        String salto= System.getProperty("line.separator");
        try {
            while ((linea= entrada.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }
        } catch (IOException e) {
            System.out.println("ERROR de E/S, al operar " + e.getMessage());
        }
        String consulta= stringBuilder.toString();
        System.out.println(consulta);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            System.out.println("ERROR Driver:"+ e.getMessage());
        }
        try {
            Connection connmysql = DriverManager.getConnection
                    ("jdbc:mysql://localhost/actividad29?allowMultiQueries=true","root", "");
            Statement sents = connmysql.createStatement();
            int res= sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito, res="+ res) ;
            connmysql.close();
            sents.close();
        }catch (SQLException e) {
            System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
        }
    }
}