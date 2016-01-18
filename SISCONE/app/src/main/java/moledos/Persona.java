package moledos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import crud.DBConnection;
import crud.Sentencias;

/**
 * Created by DANIEL on 02/12/2015.
 */
public class Persona {

    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String tipo;

    public Persona() {
    }

    public Persona(String cedula, String nombre, String apellido, String correo, String contrasena, String tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void registrarProfesor() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("cedula_profesor", this.cedula);
        parametros.put("nombre_profesor", this.nombre);
        parametros.put("apellido_profesor", this.apellido);
        parametros.put("correo_profesor", this.correo);
        parametros.put("contrasena_profesor", this.contrasena);

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("profesor", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet buscarCusroProfesor(Integer cedulaProfesor) {

        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("cedula_Profesor", cedulaProfesor.toString());
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("curso", null, condiciones));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public ResultSet buscarProfesor(String cedulaProfesor) {

        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("cedula_Profesor", cedulaProfesor);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("profesor", null, condiciones));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }


}
