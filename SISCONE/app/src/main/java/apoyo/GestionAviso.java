package apoyo;

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
public class GestionAviso {

    public static void enviarAvisos(String idCurso,String titulo){

        String idAviso = buscarAviso(idCurso,titulo);
        List<String> cedulas = buscarRepresentantes(idCurso);
        List<String> buzones = buscarBuzones(cedulas);

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();
        parametros.put("id_aviso", idAviso);

        for (String buzon : buzones) {
            parametros.put("id_buzon", buzon);
            try {
                Statement sentencia = conexion.createStatement();
                ResultSet resultado = sentencia.executeQuery(
                        Sentencias.registrar("Lista_Avisos", parametros));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static String buscarAviso(String idCurso,String titulo) {

        String idAviso = null;
        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("id_curso", idCurso);
        condiciones.put("titulo", titulo);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("aviso", null, condiciones));
            while(resultado.next()) {
                idAviso = resultado.getString("id_Aviso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idAviso;
    }

    private static List<String> buscarRepresentantes(String idCurso) {

        List<String> cedulas = new ArrayList<>();
        ResultSet resultado = null;
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("id_curso", idCurso);
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(
                    Sentencias.consultar("curso_representantes", null, condiciones));
            while(resultado.next()) {
                cedulas.add(resultado.getString("cedula_representante"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cedulas;
    }

    private static List<String> buscarBuzones(List<String> cedulas){

        List<String> buzones = new ArrayList<>();
        ResultSet resultado = null;

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();

        for (String cedula : cedulas) {
            condiciones.put("cedula_representante", cedula);
            try {
                Statement sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(
                        Sentencias.consultar("buzon", null, condiciones));
                while(resultado.next()) {
                    buzones.add(resultado.getString("id_buzon"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return buzones;
    }

}
