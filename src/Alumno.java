public class Alumno extends Persona{
    private final String matricula;

    public Alumno(String id, String nombre, String matricula) {
        super(id, nombre);
        this.matricula = matricula;
    }
}