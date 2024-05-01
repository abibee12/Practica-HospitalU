import java.util.HashMap;
import java.util.Map;

public class Hospitalizacion {
    private static Map<Integer, Paciente> habitacionesOcupadas;
    private static final int MAX_HABITACIONES = 2;

    public Hospitalizacion() {
        this.habitacionesOcupadas = new HashMap<>();
    }

    public boolean darAltaPaciente(Paciente paciente, int numeroHabitacion, String procedimientoMedico) {
        if (habitacionesOcupadas.size() >= MAX_HABITACIONES) {
            System.out.println("El hospital ha alcanzado su capacidad máxima. No hay habitaciones disponibles.");
            return false;
        } else if (habitacionesOcupadas.containsKey(numeroHabitacion)) {
            System.out.println("La habitación " + numeroHabitacion + " ya está ocupada.");
            return false;
        } else {
            paciente.setProcedimientoMedico(procedimientoMedico);
            habitacionesOcupadas.put(numeroHabitacion, paciente);
            System.out.println("Paciente " + paciente.getNombre() + " ingresado en la habitación " + numeroHabitacion + " para el procedimiento: " + procedimientoMedico);
            return true;
        }
    }

    public boolean darBajaPaciente(int numeroHabitacion) {
        if (habitacionesOcupadas.containsKey(numeroHabitacion)) {
            Paciente paciente = habitacionesOcupadas.remove(numeroHabitacion);
            System.out.println("Paciente " + paciente.getNombre() + " dado de alta de la habitación " + numeroHabitacion);
            return true;
        } else {
            System.out.println("La habitación " + numeroHabitacion + " no está ocupada.");
            return false;
        }
    }
    
     //LISTADO OCUPACION HABITACIONES 
    
     public static void imprimirListadoOcupacion() {
        if (habitacionesOcupadas.isEmpty()) {
            System.out.println("Actualmente no hay pacientes ingresados en el hospital.");
        } else {
            System.out.println("Listado de ocupación de habitaciones:");
            for (Map.Entry<Integer, Paciente> entry : habitacionesOcupadas.entrySet()) {
                int numeroHabitacion = entry.getKey();
                Paciente paciente = entry.getValue();
                System.out.println("Habitación " + numeroHabitacion + ": " + paciente.getNombre());
            }
        }
    }
    
    //ENDLISTADO-OCUPACION-HABITACIONES
}