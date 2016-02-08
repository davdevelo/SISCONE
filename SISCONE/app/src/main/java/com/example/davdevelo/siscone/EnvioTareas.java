package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apoyo.GestionAviso;
import moledos.Aviso;
import moledos.Materia;
import moledos.Tarea;

/**
 * Created by davdevelo on 14/12/2015.
 */
public class EnvioTareas extends AppCompatActivity {

    private EditText titulo;
    private EditText descripcion;
    private EditText fechaEntrega;
    private Spinner listaMateria;
    private String curso;
    private StringBuilder cuerpoAviso;
    private EditText elementos[];
    private List<Materia> materias;
    private List<String> nombresMateria;
    private Map<String, Materia> mapaMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_tareas);

        iniciarEntorno();
        elementos = new EditText[]{titulo, descripcion,fechaEntrega};

        findViewById(R.id.buttonRegresarMenuEnvioTareas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(EnvioTareas.this, MenuProfesor.class);
                intent.putExtra("cursoID", curso);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonGuardarTarea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAviso();
            }
        });
    }

    private void iniciarEntorno() {
        recogerParametro();
        buscarElementos();
        ConsultasMaterias consultasMaterias = new ConsultasMaterias(curso);
        consultasMaterias.execute(1);
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        curso = "";
        if (extra != null) {
            curso = (String) extra.get("cursoID");
            Log.i("cursoID", curso);
        }
    }

    private void buscarElementos() {
        titulo = (EditText) findViewById(R.id.editTextTituloDeTarea);
        descripcion = (EditText) findViewById(R.id.editTextDescripcionTarea);
        fechaEntrega = (EditText) findViewById(R.id.editTextFechaEntrega);
        listaMateria = (Spinner) findViewById(R.id.spinnerListaMaterias);
    }

    private void limpiar() {
        listaMateria.setSelection(0);
        for (EditText e : elementos) {
            e.setText("");
        }
    }

    private void registrarAviso() {

        int index = listaMateria.getSelectedItemPosition();

        if(titulo.getText().toString().equals("") || fechaEntrega.getText().toString().equals("")){
            Toast mensaje = Toast.makeText(getApplicationContext(), "Debe llenar los campos obligatorios", Toast.LENGTH_LONG);
            mensaje.show();
        }
        else {
            if(listaMateria.getSelectedItem().toString().equals("Seleccione una Materia") || index<0 ){
                Toast mensaje = Toast.makeText(getApplicationContext(), "Debe seleccionar una materia", Toast.LENGTH_LONG);
                mensaje.show();
            }
            else {
                cuerpoAviso = new StringBuilder();
                cuerpoAviso.append("Materia: ").append(listaMateria.getSelectedItem().toString()).append("\n");
                cuerpoAviso.append("Fecha de entrega: ").append(fechaEntrega.getText().toString()).append("\n");
                cuerpoAviso.append(descripcion.getText().toString()).append("\n");

                if(validarFecha()){
                    ConsultasCursoR consultasCursoR = new ConsultasCursoR(curso);
                    consultasCursoR.execute(1);
                }
            }
        }
    }

    private void registraTarea(){

        int index = listaMateria.getSelectedItemPosition();
        String idmateria = "";
        if(index>0){
            idmateria = (mapaMaterias.get(listaMateria.getSelectedItem().toString())).getIdMateria();
        }

        Tarea tarea = new Tarea(idmateria,titulo.getText().toString(),
                procesarFecha(),
                descripcion.getText().toString());

        ConsultasTarea consultasTarea = new ConsultasTarea(tarea);
        consultasTarea.execute(1);
    }

    private boolean validarFecha(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        String fechaActual = dateFormat.format(cal.getTime());
        Log.i("Fecha actual", fechaActual);

        String  fecha = procesarFecha();

        String [] actualComponentes = fechaActual.split("/");
        String [] fechaCompoentes = fecha.split("/");

        Integer [] tiempoActual = new Integer [3];
        Integer [] tiempoEntrega= new Integer [3];

        for(int i=0;i<actualComponentes.length;i++){
            tiempoActual[i] = new Integer(actualComponentes[i]);
            tiempoEntrega[i] = new Integer(fechaCompoentes[i]);
        }

        if(tiempoActual[0]>tiempoEntrega[0]){
            Toast mensaje = Toast.makeText(getApplicationContext(), "El año de la fecha es incorrecta \n Esta debe ser mayor al año actual", Toast.LENGTH_LONG);
            mensaje.show();
            return false;
        }
        else{
            if(tiempoActual[1]>tiempoEntrega[2]){
                Toast mensaje = Toast.makeText(getApplicationContext(), "El mes de la fecha es incorrecta \n Este debe ser mayor al mes actual", Toast.LENGTH_LONG);
                mensaje.show();
                return false;
            }
        }

        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yy/dd/MM");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            Toast mensaje = Toast.makeText(getApplicationContext(), "La fecha ingresada es incorrecta", Toast.LENGTH_LONG);
            mensaje.show();
            return false;
        }
        return true;

    }

    private String  procesarFecha(){
        String fecha = fechaEntrega.getText().toString();
        String fechaModificada;
        String [] componentes = fecha.split("/");
        fechaModificada = componentes[0] + "/"+ componentes[2] + "/" + componentes[1];

        Log.i("fecha", fecha);
        Log.i("fecha modificada", fechaModificada);

        return fechaModificada;

    }

    private class ConsultasCursoR extends AsyncTask<Integer, Void, String> {
        private String curso;
        public ConsultasCursoR(String curso) {
            this.curso = curso;
        }

        @Override
        protected String doInBackground(Integer... params) {

            String idCurso;
            ResultSet resultado;
            resultado = GestionAviso.buscarRepresentantesCurso(curso);
            try {
                while(resultado.next()){
                    idCurso = resultado.getString("id_curso");
                }
            } catch (SQLException e) {
                return "Incorrecto";
            }

            return "Correcto";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Correcto")) {

                Aviso aviso = new Aviso(curso, titulo.getText().toString(), cuerpoAviso.toString());
                ConsultasAviso registrarNuevo = new ConsultasAviso(aviso);
                registrarNuevo.execute(1);

                registraTarea();
                limpiar();
            }
        }
    }

    private class ConsultasAviso extends AsyncTask<Integer, Void, String> {
        private Aviso aviso;
        public ConsultasAviso(Aviso aviso) {
            this.aviso = aviso;
        }

        @Override
        protected String doInBackground(Integer... params) {
            aviso.registrarAviso();
            GestionAviso.enviarAvisos(aviso.getIdCurso(), aviso.getTitulo());
            return "Correcto";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Correcto")) {
                Toast registroCorrecto = Toast.makeText(getApplicationContext(), "EL aviso ha sido registrado", Toast.LENGTH_LONG);
                registroCorrecto.show();
            }
        }
    }

    private class ConsultasMaterias extends AsyncTask<Integer, Void, String> {

        String curso;
        public ConsultasMaterias(String curso) {this.curso = curso;}

        @Override
        protected String doInBackground(Integer... params) {
            materias = Materia.consultarMaterias(curso);
            return "Correcto";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Correcto")) {
                mapaMaterias = new HashMap<>();
                nombresMateria = new ArrayList<>();

                nombresMateria.add("Seleccione una Materia");
                for (Materia materia : materias){
                    nombresMateria.add(materia.getNombreMateria());
                    mapaMaterias.put(materia.getNombreMateria(),materia);
                }
                ArrayAdapter adapter = new ArrayAdapter(EnvioTareas.this, android.R.layout.simple_spinner_item, nombresMateria);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                listaMateria.setAdapter(adapter);
            }
        }
    }

    private class ConsultasTarea extends AsyncTask<Integer, Void, String> {
        private Tarea tarea;
        public ConsultasTarea(Tarea tarea) {
            this.tarea = tarea;
        }

        @Override
        protected String doInBackground(Integer... params) {
            tarea.registrarTarea();
            return "Correcto";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Correcto")) {
                Toast registroCorrecto = Toast.makeText(getApplicationContext(), "La Tarea ha sido registrado", Toast.LENGTH_LONG);
                registroCorrecto.show();
            }
        }
    }

}
