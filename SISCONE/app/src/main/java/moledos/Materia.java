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
public class Materia {

    private String nombreMateria;
    private String idCurso;

    public Materia() {
    }

    public Materia(String nombreMateria, String idCurso) {
        this.nombreMateria = nombreMateria;
        this.idCurso = idCurso;
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
}
