package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

import moledos.Login;
import moledos.Persona;

public class MainActivity extends AppCompatActivity {

    private EditText cedula;
    private EditText contrasena;
    private Spinner sistemas;
    private EditText elementos[];

    private final String CONTRASENA_INVALIDA="La contraseña  no coincide";
    private final String USUARIO_NO_REGISTRADO="No exite el usuraio";
    private final String SElELECIONE_TIPO = "Debe seleccion el tipo de Usuario";
    private final String TIPO="Seleccione el Tipo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);
        iniciarEntorno();
    }

    private void iniciarEntorno() {
        buscarElemntos();
        elementos = new EditText[]{cedula, contrasena};

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipoUsuario, android.R.layout.simple_spinner_item);
        sistemas.setAdapter(adapter);

        findViewById(R.id.buttonLimpiar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        findViewById(R.id.buttonSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buttonIngresar).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logearse();
            }
        });
        findViewById(R.id.buttonCrearCuenta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistroProfesor.class));
            }
        });

    }

    private void limpiar() {
        sistemas.setSelection(0);
        for (EditText e : elementos) {
            e.setText("");
        }
    }

    private void buscarElemntos() {
        cedula = (EditText) findViewById(R.id.editCedulaLogin);
        contrasena = (EditText) findViewById(R.id.editContraseñaLogin);
        sistemas = (Spinner) findViewById(R.id.comboBoxTipoUSR);
    }

    private void logearse() {
        Login login = new Login(cedula.getText().toString(),
                contrasena.getText().toString(),
                sistemas.getSelectedItem().toString());
        if(login.getTipo().equals(TIPO)){
            Toast toast = Toast.makeText(getApplicationContext(), SElELECIONE_TIPO, Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Logeo logeo = new Logeo(login);
            logeo.execute(1);
        }


    }

    private class Logeo extends AsyncTask<Integer, Void, ResultSet> {
        private Login login;
        public Logeo(Login login) {
            this.login = login;
        }

        @Override
        protected ResultSet doInBackground(Integer... params) {
            ResultSet persona;
            Persona profesor = new Persona();
            if (login.getTipo().equals("Profesor")) {
                persona = profesor.buscarPersona("Profesor", login.getCedula());
            }else{
                persona = profesor.buscarPersona("Representante", login.getCedula());
            }
            return persona;
        }

        @Override
        protected void onPostExecute(ResultSet persona) {
            super.onPostExecute(persona);
            String contrasena="";
            String nombre = "";
            String apellido = "";
            String correo = "";
            try {
                while(persona.next()) {
                    contrasena=persona.getString("contrasena_"+login.getTipo());
                    nombre=persona.getString("nombre_"+login.getTipo());
                    apellido = persona.getString("apellido_"+login.getTipo());
                    correo = persona.getString("correo_"+login.getTipo());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!contrasena.equals("")) {
                if(login.getContrasena().trim().equals(contrasena.trim())){

                    Persona personaUsuaria = new Persona(login.getCedula(),nombre,
                            apellido,correo,contrasena);

                    finish();
                    if(login.getTipo().equals("Profesor")) {
                        Intent intent = new Intent(MainActivity.this, MenuPrincipalProfesor.class);
                        intent.putExtra("usuario", login.getCedula());
                        intent.putExtra("persona",personaUsuaria);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(MainActivity.this, MenuRepresentante.class);
                        intent.putExtra("usuario", login.getCedula());
                        intent.putExtra("persona",personaUsuaria);
                        startActivity(intent);
                    }
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), CONTRASENA_INVALIDA, Toast.LENGTH_SHORT);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), USUARIO_NO_REGISTRADO, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }


}
