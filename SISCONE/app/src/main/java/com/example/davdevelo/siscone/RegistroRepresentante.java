package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import moledos.Persona;

public class RegistroRepresentante extends AppCompatActivity {

    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText correo;
    private EditText elementos[];
    private String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_representante);

        buscarElementos();
        elementos = new EditText[]{cedula, nombre, apellido, correo};
        findViewById(R.id.buttonRegresarMenuProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(RegistroRepresentante.this, AdministracionRepresentantes.class));
                intent.putExtra("cursoID", curso);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.buttonLimpiarForRegisRepr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
        findViewById(R.id.buttonRegistrarRepr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarRepresentante();
            }
        });
    }

    private void buscarElementos() {
        cedula = (EditText) findViewById(R.id.editCedulaRepresentante);
        nombre = (EditText) findViewById(R.id.editNombreRepresentante);
        apellido = (EditText) findViewById(R.id.editApellidoRepresentante);
        correo = (EditText) findViewById(R.id.editCorreoRepresentante);
    }

    private Persona recuperarDatos() {
        Persona representante = new Persona(
                cedula.getText().toString(),
                nombre.getText().toString(),
                apellido.getText().toString(),
                correo.getText().toString(),
                "12345");
        return representante;
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        curso = "";
        if (extra != null) {
            curso = (String) extra.get("cursoID");
        }
    }

    private void limpiar() {
        for (EditText e : elementos) {
            e.setText("");
        }
    }

    private void registrarRepresentante() {
        ConsultasRepresentante registrarNuevo = new ConsultasRepresentante(recuperarDatos());
        registrarNuevo.execute(1);
        limpiar();
    }

    private class ConsultasRepresentante extends AsyncTask<Integer, Void, String> {
        private Persona representante;

        public ConsultasRepresentante(Persona representante) {
            this.representante = representante;
        }

        @Override
        protected String doInBackground(Integer... params) {
            ResultSet resultado = representante.buscarPersona("Representante", representante.getCedula());
            String nombre = "";
            try {
                while (resultado.next()) {
                    nombre = resultado.getString("nombre_Representante");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (nombre.equals("")) {
                representante.registrarPersona("Representante");
                return "Correcto";
            } else {
                Toast registroExiste = Toast.makeText(getApplicationContext(), "El Representante ya ha sido registrado anteriormente", Toast.LENGTH_LONG);
                registroExiste.show();
                return "Incorrecto";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Correcto")) {
                Toast registroCorrecto = Toast.makeText(getApplicationContext(), "Representante registrado correctamente", Toast.LENGTH_LONG);
                registroCorrecto.show();
            }
        }
    }


}
