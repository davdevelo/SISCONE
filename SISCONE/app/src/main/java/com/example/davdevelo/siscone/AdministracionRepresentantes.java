package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import moledos.Estudiante;
import moledos.Persona;

public class AdministracionRepresentantes extends AppCompatActivity {

    private EditText cedulaRepresentante;
    private String curso;
    private EditText elementos[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_representantes);
        iniciarEntrono();
    }

    private void iniciarEntrono(){
        recogerParametro();
        buscarElementos();

        findViewById(R.id.buttonAgregarRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultasBase consulta = new ConsultasBase(cedulaRepresentante.getText().toString());
                consulta.execute(1);
            }
        });
        findViewById(R.id.buttonRegresoFormularioAR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministracionRepresentantes.this, MenuProfesor.class);
                intent.putExtra("cursoID", curso);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonRegitrarNuevoRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministracionRepresentantes.this, RegistroRepresentante.class);
                intent.putExtra("cursoID", curso);
                startActivity(intent);
            }
        });
    }
    private void buscarElementos(){
        cedulaRepresentante = (EditText) findViewById(R.id.editTextCedulaRepresentanteAR);
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

    private class ConsultasBase extends AsyncTask<Integer, Void, String> {

        private String cedula;
        public ConsultasBase(String cedula) {
            this.cedula = cedula;
        }

        @Override
        protected String doInBackground(Integer... params) {
            Persona p = new Persona();
            ResultSet resultado = p.buscarPersona("Representante", cedula);
            String nombre = "";
            try {
                while (resultado.next()) {
                    nombre = resultado.getString("nombre_Representante");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (nombre.equals("")) {
                Toast mnesajeError = Toast.makeText(getApplicationContext(), "El Representante no esta registrado  \n Debe registralo en el sistema primero ", Toast.LENGTH_LONG);
                mnesajeError.show();
                return "Incorrecto";

            } else {
                return "Correcto";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Correcto")) {
                Intent intent = new Intent(AdministracionRepresentantes.this, RegistoAlumnos.class);
                intent.putExtra("cursoID", curso);
                intent.putExtra("representante",cedula);
                startActivity(intent);
            }
        }
    }


}
