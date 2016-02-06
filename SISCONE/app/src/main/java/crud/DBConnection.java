package crud;

import android.util.Log;

import java.sql.Connection;
import net.sourceforge.jtds.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by user on 13/01/2016.
 */
public class DBConnection {


    //private static  final String URL = "jdbc:jtds:sqlserver://192.168.94.2:1433/SISCONE";
    private static  final String URL = "jdbc:jtds:sqlserver://172.31.104.16:1433/SISCONE";
    private static  final String PASSSWORD = "sa";
    private static  final String USER = "sa";
    private static Connection connection = null;
    private static DBConnection instace = null;

    private DBConnection() {
    }

    public static DBConnection getInstace() {
        if (instace == null) {
            instace = new DBConnection();
        }
        return instace;
    }

    public Connection getConnection(){
        if(connection == null){
            connection = conectar();
        }
        return connection;
    }

    private Connection conectar(){

        Connection conexion = null;
        try {
            (new Driver()).getClass();
            conexion = DriverManager.getConnection(URL, USER, PASSSWORD);
            Log.i("conexion", "La conexion se establecio");
        } catch (SQLException e) {
            Log.i("conexion", "Fallo la conexion");
        }
        return conexion;
    }


}
