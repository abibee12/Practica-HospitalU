import java.time.LocalDate;

public class Paciente {
    private String id;
    private String nombre;
    private int edad;
    private String procedimientoMedico;
    private static int contadorId= 0; 
    private LocalDate fechaEmision;
    private double montoTotal;
    private String numeroFactura;
    private String numeroSeguridadSocial;
    private boolean tieneSeguroPrivado;


    public Paciente(String nombre, int edad, String numeroSeguridadSocial, boolean tieneSeguroPrivado) {
        this.id= "PAC" + (++contadorId); 
        this.nombre = nombre;
        this.edad = edad;
        
        this.fechaEmision = LocalDate.now(); 
        this.montoTotal = 0.0;
        this.numeroFactura = "FACT" + contadorId; 
         this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.tieneSeguroPrivado = tieneSeguroPrivado;
    }
    
    public String getId(){
        return id; 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

   

public String getProcedimientoMedico() {
    return procedimientoMedico;
}

public void setProcedimientoMedico(String procedimientoMedico) {
    this.procedimientoMedico = procedimientoMedico;
}
  

   public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }

    public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    public boolean tieneSeguroPrivado() {
        return tieneSeguroPrivado;
    }

    public void setTieneSeguroPrivado(boolean tieneSeguroPrivado) {
        this.tieneSeguroPrivado = tieneSeguroPrivado;
    }
    
//HAY QUE AGREGAR METODOS Y ATRIBUTOS PARA SEGURIDAD SOCIAL Y SEGURO MEDICO PRIVADO
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }
    
     public void imprimirFactura() {
    System.out.println("Número de Factura: " + getNumeroFactura());
    System.out.println("Fecha de Emisión: " + getFechaEmision());
    System.out.println("Nombre del Paciente: " + getNombre());
    System.out.println("Procedimiento Médico: " + getProcedimientoMedico());
    System.out.println("Monto Total: $" + getMontoTotal());
}
}