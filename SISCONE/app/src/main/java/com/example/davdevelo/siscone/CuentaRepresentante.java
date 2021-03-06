package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

import moledos.Persona;

public class CuentaRepresentante extends AppCompatActivity {

    private TextView datosPeronales;
    private EditText correo;
    private EditText contrasenaNueva;
    private EditText confirmarContrasena;
    private EditText elementos[];
    private Persona usuario;
    private String cedulaRepresentante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_representante);

        elementos = new EditText[]{contrasenaNueva, confirmarContrasena};
        iniciarEntorno();

        findViewById(R.id.buttonActualizarDatosRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRepresentante();
            }
        });

        findViewById(R.id.buttonLimpiarCuentaR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contrasenaNueva.setText("");
                confirmarContrasena.setText("");
            }
        });

        findViewById(R.id.buttonRegresarCuentaR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuentaRepresentante.this, MenuRepresentante.class);
                intent.putExtra("usuario", cedulaRepresentante);
                intent.putExtra("persona", usuario);
                startActivity(intent);
                finish();
            }
        });


    }


    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        cedulaRepresentante = "";
        if (extra != null) {
            cedulaRepresentante = (String) extra.get("usuario");
            usuario = (Persona) extra.get("persona");
            Log.i("representante", cedulaRepresentante);
        }
    }

    private void limpiarFormulario() {
        for (EditText e : elementos) {
            e.setText("");
        }
    }

    private void buscarElementos() {
        correo = (EditText) findViewById(R.id.editTextCorreoRepresentante);
        contrasenaNueva = (EditText) findViewById(R.id.editTextContraseñaNueva);
        confirmarContrasena = (EditText) findViewById(R.id.editTextConfirmarContrasena);
        datosPeronales = (TextView) findViewById(R.id.textViewDatosPerosnales);
    }

    private void iniciarEntorno() {
        buscarElementos();
        recogerParametro();

        StringBuilder datos = new StringBuilder();
        datos.append(usuario.getNombre().trim()).append(" ").append(usuario.getApellido().trim());
        datos.append("  ").append(usuario.getCedula().trim());
        datosPeronales.setText(datos.toString());
        correo.setText(usuario.getCorreo().trim());
    }

    private void actualizarRepresentante() {
        if (contrasenaNueva.getText().toString().trim().equals(
                confirmarContrasena.getText().toString().trim())) {

            usuario.setContrasena(contrasenaNueva.getText().toString());
            usuario.setCorreo(correo.getText().toString().trim());
            ConsultasRepresentante actualizar = new ConsultasRepresentante(usuario);
            actualizar.execute(1);
        } else {
            Toast mensaje = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG);
            mensaje.show();
        }


    }

    private class ConsultasRepresentante extends AsyncTask<Integer, Void, String> {
        private Persona representante;

        public ConsultasRepresentante(Persona representante) {
            this.representante = representante;
        }

        @Override
        protected String doInBackground(Integer... params) {
            Boolean resultado = representante.actualizarDatos("Representante");
            return "Correcto";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Correcto")) {
                Toast mensaje = Toast.makeText(getApplicationContext(), "Datos actualizados correctamente", Toast.LENGTH_LONG);
                mensaje.show();
            } else {
                Toast mensaje = Toast.makeText(getApplicationContext(), "No se ha podido actualizar los datos", Toast.LENGTH_LONG);
                mensaje.show();
            }
            contrasenaNueva.setText("");
            confirmarContrasena.setText("");
        }
    }


}
