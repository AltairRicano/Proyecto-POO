public abstract class Persona { // Cambio de nombre
    protected String id;
    protected String nombre;

    public Persona(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
}