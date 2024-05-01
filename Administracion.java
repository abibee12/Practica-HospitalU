public class Administracion {
    private Direccion direccion;
    private DocumentacionClinica documentacionClinica;
    private Contabilidad contabilidad;
    private RecursosHumanos recursosHumanos;
    private Mantenimiento mantenimiento;
    private Limpieza limpieza;
    private Seguridad seguridad;

    public Administracion() {
        this.direccion = new Direccion();
        this.documentacionClinica = new DocumentacionClinica();
        this.contabilidad = new Contabilidad();
        this.recursosHumanos = new RecursosHumanos();
        this.mantenimiento = new Mantenimiento();
        this.limpieza = new Limpieza();
        this.seguridad = new Seguridad();
    }

    // Getters y setters para cada servicio

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public DocumentacionClinica getDocumentacionClinica() {
        return documentacionClinica;
    }

    public void setDocumentacionClinica(DocumentacionClinica documentacionClinica) {
        this.documentacionClinica = documentacionClinica;
    }

   
}


class Direccion { /* ... */ }
class DocumentacionClinica { /* ... */ }
class Contabilidad { /* ... */ }
class RecursosHumanos { /* ... */ }
class Mantenimiento { /* ... */ }
class Limpieza { /* ... */ }
class Seguridad { /* ... */ }