import java.util.List;
import java.util.ArrayList;

public class Reporte {

    public static void generarReporteEstadoLaboratorio(GestionIncidencias gestion) {
        List<EquipoComputo> inventario = gestion.getInventario();

        List<EquipoComputo> equiposActivos = new ArrayList<>();
        List<EquipoComputo> equiposConIncidencia = new ArrayList<>();

        // Clasificamos los equipos según su estado actual
        for (EquipoComputo equipo : inventario) {
            if (equipo.getEstado() == EstadoEquipo.ACTIVO) {
                equiposActivos.add(equipo);
            } else {
                equiposConIncidencia.add(equipo);
            }
        }

        System.out.println("\n===== FOTO ACTUAL DEL LABORATORIO =====");
        // Aquí mostramos cuántos sirven vs cuántos no
        System.out.println(">> EQUIPOS FUNCIONANDO: " + equiposActivos.size());
        System.out.println(">> EQUIPOS EN REPARACIÓN: " + equiposConIncidencia.size());

        if (!equiposConIncidencia.isEmpty()) {
            System.out.println("\n   [DETALLE DE FALLAS ACTIVAS]");
            for (EquipoComputo eq : equiposConIncidencia) {
                // Buscamos cuál es la incidencia que tiene detenido al equipo
                Incidencia inc = gestion.buscarIncidenciaActivaPorEquipo(eq.getIdEquipo());
                String causa = (inc != null) ? inc.getTipoFalla().toString() : "MANTENIMIENTO GENERAL";

                System.out.println("   - Equipo " + eq.getIdEquipo() + ": " + causa);
            }
        }
        System.out.println("=======================================\n");
    }
}