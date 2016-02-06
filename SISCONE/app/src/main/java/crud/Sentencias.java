package crud;

import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 17/01/2016.
 */
public class Sentencias {

    public static String consultar(String tabla,List<String> parametros,Map<String, String> condiciones){
        StringBuilder sentencia = new StringBuilder();

        if(parametros == null || parametros.isEmpty())
            sentencia.append("select * from ").append(tabla);
        else{
            sentencia.append("select ");
            for (String parametro : parametros){
                sentencia.append(parametro).append(", ");
            }
            sentencia.deleteCharAt(sentencia.length()-2);
            sentencia.append("from ").append(tabla);
        }

        if (condiciones == null || condiciones.isEmpty()){
            Log.i("sentencia", sentencia.toString());
            return sentencia.toString();
        }
        else{
            sentencia.append(" where ");
            for (String  condicion : condiciones.keySet()) {
                sentencia.append(condicion).append("='").
                        append(condiciones.get(condicion)).append("'");
                sentencia.append(" and ");
            }
        }
        sentencia.delete(sentencia.length()-5,sentencia.length());
        Log.i("sentencia", sentencia.toString());
        return sentencia.toString();
    }

    public static String registrar(String tabla,Map<String, String> parametros){
        StringBuilder sentencia = new StringBuilder();

        if(parametros == null || parametros.isEmpty())
            sentencia.append("La operación no ha podido ser realizada");
        else{
            sentencia.append("insert into ").append(tabla).append("(");

            for (String  parametro : parametros.keySet()){
                sentencia.append(parametro).append(", ");
            }
            sentencia.deleteCharAt(sentencia.length() - 2);
            sentencia.append(")");

            sentencia.append(" values (");
            for (String  parametro : parametros.keySet()){
                sentencia.append("'").append(parametros.get(parametro)).append("', ");
            }
        }
        sentencia.deleteCharAt(sentencia.length() - 2);
        sentencia.append(")");
        Log.i("sentencia", sentencia.toString());
        return sentencia.toString();
    }
}
