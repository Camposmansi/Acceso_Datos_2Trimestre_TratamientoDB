package JASPERREPORTS;

import net.sf.jasperreports.engine.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConexionDB {
    //public static Connection conn;
    static Connection conexion;
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

            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/actividad30", "root", "");

            System.out.println("Conexion con MySQL establecida\n");
        }catch (SQLException e){
            System.out.println("Error al ejecutar sentencia SQL");
        }catch (ClassNotFoundException e){
            System.out.println("Error de Driver.");
        }

        return null;
    }

    public void generateReport(String reportSource, String tituloPDF, String autorPDF) {
        //String reportSource = "src/Informes/I_usuarios.jrxml";
        //String reportPDF = "Informe.pdf";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("titulo", tituloPDF);
        params.put("autor", autorPDF);
        params.put("fecha", (new java.util.Date()).toString());
        try {

            conexion();

            JasperReport jasperReport = JasperCompileManager.compileReport("./src/main/java/JASPERREPORTS/plantilla/" + reportSource);
            JasperPrint MiInforme = JasperFillManager.fillReport(jasperReport, params, conexion);

            File tempFile = File.createTempFile("inf-temp-", ".pdf");
            tempFile.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                JasperExportManager.exportReportToPdfStream(MiInforme, fos);
            }
            Desktop.getDesktop().open(tempFile);
            System.out.println(tempFile);

            //System.out.println("Se ha creado el pdf correctamente");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Error en Base de datos\n\n" + ex.getMessage(), "Conexion", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
