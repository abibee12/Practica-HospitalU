import java.util.Scanner;
import java.util.List; 
import java.util.HashSet;
import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.UUID;


public class HospitalMenu {
    
    private static Scanner scanner = new Scanner(System.in);
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Empleado> empleados = new ArrayList<>();
    private static HashSet<LocalTime> horasReservadas = new HashSet<>();
    private static Hospitalizacion hospitalizacion = new Hospitalizacion();
    private static HashMap<PruebasMedicas.TipoPrueba, List<LocalDateTime>> citasDisponibles = new HashMap<>();
    //private static UnidadFormacion unidadFormacion;
    private static ConsultasExternas consultasExternas = new ConsultasExternas();
    
    private static UnidadFormacion unidadFormacion = new UnidadFormacion("Nombre de la Unidad");

    public static void main(String[] args) {
        boolean salir= false;
        while (!salir) {
            System.out.println("1. Alta pacientes");
            System.out.println("2. Alta estudiantes");
            System.out.println("3. Alta empleados");
            System.out.println("4. Buscar y Mostrar Datos");
            System.out.println("5. Agenda de médico");
            System.out.println("6. Agenda de enfermería");
            System.out.println("7. Dar Citas");
            System.out.println("8. Consultas/Actualizaciones calendario personal médico/personal de enfermería.");
            System.out.println("9. Dar de Alta/baja hospitalizacion");
            System.out.println("10. Crear/Asignar clases al personal");
            System.out.println("11.  para ir al submenu(nivel 3)");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    altaPaciente();
                    break;
                case 2:
                    darDeAltaEstudiante();
                    break;
            
                case 3:
                    altaEmpleado();
                    break;
                    
                case 4:
                    buscarPorNombreYMostrarDatos();
                    break;
                case 5:
                    agendaMedico();
                    break;
                case 6:
                    agendaEnfermeria();
                    break;
                case 7:
                    darCita();
                    break;
                case 8:
                    verAgendaEmpleado();
                    break;
                case 9:
                    System.out.println("¿Desea dar de alta o de baja a un paciente? (alta/baja)");
                    String accion = scanner.next();
                    if ("alta".equalsIgnoreCase(accion)) {
                        darAltaHospitalizacion();
                    } else if ("baja".equalsIgnoreCase(accion)) {
                        darBajaHospitalizacion();
                    } else {
                        System.out.println("Acción no reconocida.");
                    }
                    break;
                case 10:
                    crearYAsignarClase();
                    break; 
                case 11:
                     SubMenu.main(new String[0]);
                     break;
                case 0:
                    salir= true; 
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    //OPCION 1- ALTA PACIENTES
    private static void altaPaciente() {
        
    System.out.println("Introduzca el nombre del paciente:");
    scanner.nextLine(); 
    String nombre = scanner.nextLine();
    
    System.out.println("Introduzca la edad del paciente:");
    int edad = scanner.nextInt();
    scanner.nextLine();
    
     System.out.println("¿El paciente tiene seguro médico privado? (si/no):");
    String respuestaSeguro = scanner.nextLine();
    boolean tieneSeguroPrivado = respuestaSeguro.equalsIgnoreCase("si");

    String numeroSeguridadSocial = null;
    if (!tieneSeguroPrivado) {
        System.out.println("Introduzca el número de seguridad social del paciente (dejar en blanco si no tiene):");
        numeroSeguridadSocial = scanner.nextLine();
        if (numeroSeguridadSocial.isEmpty()) {
            numeroSeguridadSocial = null;
        }
    }
    
    Paciente paciente = new Paciente(nombre, edad, numeroSeguridadSocial, tieneSeguroPrivado);
    
    pacientes.add(paciente); 
    System.out.println("------ Paciente  -----");
    System.out.println("ID: "  + paciente.getId()  );
    System.out.println("NOMBRE: "  + nombre );
    System.out.println("EDAD: "  + edad );
    
    if (numeroSeguridadSocial != null) {
        System.out.println("NÚMERO DE SEGURIDAD SOCIAL: " + numeroSeguridadSocial);
    }
    if (tieneSeguroPrivado) {
        System.out.println("El paciente tiene seguro médico privado.");
    }
    System.out.println("HA SIDO DADO DE ALTA CON ÉXITO");
    
    
}
    //END-OPCION1  
        
    //OPCION 2- ALTA ESTUDIANTES
   public static void darDeAltaEstudiante() {
    System.out.print("Ingrese el nombre del estudiante: ");
    scanner.nextLine(); 
    String nombre = scanner.nextLine();
    
    System.out.print("Ingrese la edad del estudiante: ");
    int edad = scanner.nextInt();  
    scanner.nextLine(); 

    Estudiante estudiante = new Estudiante(nombre, edad);
 
    asignarClasesYCitas(estudiante);
    unidadFormacion.agregarEstudiante(estudiante);
    System.out.println("Estudiante registrado con éxito.");
    }

 private static void asignarClasesYCitas(Estudiante estudiante) {
    System.out.println("Seleccione la opción deseada:");
    System.out.println("1. Asignar a una clase con personal médico o de enfermería");
    System.out.println("2. Asignar a una cita con un médico");
    int opcion = scanner.nextInt();
    scanner.nextLine(); // Limpiar el buffer

    switch (opcion) {
        case 1:
          
        // Asignar clases con personal médico o de enfermería
        System.out.println("Asignando a clases disponibles...");
        List<ClaseFormacion> clasesDisponibles = unidadFormacion.getClasesDisponibles().stream()
            .filter(clase -> clase.getInstructor() instanceof PersonalMedico || clase.getInstructor() instanceof PersonalEnfermeria)
            .collect(Collectors.toList());
    
        if (clasesDisponibles.isEmpty()) {
            System.out.println("No hay clases disponibles con personal médico o de enfermería.");
        } else {
            System.out.println("Seleccione la clase que desea asignar:");
            for (int i = 0; i < clasesDisponibles.size(); i++) {
                ClaseFormacion clase = clasesDisponibles.get(i);
                System.out.println((i + 1) + "Clase:  " + clase.getTitulo()  );
                System.out.println("Instructor: " + clase.getInstructor().getNombre());
            }
            int seleccion = scanner.nextInt();
            scanner.nextLine(); 
            
            if (seleccion > 0 && seleccion <= clasesDisponibles.size()) {
                ClaseFormacion claseSeleccionada = clasesDisponibles.get(seleccion - 1);
                estudiante.agregarClase(claseSeleccionada);
                System.out.println("Clase '" + claseSeleccionada.getTitulo() + "' asignada con éxito." );
                System.out.println("--DATOS CITA--");
                System.out.println("Fecha: " +  claseSeleccionada.getFecha() );
                System.out.println("Hora: " + claseSeleccionada.getHora());
            } else {
                System.out.println("Selección inválida.");
            }
        }
        break;
        case 2:
            // Asignar citas con personal médico
            System.out.println("Seleccione el médico para ver su agenda:");
            Empleado medicoSeleccionado = obtenerPersonalMedico();
            if (medicoSeleccionado != null) {
                List<CitaSanitaria> citas = ((PersonalMedico) medicoSeleccionado).getAgenda().obtenerTodasLasCitas();
                if (citas.isEmpty()) {
                    System.out.println("No hay citas programadas para " + medicoSeleccionado.getNombre());
                } else {
                    System.out.println("Citas programadas para " + medicoSeleccionado.getNombre() + ":");
                    for (int i = 0; i < citas.size(); i++) {
                        CitaSanitaria cita = citas.get(i);
                        System.out.println((i + 1) + ". Fecha: " + cita.getFecha() + ", Paciente: " + cita.getPaciente().getNombre());
                    }
                    System.out.print("Seleccione una cita para asignar al estudiante: ");
                    int citaSeleccionada = scanner.nextInt();
                    scanner.nextLine();
                    Date fechaSeleccionada = citas.get(citaSeleccionada - 1).getFecha();
                    LocalDateTime fechaHora = fechaSeleccionada.toInstant()
                                                               .atZone(ZoneId.systemDefault())
                                                               .toLocalDateTime();

                    CitaEstudiante citaEstudiante = new CitaEstudiante(fechaHora, medicoSeleccionado);
                    estudiante.agregarCita(citaEstudiante);
                    System.out.println("Cita asignada con éxito.");
                }
            }
            break;
        default:
            System.out.println("Opción no válida. Por favor, intente de nuevo.");
            break;
    }
}


   //END-OPCION2

   //OPCION 3- ALTA EMPLEADOS

    private static void altaEmpleado() {
        
    System.out.println("Seleccione el tipo de empleado para dar de alta:");
    System.out.println("1. Personal médico");
    System.out.println("2. Personal de enfermería");
    System.out.println("3. Regresar al menú principal");
        int seleccion;
       
        seleccion = scanner.nextInt();
        switch (seleccion) {
            case 1:
                darAltaPersonalMedico();
                break;
            case 2:
                darAltaPersonalEnfermeria();
                break;
            case 3:
                // Regresar al menú principal
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
                break;
        }
}

private static void darAltaPersonalMedico() {
        System.out.println("Introduzca el nombre del personal médico:");
        scanner.nextLine(); 
        String nombre = scanner.nextLine(); 
        
        System.out.println("Introduzca la edad del personal médico:");
        int edad = scanner.nextInt();
        scanner.nextLine();
       
        Especialidad especialidad = null;
        while (especialidad == null) {
            System.out.println("Introduzca la especialidad del personal médico (ej. CARDIOLOGIA, DERMATOLOGIA):");
            
            String especialidadStr = scanner.nextLine();
            try {
                especialidad = Especialidad.valueOf(especialidadStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Especialidad no válida. Por favor, intente de nuevo.");
            }
        }
        
        Turno turno = obtenerTurno();
  
        Unidad unidad;
    if (turno == Turno.NOCHE) {
        System.out.println("Introduzca la unidad a la que se asignará el personal médico:");
        String unidadStr = scanner.nextLine();
        unidad = Unidad.valueOf(unidadStr.toUpperCase());
        if (unidad != Unidad.URGENCIAS) {
            System.out.println("Por la noche solo tienen que estar en urgencias.");
            
            return; 
        }
    } else {
        System.out.println("Introduzca la unidad a la que se asignará el personal médico:");
        String unidadStr = scanner.nextLine();
        unidad = Unidad.valueOf(unidadStr.toUpperCase());
    }

        
        PersonalMedico medico = new PersonalMedico(nombre, edad, unidad, especialidad, turno);
        empleados.add(medico);
        
        System.out.println("***** Personal Medico *****");
        System.out.println("ID: " + medico.getId());
        System.out.println("NOMBRE:" + nombre);
        System.out.println("UNIDAD:" + unidad);
        System.out.println("Turno:" + turno);
        System.out.println(" " );
        System.out.println("Personal médico dado de alta con exito ");
        
    }

   private static void darAltaPersonalEnfermeria() {
    System.out.println("Introduzca el nombre del personal de enfermería:");
    scanner.nextLine();
    String nombre = scanner.nextLine();
    System.out.println("Introduzca la edad del personal de enfermería:");
    int edad = scanner.nextInt();
    scanner.nextLine(); 
    Turno turno = obtenerTurno();

    Unidad unidad = Unidad.URGENCIAS; 
    if (turno == Turno.NOCHE) {
        System.out.println("El personal de enfermería de turno de noche debe ser asignado a la unidad de Urgencias automáticamente.");
    } else {
        System.out.println("Introduzca la unidad a la que se asignará el personal de enfermería (no Urgencias):");
        String unidadStr = scanner.nextLine();
        unidad = Unidad.valueOf(unidadStr.toUpperCase());
        if (unidad == Unidad.URGENCIAS) {
            System.out.println("La unidad de Urgencias solo está disponible para el turno de noche.");
            return; 
        }
    }

    PersonalEnfermeria enfermeria = new PersonalEnfermeria(nombre, edad, unidad, turno);
    empleados.add(enfermeria);
    
    System.out.println("***** Personal Enfermeria *****");
        System.out.println("ID: " + enfermeria.getId());
        System.out.println("NOMBRE:" + nombre);
        System.out.println("UNIDAD:" + unidad);
        System.out.println("Turno:" + turno);
        System.out.println(" " );
        System.out.println("Personal enfermeria dado de alta con exito ");
}

    private static Turno obtenerTurno() {
    while (true) {
        System.out.println("Introduzca el turno a la que se asignará el personal médico/enfermería (MAÑANA, TARDE, NOCHE):");
        String turnoStr = scanner.nextLine().trim().toUpperCase();
        try {
            return Turno.valueOf(turnoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Turno inválido. Por favor, intente de nuevo.");
        }
    }
}

   //END-OPCION3

//OPCION 4- BUSCAR Y MOSTRAR DATOS
    private static void buscarPorNombreYMostrarDatos() {
    System.out.println("Ingrese el nombre de la persona (empleado o paciente) a buscar:");
    scanner.nextLine(); 
    String nombreBuscado = scanner.nextLine();
    boolean encontrado = false;
    for (Empleado empleado : empleados) {
        if (empleado.getNombre().equalsIgnoreCase(nombreBuscado)) {
            encontrado = true;
            System.out.println("Empleado encontrado:");
            
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Edad: " + empleado.getEdad());
            
            System.out.println("Unidad: " + empleado.getUnidad().name());
            if (empleado instanceof PersonalMedico) {
                System.out.println("Especialidad: " + ((PersonalMedico) empleado).getEspecialidad());
            }
            break; 
        }
    }

    
    if (!encontrado) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNombre().equalsIgnoreCase(nombreBuscado)) {
                encontrado = true;
                System.out.println("Paciente encontrado:");
                
                System.out.println("Nombre: " + paciente.getNombre());
                System.out.println("Edad: " + paciente.getEdad());
               
               
                break; 
            }
        }
    }

    if (!encontrado) {
        System.out.println("No se encontró un empleado o paciente con ese nombre.");
    }
}

 

//END-OPCION4

    private static void agendaMedico() {
        System.out.println("Mostrando agenda de un médico...");
    }

    private static void agendaEnfermeria() {
        System.out.println("Mostrando agenda de una enfermería...");
    }

    //OPCION 7- DAR CITA CON MEDICO/ENFERMERO O PRUEBAS MEDICAS
    private static void darCita() {
    System.out.println("Seleccione el tipo de empleado para la cita:");
    System.out.println("1. Personal médico");
    System.out.println("2. Personal de enfermería");
    System.out.println("3. Programar una prueba médica");
    int tipoEmpleado = scanner.nextInt();
    scanner.nextLine(); 
    
    Empleado empleado = null;
    if (tipoEmpleado == 1) {
        empleado = obtenerPersonalMedico();
    } else if (tipoEmpleado == 2) {
        empleado = obtenerPersonalEnfermeria();
    } else if (tipoEmpleado == 3) {
        // Llamada al método para programar una prueba médica
        darCitaPruebaMedica();
    } else {
        System.out.println("Tipo de empleado no válido.");
        return;
    }
    
    

    if (empleado == null) {
        System.out.println("No se ha seleccionado ningún empleado.");
        return;
    }

    Paciente paciente = seleccionarPaciente();
    if (paciente == null) {
        System.out.println("No se ha seleccionado ningún paciente.");
        return;
    }

    Turno turno = empleado.getTurno();
    if (turno== Turno.NOCHE) {
        System.out.println("El empleado seleccionado tiene turno de noche y no puede tener citas con pacientes.");
        return;
    }
    
    LocalDate fecha = obtenerFechaCita(); // Obtiene la fecha de la cita
    LocalTime hora = obtenerHoraCita(empleado.getTurno()); // Obtiene la hora de la cita
    LocalDateTime fechaCitaLDT = LocalDateTime.of(fecha, hora); // Combina fecha y hora
    Date fechaCita = convertirALocalDate(fechaCitaLDT);
    
    // Verificar disponibilidad en la agenda del empleado
    if (empleado.getAgenda().verificarDisponibilidad(turno, fechaCita)) {
        CitaSanitaria cita = new CitaSanitaria(empleado, paciente, turno, fechaCita);
        empleado.getAgenda().agregarCita(cita);
        consultasExternas.agregarCita(cita);
        System.out.println("---Cita creada con éxito---");
        System.out.println("Paciente: " + paciente.getNombre() );
        System.out.println("Dr/Dra: "  + empleado.getNombre() );                  
        System.out.println("Fecha de la cita: " + fechaCita);
    } else {
        System.out.println("No hay disponibilidad para el turno seleccionado.");
    }
}

  private static Paciente seleccionarPaciente() {
    System.out.println("Ingrese el nombre del paciente:");
    String nombrePaciente = scanner.nextLine(); 

    for (Paciente paciente : pacientes) {
        if (paciente.getNombre().equalsIgnoreCase(nombrePaciente)) {
            return paciente;
        }
    }

    System.out.println("Paciente no encontrado. Por favor, intente de nuevo.");
    return seleccionarPaciente(); // Llamada recursiva para una nueva selección
}

private static Date convertirALocalDate(LocalDateTime fechaCita) {
    return Date.from(fechaCita.atZone(ZoneId.systemDefault()).toInstant());
    }

private static LocalDate obtenerFechaCita() {
    
    System.out.println("Seleccione el día para la cita:");
        System.out.println("1: Lunes 3 de junio del 2024");
        System.out.println("2: Martes 4 de junio");
        System.out.println("3: Miércoles 5 de junio");
        System.out.println("4: Jueves 6 de junio");
        System.out.println("5: Lunes 10 de junio del 2024");
        System.out.println("6: Martes 11 de junio");
        System.out.println("7: Miércoles 12 de junio");
        System.out.println("8: Jueves 13 de junio");
        System.out.println("9: Lunes 17 de junio del 2024");
        System.out.println("10: Martes 18 de junio");
        System.out.println("11: Miércoles 19 de junio");
        System.out.println("12: Jueves 20 de junio");
        int diaSeleccionado = scanner.nextInt();
        scanner.nextLine(); 
        
        LocalDate fechaCita = LocalDate.now();
        switch (diaSeleccionado) {
            case 1: fechaCita = LocalDate.of(2024,6,3); break;
            case 2: fechaCita = LocalDate.of(2024,6,4); break;
            case 3: fechaCita = LocalDate.of(2024,6,5); break;
            case 4: fechaCita = LocalDate.of(2024,6,6); break;
            case 5: fechaCita = LocalDate.of(2024,6,10); break;
            case 6: fechaCita = LocalDate.of(2024,6,11); break;
            case 7: fechaCita = LocalDate.of(2024,6,12); break;
            case 8: fechaCita = LocalDate.of(2024,6,13); break;
            case 9: fechaCita = LocalDate.of(2024,6,17); break;
            case 10: fechaCita = LocalDate.of(2024,6,18); break;
            case 11: fechaCita = LocalDate.of(2024,6,19); break;
            case 12: fechaCita = LocalDate.of(2024,6,20); break;
            default: System.out.println("Selección inválida. Por favor, intente de nuevo.");
            return obtenerFechaCita();
        }
        return fechaCita;
    }  

private static LocalTime convertirSeleccionAHora(int seleccion, Turno turno) {
    LocalTime hora = LocalTime.of(8, 0); 
    switch (seleccion) {
        case 1: hora = (turno == Turno.MAÑANA) ? LocalTime.of(8, 0) : LocalTime.of(13, 0); break;
        case 2: hora = (turno == Turno.MAÑANA) ? LocalTime.of(9, 0) : LocalTime.of(14, 0); break;
        case 3: hora = (turno == Turno.MAÑANA) ? LocalTime.of(10, 0) : LocalTime.of(15, 0); break;
        case 4: hora = (turno == Turno.MAÑANA) ? LocalTime.of(10, 45) : LocalTime.of(16, 45); break;
        case 5: hora = (turno == Turno.MAÑANA) ? LocalTime.of(11, 0) : LocalTime.of(19, 0); break;
        default: System.out.println("Selección inválida. Por favor, intente de nuevo.");
                 return convertirSeleccionAHora(scanner.nextInt(), turno);
    }
    return hora;
}


private static LocalTime mostrarHorasDisponibles(Turno turno) {
    System.out.println("Seleccione la hora para el turno de " + (turno == Turno.MAÑANA ? "mañana" : "tarde") + ":");
    LocalTime[] opciones = new LocalTime[5];
    if (turno == Turno.MAÑANA) {
        opciones[0] = LocalTime.of(8, 0);
        opciones[1] = LocalTime.of(9, 0);
        opciones[2] = LocalTime.of(10, 0);
        opciones[3] = LocalTime.of(10, 45);
        opciones[4] = LocalTime.of(11, 0);
    } else {
        opciones[0] = LocalTime.of(13, 0);
        opciones[1] = LocalTime.of(14, 0);
        opciones[2] = LocalTime.of(15, 0);
        opciones[3] = LocalTime.of(16, 45);
        opciones[4] = LocalTime.of(19, 0);
    }

    for (int i = 0; i < opciones.length; i++) {
        if (!horasReservadas.contains(opciones[i])) {
            System.out.println((i + 1) + ": " + opciones[i]);
        }
    }

    int horaSeleccionada = scanner.nextInt();
    scanner.nextLine(); 
    LocalTime horaCita = opciones[horaSeleccionada - 1];

    while (horasReservadas.contains(horaCita)) {
        System.out.println("La hora seleccionada ya está reservada. Por favor, elija otra hora.");
        horaSeleccionada = scanner.nextInt();
        scanner.nextLine();   
        horaCita = opciones[horaSeleccionada - 1];
    }

    horasReservadas.add(horaCita);
    return horaCita;
}

private static LocalTime obtenerHoraCita(Turno turno) {
    return mostrarHorasDisponibles(turno);
}
 
private static Empleado obtenerPersonalMedico() {
    System.out.println("Seleccione un médico de la lista:");
    List<Empleado> medicos = empleados.stream()
                                      .filter(e -> e instanceof PersonalMedico)
                                      .collect(Collectors.toList());

    if (medicos.isEmpty()) {
        System.out.println("No hay médicos disponibles.");
        return null;
    }

    for (int i = 0; i < medicos.size(); i++) {
        PersonalMedico medico = (PersonalMedico) medicos.get(i);
        System.out.println((i + 1) + ". " + medico.getNombre() + " - Especialidad: " + medico.getEspecialidad() + "turno" + medico.getTurno() );
    }

    System.out.println("Ingrese el número del médico:");
    int indice = scanner.nextInt() - 1;
    scanner.nextLine(); 

    if (indice >= 0 && indice < medicos.size()) {
        return medicos.get(indice);
    } else {
        System.out.println("Selección inválida.");
        return obtenerPersonalMedico(); 
    }
       
    }

private static Empleado obtenerPersonalEnfermeria() {
    System.out.println("Seleccione un enfermero/a de la lista:");
    List<Empleado> enfermeros = empleados.stream()
                                         .filter(e -> e instanceof PersonalEnfermeria)
                                         .collect(Collectors.toList());

    if (enfermeros.isEmpty()) {
        System.out.println("No hay personal de enfermería disponible.");
        return null;
    }

    for (int i = 0; i < enfermeros.size(); i++) {
        PersonalEnfermeria enfermero = (PersonalEnfermeria) enfermeros.get(i);
        System.out.println((i + 1) + ". " + enfermero.getNombre() + enfermero.getTurno());
    }

    System.out.println("Ingrese el número del enfermero/a:");
    int indice = scanner.nextInt() - 1;
    scanner.nextLine(); 

    if (indice >= 0 && indice < enfermeros.size()) {
        return enfermeros.get(indice);
    } else {
        System.out.println("Selección inválida.");
        return obtenerPersonalEnfermeria(); 
    }
}
    
 public static void darCitaPruebaMedica() {
     System.out.println("Seleccione un paciente por ID:");
        for (Paciente paciente : pacientes) {
            System.out.println("ID: " + paciente.getId() + ", Nombre: " + paciente.getNombre() + ", Edad: " + paciente.getEdad());
        }
        String idPaciente = scanner.nextLine();
        Paciente pacienteSeleccionado = buscarPacientePorId(idPaciente);

        if (pacienteSeleccionado == null) {
            System.out.println("Paciente no encontrado. Intente de nuevo.");
            return;
        }
     
     
        System.out.println("Seleccione el tipo de prueba médica:");
        for (PruebasMedicas.TipoPrueba tipo : PruebasMedicas.TipoPrueba.values()) {
            System.out.println((tipo.ordinal() + 1) + ". " + tipo);
        }
        int seleccionTipo = scanner.nextInt();
        scanner.nextLine(); 
        PruebasMedicas.TipoPrueba tipoPrueba = PruebasMedicas.TipoPrueba.values()[seleccionTipo - 1];

        System.out.println("Fechas disponibles para " + tipoPrueba + ":");
        List<LocalDateTime> fechas = citasDisponibles.get(tipoPrueba);
        for (int i = 0; i < fechas.size(); i++) {
            System.out.println((i + 1) + ". " + fechas.get(i));
        }

        System.out.println("Seleccione una fecha:");
        int seleccionFecha = scanner.nextInt();
        scanner.nextLine(); 

        LocalDateTime fechaSeleccionada = fechas.get(seleccionFecha - 1);
        fechas.remove(seleccionFecha - 1); // Eliminar la fecha seleccionada de la lista

        System.out.println("Cita para " + tipoPrueba + " programada para: " + fechaSeleccionada);
    }
    
    private static Paciente buscarPacientePorId(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    
    static {
        // Inicializar las listas de citas disponibles para cada tipo de prueba
        for (PruebasMedicas.TipoPrueba tipo : PruebasMedicas.TipoPrueba.values()) {
            List<LocalDateTime> fechas = new ArrayList<>();

            fechas.add(LocalDateTime.of(2024, 6, 1, 9, 0));
            fechas.add(LocalDateTime.of(2024, 6, 1, 11, 0));
            fechas.add(LocalDateTime.of(2024, 6, 2, 9, 0));
            fechas.add(LocalDateTime.of(2024, 6, 2, 11, 0));
            citasDisponibles.put(tipo, fechas);
        }
    }
    //END-OPCION7

    //OPCION 8-CONSULTAR/ACTUALIZAR CALENDARIO M/E
  private static void verAgendaEmpleado() {
    System.out.println("Seleccione el tipo de empleado para ver la agenda:");
    System.out.println("1. Personal médico");
    System.out.println("2. Personal de enfermería");
    int tipoEmpleado = scanner.nextInt();
    scanner.nextLine();
    Empleado empleadoSeleccionado = null;
    if (tipoEmpleado == 1) {
        empleadoSeleccionado = obtenerPersonalMedico();
    } else if (tipoEmpleado == 2) {
        empleadoSeleccionado = obtenerPersonalEnfermeria();
    } else {
        System.out.println("Tipo de empleado no válido.");
        return;
    }

    if (empleadoSeleccionado == null) {
        System.out.println("No se ha seleccionado ningún empleado.");
        return;
    }

    List<CitaSanitaria> citas = empleadoSeleccionado.getAgenda().obtenerTodasLasCitas();

    if (citas.isEmpty()) {
        System.out.println("---- " + empleadoSeleccionado.getNombre());
        System.out.println("No hay citas programadas ");
    } else {
        System.out.println("----- " + empleadoSeleccionado.getNombre() + ":");
        for (CitaSanitaria cita : citas) {
            System.out.println("---Citas programadas---");
            System.out.println("Fecha: " + cita.getFecha() + ", Paciente: " + cita.getPaciente().getNombre() + ", Turno: " + cita.getTurno());
        }
    }
}

//END-OPCION8
    
//OPCION 9-ALTA/BAJA HOSPITALIZACION

private static void darAltaHospitalizacion() {
    System.out.println("Ingrese el nombre del paciente:");
    scanner.nextLine();  
    String nombre = scanner.nextLine(); 
    System.out.println("Ingrese la edad del paciente:");
    int edad = scanner.nextInt();
    System.out.println("Ingrese el número de habitación:");
    int numeroHabitacion = scanner.nextInt();
    scanner.nextLine();
    
      System.out.println("¿El paciente tiene seguro médico privado? (si/no):");
    String respuestaSeguro = scanner.nextLine();
    boolean tieneSeguroPrivado = respuestaSeguro.equalsIgnoreCase("si");

    String numeroSeguridadSocial = null;
    if (!tieneSeguroPrivado) {
        System.out.println("Introduzca el número de seguridad social del paciente (dejar en blanco si no tiene):");
        numeroSeguridadSocial = scanner.nextLine();
        if (numeroSeguridadSocial.isEmpty()) {
            numeroSeguridadSocial = null;
        }
    }

    
    System.out.println("Ingrese el procedimiento médico:");
    String procedimientoMedico = scanner.nextLine();

    Paciente paciente = new Paciente(nombre, edad, numeroSeguridadSocial, tieneSeguroPrivado);
    boolean Alta = hospitalizacion.darAltaPaciente(paciente, numeroHabitacion, procedimientoMedico);
    if (Alta) {
        System.out.println("Alta de hospitalización realizada con éxito.");
    }
}

private static void darBajaHospitalizacion() {
    System.out.println("Ingrese el número de habitación para dar de baja:");
    int numeroHabitacion = scanner.nextInt();

    boolean Baja = hospitalizacion.darBajaPaciente(numeroHabitacion);
    if (Baja) {
        System.out.println("Baja de hospitalización realizada con éxito.");
    }
}

//END-OPCION 9

//OPCION 10-CREAR Y ASIGNAR CLASES AL PERSONAL 
private static void crearYAsignarClase() {
    System.out.println("...Creando una nueva clase de formación...");

    System.out.print("Ingrese el título de la clase: ");
    String titulo = scanner.nextLine();
    scanner.nextLine();
    
    Empleado instructor = null;
    System.out.println("Seleccione el tipo de instructor: 1. Médico 2. Enfermero/a");
    int tipo = scanner.nextInt();
    scanner.nextLine(); 

    if (tipo == 1) {
        instructor = obtenerPersonalMedico();
    } else if (tipo == 2) {
        instructor = obtenerPersonalEnfermeria();
    }

    if (instructor == null) {
        System.out.println("No se pudo obtener un instructor válido.");
        return;
    }

    String turnoInstructor = instructor.getTurno().toString();
    String turnoClase;

    if (turnoInstructor.equalsIgnoreCase("MAÑANA")) {
        turnoClase = "Tarde";
    } else if (turnoInstructor.equalsIgnoreCase("TARDE")) {
        turnoClase = "Mañana";
    } else {
        System.out.println("El turno del instructor no es válido.");
        return;
    }

    // Solicitar fecha de la clase
    System.out.println("Seleccione el día para la cita:");
    System.out.println("1: Lunes 3 de junio del 2024");
    System.out.println("2: Martes 4 de junio");
    System.out.println("3: Miércoles 5 de junio");
    System.out.println("4: Jueves 6 de junio");
    System.out.println("5: Lunes 10 de junio del 2024");
    System.out.println("6: Martes 11 de junio");
    System.out.println("7: Miércoles 12 de junio");
    System.out.println("8: Jueves 13 de junio");
    System.out.println("9: Lunes 17 de junio del 2024");
    System.out.println("10: Martes 18 de junio");
    System.out.println("11: Miércoles 19 de junio");
    System.out.println("12: Jueves 20 de junio");
    int opcionFecha = scanner.nextInt();
    scanner.nextLine(); 

    String fechaSeleccionada;
    switch (opcionFecha) {
        case 1:
            fechaSeleccionada = "Lunes 3 de junio del 2024";
            break;
        case 2:
            fechaSeleccionada = "Martes 4 de junio";
            break;
        case 3:
            fechaSeleccionada = "Miércoles 5 de junio";
            break;
        case 4:
            fechaSeleccionada = "Jueves 6 de junio";
            break;
        case 5:
            fechaSeleccionada = "Lunes 10 de junio del 2024";
            break;
        case 6:
            fechaSeleccionada = "Martes 11 de junio";
            break;
        case 7:
            fechaSeleccionada = "Miércoles 12 de junio";
            break;
        case 8:
            fechaSeleccionada = "Jueves 13 de junio";
            break;
        case 9:
            fechaSeleccionada = "Lunes 17 de junio del 2024";
            break;
        case 10:
            fechaSeleccionada = "Martes 18 de junio";
            break;
        case 11:
            fechaSeleccionada = "Miércoles 19 de junio";
            break;
        case 12:
            fechaSeleccionada = "Jueves 20 de junio";
            break;
        default:
            System.out.println("Selección inválida. Por favor, seleccione una fecha válida.");
            return;
    }
    
    // Mostrar opciones de hora según el turno de la clase
    System.out.println("Seleccione la hora de la clase:");
    switch (turnoClase) {
        case "Tarde":
            System.out.println("1. 13:00\n2. 15:00\n3. 17:00\n4. 19:00");
            break;
        case "Mañana":
            System.out.println("1. 09:00\n2. 11:00");
            break;
        default:
            System.out.println("Turno de clase no definido.");
            return;
    }

    int opcionHora = scanner.nextInt();
    scanner.nextLine(); 
    String horaClase;

    switch (turnoClase) {
        case "Tarde":
            switch (opcionHora) {
                case 1:
                    horaClase = "13:00";
                    break;
                case 2:
                    horaClase = "15:00";
                    break;
                case 3:
                    horaClase = "17:00";
                    break;
                case 4:
                    horaClase = "19:00";
                    break;
                default:
                    System.out.println("Opción de hora no válida.");
                    return;
            }
            break;
        case "Mañana":
            switch (opcionHora) {
                case 1:
                    horaClase = "09:00";
                    break;
                case 2:
                    horaClase = "11:00";
                    break;
                default:
                    System.out.println("Opción de hora no válida.");
                    return;
            }
            break;
        default:
            horaClase = "";
            break;
    }

    ClaseFormacion nuevaClase = new ClaseFormacion(titulo, instructor);
    nuevaClase.setFecha(fechaSeleccionada);
    nuevaClase.setHora(horaClase);
    unidadFormacion.agregarClaseFormacion(nuevaClase);

    System.out.println("---Clases en Unidad Formativa---");
    System.out.println("ID: " + nuevaClase.getId());
    System.out.println("Día: " + fechaSeleccionada  );
    System.out.println("Hora:" + horaClase);
    System.out.println("Turno: " + turnoClase  );
    System.out.println("Clase creada y asignada con éxito");
}
//END-OPCION 10
   
}