import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;


public class CitaEstudiante {
    private LocalDateTime fechaHora;
    private Empleado medico;

    public CitaEstudiante(LocalDateTime fechaHora, Empleado medico) {
        this.fechaHora = fechaHora;
        this.medico = medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Empleado getMedico() {
        return medico;
    }

  
}
