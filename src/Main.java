import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionIncidencias gestion = new GestionIncidencias();
        Scanner scanner = new Scanner(System.in);



        // Datos Mock (Simulados) para pruebas
        gestion.registrarEquipo(new EquipoComputo("PC-01"));
        gestion.registrarEquipo(new EquipoComputo("PC-02"));
        gestion.registrarTecnico(new Tecnico("T01", "Juan Perez"));
        gestion.registrarTecnico(new Tecnico("T02", "Maria Lopez")); // Para probar carga de trabajo

        Alumno alumno = new Alumno("A01", "Carlos Estudiante", "S2000");

        int opcion;
        do {
            System.out.println("\n--- SISTEMA TICKETING LAB-LIS ---");
            System.out.println("1. Reportar Incidencia (Alumno/Profesor)");
            System.out.println("2. Consultar Historial (Técnico)");
            System.out.println("3. Cambiar Prioridad (Técnico)");
            System.out.println("4. Resolver Incidencia (Técnico)");
            System.out.println("5. Generar Reporte (Admin)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("ID Equipo (ej. PC-01): ");
                    String idEq = scanner.next();
                    System.out.println("Tipo (1.Hardware, 2.Software, 3.Red): ");
                    int tipoInt = scanner.nextInt();
                    TipoFalla tipo = TipoFalla.values()[tipoInt - 1];
                    System.out.print("Descripción: ");
                    scanner.nextLine(); // Consumir nueva linea
                    String desc = scanner.nextLine();

                    gestion.reportarIncidencia(alumno, idEq, tipo, desc);
                    break;
                case 2:
                    for (Incidencia inc : gestion.getHistorial()) {
                        System.out.println(inc);
                    }
                    break;
                case 3:
                    System.out.print("ID Incidencia: ");
                    int idIncP = scanner.nextInt();
                    System.out.println("Nueva Prioridad (1.Urgente, 2.Alta, 3.Media, 4.Baja): ");
                    int prioInt = scanner.nextInt();
                    gestion.cambiarPrioridad(idIncP, Prioridad.values()[prioInt - 1]);
                    break;
                case 4:
                    System.out.print("ID Incidencia a cerrar: ");
                    int idIncC = scanner.nextInt();
                    gestion.resolverIncidencia(idIncC);
                    break;
                case 5:
                    Reporte.generarReporteEstadoLaboratorio(gestion);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}