package JASPERREPORTS;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Principal {
    static Connection conexion = null;

    public static void main(String[] args) {
        //Conectamos con la DB
        ConexionDB msql = new ConexionDB();
        conexion = msql.conexion();

        String reportSource = "./src/main/java/JASPERREPORTS/plantilla/plantilla2.jrxml";
        String reportHTML = "./src/main/java/JASPERREPORTS/informes/Informe.html";
        String reportPDF = "./src/main/java/JASPERREPORTS/informes/Informe.pdf";
        String reportXML = "./src/main/java/JASPERREPORTS/informes/Informe.xml";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("titulo", "RESMEN DATOS DE DEPARTAMENTOS..");
        params.put("autor", "CAMPOS");
        params.put("fecha", (new java.util.Date()).toString());
        try {
            JasperReport jasperReport =
                    JasperCompileManager.compileReport(reportSource);
            JasperPrint MiInforme =
                    JasperFillManager.fillReport(jasperReport, params, conexion);
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
            System.err.println("Error de jasper");
            ex.printStackTrace();
        }

    }
}