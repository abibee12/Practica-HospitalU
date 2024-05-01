import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cafeteria {
    private List<Producto> menuComidas;
    private List<Producto> menuBebidas;
    private boolean estaAbierta;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public Cafeteria() {
        menuComidas = new ArrayList<>();
        menuBebidas = new ArrayList<>();
        estaAbierta = true;
        horaApertura = LocalTime.of(0, 0); 
        horaCierre = LocalTime.of(23, 59); 
        inicializarMenu();
    }

    private void inicializarMenu() {
        
        menuComidas.add(new Producto("Ensalada César", 5.99));
        menuComidas.add(new Producto("Hamburguesa con papas fritas", 8.99));
        menuComidas.add(new Producto("Pasta con salsa de tomate", 7.99));

        
        menuBebidas.add(new Producto("Agua mineral", 1.99));
        menuBebidas.add(new Producto("Refresco", 2.49));
        menuBebidas.add(new Producto("Café", 1.99));
    }

    public void mostrarMenuComidas() {
        if (estaAbierta) {
            System.out.println("Menú de Comidas:");
            for (Producto producto : menuComidas) {
                System.out.println("- " + producto.getNombre() + ": $" + producto.getPrecio());
            }
        } else {
            System.out.println("La cafetería está cerrada en este momento.");
        }
    }

    public void mostrarMenuBebidas() {
        if (estaAbierta) {
            System.out.println("Menú de Bebidas:");
            for (Producto producto : menuBebidas) {
                System.out.println("- " + producto.getNombre() + ": $" + producto.getPrecio());
            }
        } else {
            System.out.println("La cafetería está cerrada en este momento.");
        }
    }

    public boolean estaAbierta() {
        LocalTime horaActual = LocalTime.now();
        return horaActual.isAfter(horaApertura) && horaActual.isBefore(horaCierre);
    }

    private static class Producto {
        private String nombre;
        private double precio;

        public Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }
    }
}

  

