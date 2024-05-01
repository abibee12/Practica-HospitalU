class Personas {
    private String nombre;
    private int edad;
    private String expediente;

    public Personas(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.expediente = "";
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

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }
}