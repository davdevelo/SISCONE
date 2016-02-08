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

import java.util.ArrayList;
import java.util.List;

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

        if(titulo.getText().toString().equals("") || fechaEntrega.getText().toString().equals("")){
            Toast mensaje = Toast.makeText(getApplicationContext(), "Debe llenar los campos obligatorios", Toast.LENGTH_LONG);
            mensaje.show();
        }
        else {
            if(listaMateria.getSelectedItem().toString().equals("Seleccione una Materia")){
                Toast mensaje = Toast.makeText(getApplicationContext(), "Debe seleccionar una materia", Toast.LENGTH_LONG);
                mensaje.show();
            }
            else {
                cuerpoAviso = new StringBuilder();
                cuerpoAviso.append("Materia: ").append(listaMateria.getSelectedItem().toString()).append("\n");
                cuerpoAviso.append("Fecha de entrega: ").append(fechaEntrega.getText().toString()).append("\n");
                cuerpoAviso.append(descripcion.getText().toString()).append("\n");


                Aviso aviso = new Aviso(curso, titulo.getText().toString(), cuerpoAviso.toString());
                ConsultasAviso registrarNuevo = new ConsultasAviso(aviso);
                registrarNuevo.execute(1);

                registraTarea();
                limpiar();
            }
        }
    }

    private void registraTarea(){

        int index = listaMateria.getSelectedItemPosition();
        String idmateria = materias.get(index).getIdMateria();

        Tarea tarea = new Tarea(idmateria,titulo.getText().toString(),
                fechaEntrega.getText().toString(),
                descripcion.getText().toString());

        ConsultasTarea consultasTarea = new ConsultasTarea(tarea);
        consultasTarea.execute(1);
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
                nombresMateria = new ArrayList<>();
                nombresMateria.add("Seleccione una Materia");
                for (Materia materia : materias){
                    nombresMateria.add(materia.getNombreMateria());
                    Log.i("nombre materia",materia.getNombreMateria());
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
