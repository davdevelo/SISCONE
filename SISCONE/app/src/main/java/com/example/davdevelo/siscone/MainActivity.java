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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import crud.DBConnection;
import moledos.Login;
import moledos.Persona;
import momentario.Listas;

public class MainActivity extends AppCompatActivity {

    private EditText cedula;
    private EditText contrasena;
    private Spinner sistemas;
    private EditText elementos[];

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

        findViewById(R.id.buttonCrearCuenta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, RegistroProfesor.class));
            }
        });

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

        Logeo logeo = new Logeo(login);
        logeo.execute(1);

        ResultSet profesor = logeo.getProfe();

        try {
            while(profesor.next()) {
                Log.i("contrasena: ", profesor.getString("contrasena_profesor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (logeo.getProfe() != null) {
            finish();
            startActivity(new Intent(MainActivity.this, MenuProfesor.class));
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No exite el usuraio \n O los campos estan llenados incorectamente ", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    private class Logeo extends AsyncTask<Integer, Void, String> {
        private Login login;
        private ResultSet profe;

        public Logeo(Login login) {
            this.login = login;
        }

        public ResultSet getProfe() {
            return profe;
        }

        @Override
        protected String doInBackground(Integer... params) {
            if (login.getTipo().equals("Profesor")) {
                profe = null;
                Persona profesor = new Persona();
                profe = profesor.buscarProfesor(login.getCedula());
            }
            return "Consultando profe";

        }

    }


}
