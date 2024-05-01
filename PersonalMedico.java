class PersonalMedico extends Empleado {
  private Especialidad especialidad;
  private static int contadorId= 0; 
  private String id;
    

    public PersonalMedico(String nombre, int edad, Unidad unidad, Especialidad especialidad, Turno turno) {
        super(nombre, edad, unidad, turno);
        this.id= "MED" + (++contadorId); 
        this.especialidad= especialidad;
             
    }

    public String getId(){
        return id; 
    }
    
    public Especialidad getEspecialidad() {
        return especialidad;
    }
    
    
  
}