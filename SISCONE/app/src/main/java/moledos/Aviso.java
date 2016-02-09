package moledos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import crud.DBConnection;
import crud.Sentencias;

/**
 * Created by MVW7 on 31/01/2016.
 */
public class Aviso implements Serializable {

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


    public List<Aviso> buscarAvisos(String cedula) {

        ResultSet resultado = null;
        Aviso aviso;

        List<Aviso> avisos = new ArrayList<>();
        List<String> idAvisos = buscarAvisosBuzon(cedula);

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();

        for (String id:idAvisos) {

            condiciones.put("id_aviso",id);
            try {
                Statement sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(
                        Sentencias.consultar("aviso", null, condiciones) + "order by id_aviso desc");
                while (resultado.next()) {
                    aviso = new Aviso(resultado.getString("id_curso"),
                            resultado.getString("titulo"),
                            resultado.getString("descripcion_aviso"));
                    avisos.add(aviso);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return avisos;
    }

    private List<String> buscarAvisosBuzon(String cedula) {
        List<String> avisos = new ArrayList<>();
        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("id_buzon", buscarBuzones(cedula));
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("Lista_Avisos", null, condiciones)+ "order by id_aviso desc");
            while (resultado.next()) {
                avisos.add(resultado.getString("id_aviso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avisos;
    }

    private String buscarBuzones(String cedula) {
        ResultSet resultado = null;
        String idBuzon = "";
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();

        condiciones.put("cedula_representante", cedula);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("buzon", null, condiciones));
            while (resultado.next()) {
                idBuzon = resultado.getString("id_buzon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idBuzon;
    }

}
