package JASPERREPORTS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


public class Principal {
    static String ruta = "D:\\Clase\\2-DAM\\Acceso a Datos\\2Trimestre\\AccesoDatos2T\\src\\main\\java\\JASPERREPORTS\\";
    public static void main(String[] args) {
        String reportSource = ruta + "plantilla\\plantilla.jrxml";
        String reportHTML = ruta + "informes\\Informe.html";
        String reportPDF = ruta + "informes\\Informe.pdf";
        String reportXML = ruta + "informes\\Informe.xml";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("titulo", "LISTADO DE DEPARTAMENTOS.");
        params.put("autor", "CAMPOS");
        params.put("fecha", (new java.util.Date()).toString());
        try {
            JasperReport jasperReport =
                    JasperCompileManager.compileReport(reportSource);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/actividad30", "root", "");

            JasperPrint MiInforme =
                    JasperFillManager.fillReport(jasperReport, params, conn);
            //Visualizar en pantalla
            JasperViewer.viewReport(MiInforme);
            //Convertir a HTML
            JasperExportManager.exportReportToHtmlFile(MiInforme, reportHTML);
            //Convertir a PDF
            JasperExportManager.exportReportToPdfFile(MiInforme, reportPDF);
            //Convertir a XML
            JasperExportManager.exportReportToXmlFile(MiInforme, reportXML, false);
            System.out.println("Archivos Creados");


        } catch (JRException ex) {
            System.out.println("Error de jasper");
            ex.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}