import java.util.List;

public class Reporte {

    // CU06: Generar reporte por tipo de falla 
    public static void generarReporteEstado(List<Incidencia> incidencias) {
        long hard = incidencias.stream().filter(i -> i.getTipoFalla() == TipoFalla.HARDWARE).count();
        long soft = incidencias.stream().filter(i -> i.getTipoFalla() == TipoFalla.SOFTWARE).count();
        long red = incidencias.stream().filter(i -> i.getTipoFalla() == TipoFalla.RED).count();

        System.out.println("----- REPORTE DEL ESTADO DEL LABORATORIO -----");
        System.out.println("Total Incidencias: " + incidencias.size());
        System.out.println("Fallas de Hardware: " + hard);
        System.out.println("Fallas de Software: " + soft);
        System.out.println("Fallas de Red:      " + red);
        System.out.println("----------------------------------------------");
    }
}