import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SubMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static List<PersonalMedico> personalMedicoList = new ArrayList<>();
    private static List<Empleado> empleados = new ArrayList<>();  
    private static ConsultasExternas consultasExternas = new ConsultasExternas();
   
     public static void main(String[] args) {
        boolean salir= false;
        while (!salir) {
            System.out.println("Submenú:");
            System.out.println("1. Emisión de facturas");
            System.out.println("2. Listado de Agendas de cada especialista");
            System.out.println("3. Listado de Pacientes por fecha");
            System.out.println("4. Listado de ocupación de habitaciones");
            System.out.println("5. Citas de consulta externa");
            System.out.println("0. Salir del submenú");
            
            int opcion = scanner.nextInt();
            switch (opcion) {
               case 1:
                    System.out.println("Has seleccionado la opción 1 del submenú");
                    break;
                case 2:
                    System.out.println("Has seleccionado la opción 2 del submenú"); 
                    break;
                case 3: 
                     listarPacientesPorFecha(empleados);
                    break;
                case 4:
                    Hospitalizacion.imprimirListadoOcupacion();
                    break;
                case 5:
                    consultasExternas.mostrarTodasLasCitas();
                    break;
                case 0:
                    salir= true; 
                    System.out.println("¡Regresando al menu!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public static void listarPacientesPorFecha(List<Empleado> empleados) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, introduzca la fecha (dd/MM/yyyy):");
        String fechaInput = scanner.nextLine();
        Date fecha = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            fecha = sdf.parse(fechaInput);
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido.");
            return; 
        }

        for (Empleado empleado : empleados) {
            Agenda agenda = empleado.getAgenda();
            List<CitaSanitaria> citasDelDia = agenda.obtenerCitasPorFecha(fecha);
  if (!citasDelDia.isEmpty()) {
                System.out.println("Pacientes de " + empleado.getNombre() + " (" + empleado.getClass().getSimpleName() + ") en la fecha " + fechaInput + ":");
                for (CitaSanitaria cita : citasDelDia) {
                    Paciente paciente = cita.getPaciente();
                    System.out.println(" - Paciente: " + paciente.getNombre() + " (ID: " + paciente.getId() + ")");
                }
            } else {
                System.out.println("No hay pacientes programados para " + empleado.getNombre() + " en la fecha " + fechaInput + ".");
            }
        }
    }
    
   
        
}