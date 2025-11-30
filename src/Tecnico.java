import java.util.ArrayList;
import java.util.List;


public class Tecnico extends Persona{
    private List<Incidencia> incidenciasAsignadas;

    public Tecnico(String id, String nombre) {
        super(id, nombre);
        this.incidenciasAsignadas = new ArrayList<>();
    }

    public void asignarIncidencia(Incidencia incidencia) {
        this.incidenciasAsignadas.add(incidencia);
    }

    public int getCargaTrabajo() {
        return (int) incidenciasAsignadas.stream()
                .filter(i -> i.getEstado() != EstadoIncidencia.TERMINADO).count();
    }

    public List<Incidencia> getIncidenciasAsignadas() {
        return incidenciasAsignadas;
    }
}