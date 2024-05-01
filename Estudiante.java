import java.util.ArrayList; 
import java.util.List;


class Estudiante extends Personas {
    //private String unidad;
    //private String tutor;
    private String id;
    private static int contadorId= 0; 
    private List<ClaseFormacion> clases;
    private List<CitaEstudiante> citas;    

    public Estudiante(String nombre, int edad) {
        super(nombre, edad);
        this.id= "EST" + (++contadorId); 
        //this.unidad = unidad;
        //this.tutor = tutor;
        this.clases= new ArrayList();
        this.citas = new ArrayList();
    }

    public String getId(){
        return id; 
    }
    
    public void agregarClase(ClaseFormacion clase){
    clases.add(clase);
    }
    
    public void agregarCita(CitaEstudiante cita) {
        this.citas.add(cita);
    }

}