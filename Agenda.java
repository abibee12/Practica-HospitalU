import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.text.SimpleDateFormat;



public class Agenda {
    private List<CitaSanitaria> citasSanitarias;

    public Agenda() {
        this.citasSanitarias = new ArrayList<>();
    }

   
    public void agregarCita(CitaSanitaria cita) {
        citasSanitarias.add(cita);
    }

    
      public List<CitaSanitaria> obtenerCitasPorFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaStr = sdf.format(fecha);
        return citasSanitarias.stream()
            .filter(c -> sdf.format(c.getFecha()).equals(fechaStr))
            .collect(Collectors.toList());
    }

    
    public boolean verificarDisponibilidad(Turno turno, Date fecha) {
        for (CitaSanitaria c : citasSanitarias) {
            if (c.getTurno() == turno && c.getFecha().equals(fecha)) {
                return false;
            }
        }
        return true;
    }
    


   
    public List<CitaSanitaria> obtenerCitasPorTurno(Turno turno) {
        return citasSanitarias.stream().filter(c -> c.getTurno() == turno).collect(Collectors.toList());
    }
    
    public List<CitaSanitaria> obtenerTodasLasCitas() {
        return new ArrayList<>(citasSanitarias);
    }
    
}
