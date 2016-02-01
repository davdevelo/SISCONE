package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import apoyo.OnTouchEvent;
import moledos.Estudiante;
import moledos.Persona;

public class RegistoAlumnos extends AppCompatActivity {

    private EditText apellidoEstudiante;
    private EditText nombreEstudiante;
    private ArrayAdapter adaptador;
    private List<Estudiante> listaEstudiante;
    private List<String> alumnos;
    private String curso;
    private String representante;
    private ListView listaAlumnos;
    private EditText elementos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo_alumnos);
        iniciarEntorno();
    }

    private void iniciarEntorno() {

        buscarElementos();
        recogerParametro();
        alumnos = new ArrayList<>();
        listaEstudiante = new ArrayList<>();
        elementos = new EditText[]{apellidoEstudiante, nombreEstudiante};
        listaAlumnos.setOnTouchListener(new OnTouchEvent());

        findViewById(R.id.buttonAgregarAlumno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAlumno();
            }
        });

        findViewById(R.id.buttonLimpiarFormularioAlummnos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        findViewById(R.id.buttonCancelarRegistroAlumnos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.buttonTerminarRegistroAlumnos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultasBase consulta = new ConsultasBase();
                consulta.execute(1);
            }
        });
    }

    private void agregarAlumno() {
        String nombre = nombreEstudiante.getText().toString();
        String apellido = apellidoEstudiante.getText().toString();
        if (nombre.equals("") || apellido.equals("")) {
            Toast mensaje = Toast.makeText(getApplicationContext(), "Falta Campos Por Llenar ", Toast.LENGTH_LONG);
            mensaje.show();
        } else {
            alumnos.add(nombreEstudiante.getText().toString() + " " + apellidoEstudiante.getText().toString());
            adaptador = new ArrayAdapter(
                    this, android.R.layout.simple_expandable_list_item_1, alumnos
            );
            listaAlumnos.setAdapter(adaptador);
            listaEstudiante.add(new Estudiante(curso, representante, nombre, apellido));
        }
        limpiar();
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        curso = "";
        if (extra != null) {
            curso = (String) extra.get("cursoID");
            representante = (String) extra.get("representante");
            Log.i("cursoID", curso);
            Log.i("representante", representante);
        }
    }

    private void buscarElementos() {
        listaAlumnos = (ListView) findViewById(R.id.listViewAlumnos);
        apellidoEstudiante = (EditText) findViewById(R.id.editTextApellidoEstudianteAR);
        nombreEstudiante = (EditText) findViewById(R.id.editTextNombreEstudianteAR);
    }

    private void limpiar() {
        for (EditText e : elementos) {
            e.setText("");
        }
    }


    private class ConsultasBase extends AsyncTask<Integer, Void, String> {

        public ConsultasBase() {
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (Estudiante e : listaEstudiante) {
                e.registrarAlumno();
            }
            listaEstudiante.get(0).agregarRepresentante();
            return "Incorrecto";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            finish();
            Intent intent = new Intent(RegistoAlumnos.this, AdministracionRepresentantes.class);
            intent.putExtra("cursoID", curso);
            startActivity(intent);
        }
    }


}
