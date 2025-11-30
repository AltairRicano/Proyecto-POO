import java.time.LocalDateTime;

public class Incidencia {
    private static int contadorIds = 1;
    private int id;
    private String descripcion;
    private TipoFalla tipoFalla;
    private Prioridad prioridad;
    private EstadoIncidencia estado;
    private LocalDateTime fechaReporte;

    // Relaciones
    private Persona usuarioReporta;
    private EquipoComputo equipo;
    private Tecnico tecnicoAsignado;

    public Incidencia(Persona usuario, EquipoComputo equipo, TipoFalla tipo, String descripcion) {
        this.id = contadorIds++;
        this.usuarioReporta = usuario;
        this.equipo = equipo;
        this.tipoFalla = tipo;
        this.descripcion = descripcion;
        this.fechaReporte = LocalDateTime.now();
        this.estado = EstadoIncidencia.PENDIENTE;
        this.prioridad = Prioridad.MEDIA; // Prioridad por defecto hasta que un técnico la cambie

        // Al crearse la incidencia, el equipo pasa a inactivo o mantenimiento
        equipo.setEstado(EstadoEquipo.INACTIVO);
    }

    // Métodos para cambiar estado y prioridad
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }

    public void setTecnico(Tecnico tecnico) {
        this.tecnicoAsignado = tecnico;
        this.estado = EstadoIncidencia.EN_PROCESO;
        this.equipo.setEstado(EstadoEquipo.MANTENIMIENTO);
    }

    public void resolverIncidencia() {
        this.estado = EstadoIncidencia.TERMINADO;
        this.equipo.setEstado(EstadoEquipo.ACTIVO);
    }

    // Getters
    public int getId() { return id; }
    public TipoFalla getTipoFalla() { return tipoFalla; }
    public EstadoIncidencia getEstado() { return estado; }
    public Tecnico getTecnicoAsignado() { return tecnicoAsignado; }
    public EquipoComputo getEquipo() { return equipo; }
    public Prioridad getPrioridad() { return prioridad; }

    @Override
    public String toString() {
        return "Incidencia #" + id + " [" + estado + "] Tipo: " + tipoFalla +
                " | Equipo: " + equipo.getIdEquipo() +
                " | Prioridad: " + prioridad +
                " | Técnico: " + (tecnicoAsignado != null ? tecnicoAsignado.getNombre() : "Sin asignar");
    }
}