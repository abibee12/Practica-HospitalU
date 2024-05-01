import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultasExternas {
    private List<CitaSanitaria> citas;

    public ConsultasExternas() {
        this.citas = new ArrayList<>();
    }


public void agregarCita(CitaSanitaria cita) {
    citas.add(cita);
    System.out.println("Cita agregada: " + cita.getPaciente().getNombre() + " con " + cita.getEmpleado().getNombre() + " el " + cita.getFecha());
}


  public void mostrarTodasLasCitas() {
    if (citas.isEmpty()) {
        System.out.println("No hay citas programadas.");
    } else {
        System.out.println("Listado de todas las citas:");
        for (CitaSanitaria cita : citas) {
            Especialidad empEspecialidad = cita.getEspecialidadDeMedico();
            String tipoPersonal = cita.getEmpleado().getClass().getSimpleName();
            System.out.println("Fecha: " + cita.getFecha() + ", Paciente: " + cita.getPaciente().getNombre() + ", Especialidad: " + (empEspecialidad != null ? empEspecialidad : "No Aplica") + ", Tipo de Personal: " + tipoPersonal);
        }
    }
}

}