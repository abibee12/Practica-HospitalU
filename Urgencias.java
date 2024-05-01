import java.util.ArrayList;
import java.util.List;


public class Urgencias {
    private List<Pacientes> pacientesEnEspera;
    private List<Pacientes> pacientesAtendidos;

    public Urgencias() {
        pacientesEnEspera = new ArrayList<>();
        pacientesAtendidos = new ArrayList<>();
    }

    public void recibirPaciente(Pacientes paciente) {
        pacientesEnEspera.add(paciente);
        System.out.println("El paciente " + paciente.getNombre() + " ha sido recibido en Urgencias.");
    }

    public void atenderPaciente() {
        if (!pacientesEnEspera.isEmpty()) {
            Pacientes pacienteAtender = pacientesEnEspera.remove(0);
            realizarEvaluacionInicial(pacienteAtender);
            realizarTratamiento(pacienteAtender);

            if (pacienteAtender.getEstado() == EstadoPaciente.ALTA) {
                System.out.println("El paciente " + pacienteAtender.getNombre() + " ha sido dado de alta y puede volver a casa.");
                pacientesAtendidos.add(pacienteAtender);
            } else {
                System.out.println("El paciente " + pacienteAtender.getNombre() + " ha sido ingresado para un tratamiento más inmediato.");
                // Aquí se podría redirigir al paciente a la unidad de hospitalización
            }
        } else {
            System.out.println("No hay pacientes en espera en este momento.");
        }
    }

    private void realizarEvaluacionInicial(Pacientes paciente) {
        System.out.println("Realizando evaluación inicial del paciente " + paciente.getNombre() + "...");
        // Aquí se realizaría la evaluación inicial del paciente
        paciente.setEstado(EstadoPaciente.EN_TRATAMIENTO);
    }

    private void realizarTratamiento(Pacientes paciente) {
        System.out.println("Realizando tratamiento del paciente " + paciente.getNombre() + "...");
        // Aquí se realizaría el tratamiento del paciente
        paciente.setEstado(EstadoPaciente.ALTA);
    }

    
}