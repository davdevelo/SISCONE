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

import java.util.List;

import moledos.Curso;
import moledos.Persona;

public class RegistroCurso extends AppCompatActivity {


    private EditText nombreInstitucion;
    private EditText nombreCurso;
    private EditText paralelo;
    private EditText elementos[];
    private String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_curso);
        iniciarEntrono();
    }

    private void iniciarEntrono(){
        buscarElementos();
        elementos = new EditText[]{nombreInstitucion, nombreCurso, paralelo};
        recogerParamentro();

        findViewById(R.id.buttonRegistroCusrso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(nombreInstitucion.getText().toString().equals("") ooo  )
                        //toast
                else
                {

                }*/
                registrarCurso();
            }
        });

        findViewById(R.id.buttonLimpiarRegistroCurso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarFormulario();
            }
        });

        findViewById(R.id.buttonRegresoFormularioCuro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(RegistroCurso.this, MenuCurso.class);
                intent.putExtra("usuario", cedula);
                startActivity(intent);
            }
        });

    }

    private void buscarElementos(){
        nombreInstitucion = (EditText) findViewById(R.id.editTextNombreInstitucion);
        nombreCurso = (EditText) findViewById(R.id.editTextNombreCurso);
        paralelo = (EditText) findViewById(R.id.editTextParallelo);
    }

    private void limpiarFormulario(){
        for (EditText e : elementos) {
            e.setText("");
        }
    }

    private void recogerParamentro(){
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        cedula = "";
        if (extra != null) {
            cedula = (String) extra.get("cedulaProfesor");
        }
    }

    private void registrarCurso(){

        String institucion = nombreInstitucion.getText().toString();
        String curso = nombreCurso.getText().toString();
        String paralel = paralelo.getText().toString();

        if(institucion.equals("") || curso.equals("") || paralel.equals("") ){
            Toast mensaje = Toast.makeText(getApplicationContext(), "Faltan Campos Por Llenar", Toast.LENGTH_LONG);
            mensaje.show();
        }else{
            Curso cursoNuevo = new Curso(institucion,curso,paralel,new Integer(cedula));
            ConsultasCurso  registrarCursoNuevo = new ConsultasCurso(cursoNuevo);
            registrarCursoNuevo.execute(1);
        }
    }

    private class ConsultasCurso extends AsyncTask<Integer, Void, String> {
        private Curso curso;
        public ConsultasCurso(Curso curso) {
            this.curso = curso;
        }

        @Override
        protected String doInBackground(Integer... params) {
            curso.registrarCurso();
            return "Curso Registrado";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("Estado", s);
            Toast registroCorrecto = Toast.makeText(getApplicationContext(), "El Curso ha sido registrado Correctamente", Toast.LENGTH_LONG);
            registroCorrecto.show();
            finish();
            Intent intent = new Intent(RegistroCurso.this, MenuCurso.class);
            intent.putExtra("usuario", cedula);
            startActivity(intent);

        }
    }


}
