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

import moledos.Persona;

public class CuentaProfesor extends AppCompatActivity {

    private TextView datosPeronales;
    private EditText correo;
    private EditText contrasenaNueva;
    private EditText confirmarContrasena;
    private EditText elementos[];
    private Persona usuario;
    private String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_profesor);

        elementos = new EditText[]{contrasenaNueva, confirmarContrasena};
        iniciarEntorno();

        findViewById(R.id.buttonActualizarDatosProfesor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRepresentante();
            }
        });

        findViewById(R.id.buttonLimpiarCuentaP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contrasenaNueva.setText("");
                confirmarContrasena.setText("");
            }
        });

        findViewById(R.id.buttonRegresarCuentaP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CuentaProfesor.this, MenuPrincipalProfesor.class);
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);
                finish();
            }
        });
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        cedula = "";
        if (extra != null) {
            cedula = (String) extra.get("usuario");
            usuario = (Persona) extra.get("persona");
            Log.i("representante", cedula);
        }
    }

    private void buscarElementos() {
        correo = (EditText) findViewById(R.id.editTextCorreoProfesor);
        contrasenaNueva = (EditText) findViewById(R.id.editTextContraseñaNuevaP);
        confirmarContrasena = (EditText) findViewById(R.id.editTextConfirmarContraseñaP);
        datosPeronales = (TextView) findViewById(R.id.textViewDatosPerosnalesP);
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

        if (correo.getText().toString().equals("") || contrasenaNueva.getText().toString().equals("") ||
                confirmarContrasena.getText().toString().equals("")) {
            Toast mensaje = Toast.makeText(getApplicationContext(), "Ni el correo, ni la contrseña pueden estar vacias", Toast.LENGTH_LONG);
            mensaje.show();

        } else {
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


    }

    private class ConsultasRepresentante extends AsyncTask<Integer, Void, String> {
        private Persona representante;

        public ConsultasRepresentante(Persona representante) {
            this.representante = representante;
        }

        @Override
        protected String doInBackground(Integer... params) {
            Boolean resultado = representante.actualizarDatos("Profesor");
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
