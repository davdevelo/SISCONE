package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

import apoyo.GestionAviso;
import apoyo.OnClickEvent;
import moledos.Aviso;
import moledos.Persona;

/**
 * Created by davdevelo on 14/12/2015.
 */
public class EnvioComunicados extends AppCompatActivity {


    private EditText titulo;
    private EditText descripcion;
    private String curso;
    private String cedulaP;
    private Persona usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_comunicados);

        iniciarEntorno();

        findViewById(R.id.buttonRegresarEnvioComunicados).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnvioComunicados.this, MenuProfesor.class);
                intent.putExtra("cursoID", curso);
                intent.putExtra("usuario", cedulaP);
                intent.putExtra("persona", usuario);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonGuardarComunicado).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAviso();
            }
        });
    }

    private void iniciarEntorno() {
        recogerParametro();
        buscarElementos();
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        curso = "";
        if (extra != null) {
            curso = (String) extra.get("cursoID");
            cedulaP = (String) extra.get("usuario");
            usuario = (Persona) extra.get("persona");
            Log.i("cursoID", curso);
        }
    }

    private void buscarElementos() {
        titulo = (EditText) findViewById(R.id.editTextTituloComunicado);
        descripcion = (EditText) findViewById(R.id.editTextDescripcionComunicado);
    }

    private void registrarAviso() {

        if(titulo.getText().toString().equals("") || descripcion.getText().toString().equals("") ){
            Toast mensaje = Toast.makeText(getApplicationContext(), "Debe dar un titulo y una descripción al aviso ", Toast.LENGTH_LONG);
            mensaje.show();
        }else{
            ConsultasCursoR consultasCursoR = new ConsultasCursoR(curso,
                    titulo.getText().toString(),descripcion.getText().toString());
            consultasCursoR.execute(1);
        }

    }

    private class ConsultasCursoR extends AsyncTask<Integer, Void, String> {
        private String curso;
        private String titulo;
        private String descripcion;

        public ConsultasCursoR(String curso, String titulo, String descripcion) {
            this.curso = curso;
            this.titulo = titulo;
            this.descripcion = descripcion;
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
                Aviso aviso = new Aviso(curso, titulo, descripcion);
                ConsultasAviso registrarNuevo = new ConsultasAviso(aviso);
                registrarNuevo.execute(1);
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
                titulo.setText("");
                descripcion.setText("");
                Toast registroCorrecto = Toast.makeText(getApplicationContext(), "EL aviso ha sido registrado", Toast.LENGTH_LONG);
                registroCorrecto.show();
            }
        }
    }
}
