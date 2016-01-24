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
 * Created by user on 13/01/2016.
 */
public class Curso {


    private Integer idCurso;
    private Integer cedulaProfesor;
    private String nombreInstitución;
    private String nombreCusro;
    private String nombreParalelo;

    public Curso() {
    }

    public Curso(String nombreInstitución, String nombreCusro,
                 String nombreParalelo, Integer cedulaProfesor) {
        this.nombreInstitución = nombreInstitución;
        this.nombreCusro = nombreCusro;
        this.nombreParalelo = nombreParalelo;
        this.cedulaProfesor = cedulaProfesor;
    }

    public Curso(Integer idCurso, Integer cedulaProfesor,
                 String nombreInstitución, String nombreCusro,
                 String nombreParalelo) {
        this.idCurso = idCurso;
        this.cedulaProfesor = cedulaProfesor;
        this.nombreInstitución = nombreInstitución;
        this.nombreCusro = nombreCusro;
        this.nombreParalelo = nombreParalelo;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getCedulaProfesor() {
        return cedulaProfesor;
    }

    public void setCedulaProfesor(Integer cedulaProfesor) {
        this.cedulaProfesor = cedulaProfesor;
    }

    public String getNombreInstitución() {
        return nombreInstitución;
    }

    public void setNombreInstitución(String nombreInstitución) {
        this.nombreInstitución = nombreInstitución;
    }

    public String getNombreCusro() {
        return nombreCusro;
    }

    public void setNombreCusro(String nombreCusros) {
        this.nombreCusro = nombreCusros;
    }

    public String getNombreParalelo() {
        return nombreParalelo;
    }

    public void setNombreParalelo(String nombreParalelo) {
        this.nombreParalelo = nombreParalelo;
    }

    public void registrarCurso() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map <String,String> parametros = new HashMap<>();

        parametros.put("cedula_Profesor",this.cedulaProfesor.toString());
        parametros.put("nombre_Institucion",this.nombreInstitución);
        parametros.put("nombre_Curso",this.nombreCusro);
        parametros.put("paralelo",this.nombreParalelo);

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("curso", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
