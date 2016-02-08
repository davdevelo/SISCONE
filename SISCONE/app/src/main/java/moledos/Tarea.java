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
 * Created by MVW7 on 08/02/2016.
 */
public class Tarea {

    private String idTarea;
    private String idMateria;
    private String nombreTarea;
    private String fechaTarea;
    private String descripcionMateria;

    public Tarea() {
    }

    public Tarea(String idTarea, String idMateria, String nombreTarea,
                 String fechaTarea, String descripcionMateria) {
        this.idTarea = idTarea;
        this.idMateria = idMateria;
        this.nombreTarea = nombreTarea;
        this.fechaTarea = fechaTarea;
        this.descripcionMateria = descripcionMateria;
    }

    public Tarea(String idMateria, String nombreTarea,
                 String fechaTarea, String descripcionMateria) {
        this.idMateria = idMateria;
        this.nombreTarea = nombreTarea;
        this.fechaTarea = fechaTarea;
        this.descripcionMateria = descripcionMateria;
    }

    public void registrarTarea() {

        Connection conexion = DBConnection.getInstace().getConnection();
        Map<String, String> parametros = new HashMap<>();

        parametros.put("id_materia", this.idMateria);
        parametros.put("nombre_tarea", this.nombreTarea);
        parametros.put("fecha_tarea", this.fechaTarea);
        parametros.put("descripcion_tarea", this.descripcionMateria);

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(
                    Sentencias.registrar("tarea", parametros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
