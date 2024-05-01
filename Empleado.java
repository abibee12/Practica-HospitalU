abstract class Empleado extends Personas {
    private Unidad unidad;
    private Agenda agenda;
    private Turno turno;
    
    

    public Empleado(String nombre, int edad, Unidad unidad, Turno turno) {
        super(nombre, edad);
        this.unidad = unidad;
        this.agenda = new Agenda();
        this.turno = turno; 
       
        
    }

    public String getNombre() {
        return super.getNombre(); 
    }
    
     public Unidad getUnidad() {
        return unidad;
    }
  
    public Agenda getAgenda() {
        return agenda;
    }
    
     public Turno getTurno(){
        return turno; 
    }
    
    
}