package moledos;

import java.io.Serializable;
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
public class Persona  implements Serializable{

    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String tipo;

    public Persona() {
    }

    public Persona(String cedula, String nombre, String apellido, String correo, String contrasena) {
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

    public void registrarPersona(String tabla) {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("cedula_"+tabla, this.cedula);
        parametros.put("nombre_"+tabla, this.nombre);
        parametros.put("apellido_"+tabla, this.apellido);
        parametros.put("correo_"+tabla, this.correo);
        parametros.put("contrasena_"+tabla, this.contrasena);

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar(tabla, parametros));
            if(tabla.equals("Representante")){
                registrarBuzon();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet buscarCursoProfesor(String cedulaProfesor) {

        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("cedula_Profesor", cedulaProfesor);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("curso", null, condiciones));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public ResultSet buscarPersona(String tabla,String cedula) {

        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        if(tabla.equals("Profesor")) {
            condiciones.put("cedula_Profesor", cedula);
        }
        else{
            condiciones.put("cedula_Representante", cedula);
        }
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar(tabla, null, condiciones));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public void registrarBuzon() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("cedula_Representante", this.cedula);
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("Buzon", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet buscarRepresentateInscrito(String idCurso,String cedulaRepresentante) {

        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("id_curso", idCurso);
        condiciones.put("cedula_representante", cedulaRepresentante);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("curso_representantes", null, condiciones));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }


    public ResultSet actualizarDatos(String tabla) {

        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();
        parametros.put("correo_"+tabla, this.correo);
        parametros.put("contrasena_"+tabla, this.contrasena);

        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("cedula_"+tabla, this.cedula);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.actualizar(tabla, parametros, condiciones));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;

    }



}
