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
 * Created by user on 24/01/2016.
 */
public class Estudiante {

    private String idCurso;
    private String cedulaRepresentante;
    private String nombreEstudiante;
    private String apellidoEstudiante;

    public Estudiante() {
    }

    public Estudiante(String idCurso, String cedulaRepresentante,
                      String nombreEstudiante, String apellidoEstudiante) {
        this.idCurso = idCurso;
        this.cedulaRepresentante = cedulaRepresentante;
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public void registrarAlumno() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("id_curso", this.idCurso);
        parametros.put("cedula_representante", this.cedulaRepresentante);
        parametros.put("nombre_estudiante", this.nombreEstudiante);
        parametros.put("apellido_estudiante", this.apellidoEstudiante);

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("estudiante", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarRepresentante() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("id_curso", this.idCurso);
        parametros.put("cedula_representante", this.cedulaRepresentante);
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("curso_representantes", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
