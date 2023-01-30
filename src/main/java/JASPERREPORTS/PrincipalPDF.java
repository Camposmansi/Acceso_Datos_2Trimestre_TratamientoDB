package JASPERREPORTS;

public class PrincipalPDF {
    public static void main(String[] args) {
        ConexionDB reportGenerator = new ConexionDB();
        reportGenerator.generateReport("plantilla2.jrxml",  "Ver Datos de los usuarios insertados", "Carlos Campos");
    }
}
