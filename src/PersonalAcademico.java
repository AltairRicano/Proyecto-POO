public class PersonalAcademico extends Persona {
    private RolAcademico rol;

    public PersonalAcademico(String id, String nombre, RolAcademico rol) {
        super(id, nombre);
        this.rol = rol;
    }

}