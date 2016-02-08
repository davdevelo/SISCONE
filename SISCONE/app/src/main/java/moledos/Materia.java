package moledos;

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
 * Created by user on 24/01/2016.
 */
public class Materia {

    private String nombreMateria;
    private String idCurso;
    private String idMateria;

    public Materia() {
    }

    public Materia(String nombreMateria, String idCurso) {
        this.nombreMateria = nombreMateria;
        this.idCurso = idCurso;
    }

    public Materia(String nombreMateria, String idCurso, String idMateria) {
        this.nombreMateria = nombreMateria;
        this.idCurso = idCurso;
        this.idMateria = idMateria;
    }

    public String getNombreMateria(){
        return this.nombreMateria;
    }

    public String getIdMateria(){
        return this.idMateria;
    }

    public void registrarMateria() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("id_curso", this.idCurso);
        parametros.put("nombre_materia", this.nombreMateria);
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("materia", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Materia> consultarMaterias(String idCurso) {

        List<Materia> materias = new ArrayList<>();
        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> condiciones = new HashMap<>();
        condiciones.put("id_curso", idCurso);
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.consultar("materia", null, condiciones));

            while (resultado.next()) {
                materias.add(new Materia(resultado.getString("nombre_materia"),
                        resultado.getString("id_cuso"),
                        resultado.getString("id_materia")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }
}
