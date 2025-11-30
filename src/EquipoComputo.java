public class EquipoComputo {
    private String idEquipo;
    private EstadoEquipo estado;

    public EquipoComputo(String idEquipo) {
        this.idEquipo = idEquipo;
        this.estado = EstadoEquipo.ACTIVO;
    }

    public String getIdEquipo() { return idEquipo; }
    public EstadoEquipo getEstado() { return estado; }
    public void setEstado(EstadoEquipo estado) { this.estado = estado; }
}