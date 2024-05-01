public class ClaseFormacion {
    private String id;
    private String titulo;
    private Empleado instructor;
    private static int contadorId= 0;
    private String fecha;  
    private String hora;

    public ClaseFormacion(String titulo, Empleado instructor) {
        this.id= "CLASE" + (++contadorId); 
        this.titulo = titulo;
        this.instructor = instructor;
        this.fecha = "";  
        this.hora = ""; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Empleado getInstructor() {
        return instructor;
    }

    public void setInstructor(Empleado instructor) {
        this.instructor = instructor;
    }
    
       public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}