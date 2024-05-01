import java.util.ArrayList;
import java.util.List;

public class UnidadesMedicasEspecializadas {
    private List<UnidadEspecializada> unidades;

    public UnidadesMedicasEspecializadas() {
        unidades = new ArrayList<>();
        crearUnidadesEspecializadas();
    }

    private void crearUnidadesEspecializadas() {
        unidades.add(new UnidadEspecializada("Enfermedades Cardiovasculares"));
        unidades.add(new UnidadEspecializada("Diabetes"));
    }

    public void atenderPaciente(Pacientes paciente, String nombreUnidad) {
        for (UnidadEspecializada unidad : unidades) {
            if (unidad.getNombre().equals(nombreUnidad)) {
                unidad.atenderPaciente(paciente);
                break;
            }
        }
    }

    public List<UnidadEspecializada> getUnidades() {
        return unidades;
    }

    private static class UnidadEspecializada {
        private String nombre;
        private List<Pacientes> pacientes;

        public UnidadEspecializada(String nombre) {
            this.nombre = nombre;
            this.pacientes = new ArrayList<>();
        }

        public void atenderPaciente(Pacientes paciente) {
            pacientes.add(paciente);
            System.out.println("El paciente " + paciente.getNombre() + " ha sido atendido en la unidad de " + nombre);
        }

        public String getNombre() {
            return nombre;
        }

        public List<Pacientes> getPacientes() {
            return pacientes;
        }
    }
}