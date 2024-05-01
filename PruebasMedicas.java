import java.time.LocalDateTime;
import java.util.HashSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;


public class PruebasMedicas {
    public enum TipoPrueba {
        ANALITICA,
        RAYOS_X,
        RESONANCIA_MAGNETICA,
        ECOGRAFIA
    }

    private TipoPrueba tipoPrueba;
    private String descripcion;
    private String resultados;
    private LocalDateTime fechaCita; 
    private static HashSet<LocalDateTime> citasProgramadas = new HashSet<>();

    public PruebasMedicas(TipoPrueba tipoPrueba, String descripcion) {
        this.tipoPrueba = tipoPrueba;
        this.descripcion = descripcion;
        this.resultados = "";
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }
    
       public boolean programarCita(LocalDateTime nuevaFecha) {
        if (!citasProgramadas.contains(nuevaFecha)) {
            citasProgramadas.add(nuevaFecha);
            this.fechaCita = nuevaFecha;
            return true;
        } else {
            System.out.println("La fecha y hora seleccionadas ya están ocupadas. Por favor, elija otro momento.");
            return false;
        }
    }
    
    public static boolean esDiaLaborable(LocalDate fecha) {
    DayOfWeek dia = fecha.getDayOfWeek();
    return !(dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY);
}
    
    
    public static HashMap<LocalDate, List<LocalTime>> generarOpcionesDeCitas() {
    LocalDate inicio = LocalDate.of(2024, 6, 1);
    LocalDate fin = LocalDate.of(2024, 6, 30);
    HashMap<LocalDate, List<LocalTime>> citasDisponibles = new HashMap<>();

    while (!inicio.isAfter(fin)) {
        if (esDiaLaborable(inicio)) {
            List<LocalTime> horas = new ArrayList<>();
            // Horas de la mañana
            horas.add(LocalTime.of(8, 0));
            horas.add(LocalTime.of(9, 0));
            horas.add(LocalTime.of(10, 0));
            horas.add(LocalTime.of(11, 0));
            // Horas de la tarde
            horas.add(LocalTime.of(13, 0));
            horas.add(LocalTime.of(14, 0));
            horas.add(LocalTime.of(15, 0));
            horas.add(LocalTime.of(16, 0));
            citasDisponibles.put(inicio, horas);
        }
        inicio = inicio.plusDays(1);
    }
    return citasDisponibles;
}




    @Override
    public String toString() {
        return "Prueba: " + tipoPrueba + "\n" +
               "Descripción: " + descripcion + "\n" +
               "Resultados: " + resultados;
    }
}