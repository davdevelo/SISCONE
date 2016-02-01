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
 * Created by MVW7 on 31/01/2016.
 */
public class Aviso {

    String idCurso;
    String titulo;
    String descripcionAviso;

    public Aviso() {
    }

    public Aviso(String idCurso, String titulo, String descripcionAviso) {
        this.idCurso = idCurso;
        this.titulo = titulo;
        this.descripcionAviso = descripcionAviso;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionAviso() {
        return descripcionAviso;
    }

    public void setDescripcionAviso(String descripcionAviso) {
        this.descripcionAviso = descripcionAviso;
    }

    public void registrarAviso() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("id_curso", this.idCurso);
        parametros.put("titulo", this.titulo);
        parametros.put("descripcion_aviso", this.descripcionAviso);

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("Aviso", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet buscarAvisosBuzon(String idBuzon) {

        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("id_buzon", idBuzon);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("Lista_Avisos", null, condiciones));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

}
