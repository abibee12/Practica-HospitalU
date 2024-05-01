class PersonalEnfermeria extends Empleado {
    private static int contadorId= 0; 
    private String id;
   
    public PersonalEnfermeria(String nombre, int edad, Unidad unidad, Turno turno) {
        super(nombre, edad, unidad, turno);
        this.id= "ENF" + (++contadorId); 
    }
   
    public String getId(){
        return id; 
    }
}