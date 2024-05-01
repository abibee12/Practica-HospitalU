import java.util.ArrayList;
import java.util.List;

public class UnidadCuidadosIntensivos {
    private List<Pacientes> pacientesAdultos;
    private List<Pacientes> pacientesNinos;
    private List<Pacientes> pacientesNeonatos;

    public UnidadCuidadosIntensivos() {
        pacientesAdultos = new ArrayList<>();
        pacientesNinos = new ArrayList<>();
        pacientesNeonatos = new ArrayList<>();
    }

    public void ingresarPacienteAdulto(Pacientes paciente) {
        pacientesAdultos.add(paciente);
    }

    public void ingresarPacienteNino(Pacientes paciente) {
        pacientesNinos.add(paciente);
    }

    public void ingresarPacienteNeonato(Pacientes paciente) {
        pacientesNeonatos.add(paciente);
    }

    public List<Pacientes> getPacientesAdultos() {
        return pacientesAdultos;
    }

    public List<Pacientes> getPacientesNinos() {
        return pacientesNinos;
    }

    public List<Pacientes> getPacientesNeonatos() {
        return pacientesNeonatos;
    }
}