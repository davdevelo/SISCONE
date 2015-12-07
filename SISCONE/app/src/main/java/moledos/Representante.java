package moledos;

import java.util.List;

/**
 * Created by user on 06/12/2015.
 */
public class Representante extends Persona {

    private List<String> alumnos;

    public Representante(){}

    public Representante(String cedula, String nombre, String apellido, String correo, String contrasena, String tipo, List<String> alumnos) {
        super(cedula, nombre, apellido, correo, contrasena, tipo);
        this.alumnos = alumnos;
    }

    public List<String> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<String> alumnos) {
        this.alumnos = alumnos;
    }
}
