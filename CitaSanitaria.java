import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

class CitaSanitaria {
    private Empleado empleado;
    private Paciente paciente;
    private Turno turno;
    private Date fecha; 
    private Especialidad especialidad;
    
    
    public CitaSanitaria(Empleado empleado, Paciente paciente, Turno turno, Date fecha) {
        this.empleado = empleado;
        this.paciente = paciente;
        this.turno = turno;
        this.fecha = fecha; 
    }

    
    public Empleado getEmpleado() { 
        return empleado;
    }

    
    
    public Paciente getPaciente() {
        return paciente;
    }

    public Turno getTurno() {
        return turno;
    }

    public Date getFecha(){
        return fecha; 
    }
    
    public Especialidad getEspecialidadDeMedico() {
        if (empleado instanceof PersonalMedico) {
            return ((PersonalMedico) empleado).getEspecialidad();
        }
        return null; 
    }
}


