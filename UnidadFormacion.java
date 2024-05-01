import java.util.ArrayList;
import java.util.List;

public class UnidadFormacion {
    private String nombre;
    private List<Estudiante> estudiantes;
    private List<PersonalMedico> personalMedico;
    private List<ClaseFormacion> clasesFormacion;

    public UnidadFormacion(String nombre) {
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
        this.personalMedico = new ArrayList<>();
        this.clasesFormacion = new ArrayList<>();
    }

    // Métodos para agregar y obtener estudiantes
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    // Métodos para agregar y obtener personal médico
    public void agregarPersonalMedico(PersonalMedico medico) {
        personalMedico.add(medico);
    }

    public List<PersonalMedico> getPersonalMedico() {
        return personalMedico;
    }

    // Métodos para agregar y obtener clases de formación
    public void agregarClaseFormacion(ClaseFormacion clase) {
        clasesFormacion.add(clase);
    }

    public List<ClaseFormacion> getClasesDisponibles() {
        return clasesFormacion;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
