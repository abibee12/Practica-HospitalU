import java.util.ArrayList;
import java.util.List;

public class Aparcamiento {
    private int numNiveles;
    private int capacidadPorNivel;
    private List<Nivel> niveles;
    private List<Empleado> empleados;

    public Aparcamiento(int numNiveles, int capacidadPorNivel) {
        this.numNiveles = numNiveles;
        this.capacidadPorNivel = capacidadPorNivel;
        this.niveles = new ArrayList<>();
        this.empleados = new ArrayList<>();

        // Inicializar los niveles del aparcamiento
        for (int i = 1; i <= numNiveles; i++) {
            niveles.add(new Nivel(i, capacidadPorNivel));
        }
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void mostrarEstadoAparcamiento() {
        System.out.println("Estado del Aparcamiento:");
        for (Nivel nivel : niveles) {
            System.out.println("Nivel " + nivel.getNumero() + ": " + nivel.getPlazasOcupadas() + "/" + nivel.getCapacidad() + " plazas ocupadas");
        }
    }

    public void resolverIncidencia(String descripcion) {
        System.out.println("Incidencia reportada: " + descripcion);
        System.out.println("Asignando empleado para resolver la incidencia...");
        if (!empleados.isEmpty()) {
            Empleado empleadoAsignado = empleados.get(0);
            System.out.println("Empleado asignado: " + empleadoAsignado.getNombre());
          
        } else {
            System.out.println("No hay empleados disponibles en este momento.");
        }
    }

    private static class Nivel {
        private int numero;
        private int capacidad;
        private int plazasOcupadas;

        public Nivel(int numero, int capacidad) {
            this.numero = numero;
            this.capacidad = capacidad;
            this.plazasOcupadas = 0;
        }

        public int getNumero() {
            return numero;
        }

        public int getCapacidad() {
            return capacidad;
        }

        public int getPlazasOcupadas() {
            return plazasOcupadas;
        }

        public void ocuparPlaza() {
            if (plazasOcupadas < capacidad) {
                plazasOcupadas++;
            } else {
                System.out.println("El nivel " + numero + " está completo.");
            }
        }

        public void liberarPlaza() {
            if (plazasOcupadas > 0) {
                plazasOcupadas--;
            } else {
                System.out.println("No hay plazas ocupadas en el nivel " + numero + ".");
            }
        }
    }

    private static class Empleado {
        private String nombre;

        public Empleado(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }
}