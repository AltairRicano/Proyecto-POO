import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestionIncidencias {
    private List<Incidencia> historialIncidencias;
    private List<Tecnico> listaTecnicos;
    private List<EquipoComputo> inventarioEquipos;

    public GestionIncidencias() {
        this.historialIncidencias = new ArrayList<>();
        this.listaTecnicos = new ArrayList<>();
        this.inventarioEquipos = new ArrayList<>();
    }

    // Agregamos este getter para que el Reporte pueda leer los equipos
    public List<EquipoComputo> getInventario() {
        return inventarioEquipos;
    }

    // NUEVO MÉTODO: Busca si un equipo tiene una incidencia activa (no terminada)
    // Esto sirve para que el reporte sepa POR QUÉ está fallando el equipo.
    public Incidencia buscarIncidenciaActivaPorEquipo(String idEquipo) {
        return historialIncidencias.stream()
                .filter(i -> i.getEquipo().getIdEquipo().equals(idEquipo)
                        && i.getEstado() != EstadoIncidencia.TERMINADO)
                .findFirst()
                .orElse(null);
    }

    // Métodos de configuración inicial
    public void registrarTecnico(Tecnico t) { listaTecnicos.add(t); }
    public void registrarEquipo(EquipoComputo e) { inventarioEquipos.add(e); }
    public EquipoComputo buscarEquipo(String id) {
        return inventarioEquipos.stream()
                .filter(e -> e.getIdEquipo().equals(id))
                .findFirst().orElse(null);
    }

    // RF01, RF02, RF05: Registrar incidencia y asignación automática
    public void reportarIncidencia(Persona usuario, String idEquipo, TipoFalla tipo, String descripcion) {
        EquipoComputo equipo = buscarEquipo(idEquipo);
        if (equipo != null) {
            Incidencia nueva = new Incidencia(usuario, equipo, tipo, descripcion);
            asignarTecnicoAutomaticamente(nueva); // Lógica de asignación automática
            historialIncidencias.add(nueva);
            System.out.println("Incidencia reportada con éxito. ID: " + nueva.getId());
        } else {
            System.out.println("Equipo no encontrado.");
        }
    }

    // Lógica para asignar al técnico con menos carga 
    private void asignarTecnicoAutomaticamente(Incidencia incidencia) {
        if (listaTecnicos.isEmpty()) return;

        Tecnico menosOcupado = listaTecnicos.get(0);
        for (Tecnico t : listaTecnicos) {
            if (t.getCargaTrabajo() < menosOcupado.getCargaTrabajo()) {
                menosOcupado = t;
            }
        }
        menosOcupado.asignarIncidencia(incidencia);
        incidencia.setTecnico(menosOcupado);
        System.out.println("Técnico asignado automáticamente: " + menosOcupado.getNombre());
    }

    // RF03: Asignar prioridad
    public void cambiarPrioridad(int idIncidencia, Prioridad nuevaPrioridad) {
        Incidencia inc = buscarIncidencia(idIncidencia);
        if (inc != null) {
            inc.setPrioridad(nuevaPrioridad);
            System.out.println("Prioridad actualizada.");
        }
    }

    // RF06: Resolver incidencia
    public void resolverIncidencia(int idIncidencia) {
        Incidencia incidenciaActual = buscarIncidencia(idIncidencia);

        if (incidenciaActual != null) {
            // 1. Cerramos la incidencia (cambia estado a TERMINADO)
            incidenciaActual.resolverIncidencia();

            // 2. Verificamos si el equipo tiene OTRAS cuentas pendientes
            EquipoComputo equipoAfectado = incidenciaActual.getEquipo();

            boolean tieneOtrasPendientes = historialIncidencias.stream()
                    .anyMatch(i -> i.getEquipo().equals(equipoAfectado)
                            && i.getEstado() != EstadoIncidencia.TERMINADO);

            // 3. Decidimos el estado del equipo
            if (!tieneOtrasPendientes) {
                equipoAfectado.setEstado(EstadoEquipo.ACTIVO);
                System.out.println(">> Incidencia #" + idIncidencia + " cerrada. El equipo " + equipoAfectado.getIdEquipo() + " está ACTIVO nuevamente.");
            } else {
                System.out.println(">> Incidencia #" + idIncidencia + " cerrada.");
                System.out.println("   ¡ATENCIÓN! El equipo sigue en MANTENIMIENTO porque tiene otras fallas pendientes.");
            }
        } else {
            System.out.println(">> ERROR: No se encontró ninguna incidencia con el ID: " + idIncidencia);
        }
    }

    public Incidencia buscarIncidencia(int id) {
        return historialIncidencias.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public List<Incidencia> getHistorial() { return historialIncidencias; }
}