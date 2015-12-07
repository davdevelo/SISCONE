package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import moledos.Login;
import momentario.Listas;

public class MainActivity extends AppCompatActivity {

    EditText cedula;
    EditText contrasena;
    Spinner sistemas;
    private EditText elementos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);

        buscarElemntos();
        elementos = new EditText[]{cedula,contrasena};

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
    }

    private void limpiar() {
        sistemas.setSelection(0);
        for(EditText e : elementos){
            e.setText("");
        }
    }

    private void buscarElemntos() {
        cedula = (EditText) findViewById(R.id.editCedulaLogin);
        contrasena = (EditText) findViewById(R.id.editContraseñaLogin);
        sistemas = (Spinner) findViewById(R.id.comboBoxTipoUSR);
    }

    private void logearse(){
        Login login = new Login(cedula.getText().toString(),
                contrasena.getText().toString(),
                sistemas.getSelectedItem().toString());

        for(Login l:Listas.registrados){
            Log.i("Login",l.getCedula()+ "  " + l.getContrasena()+"  " + l.getTipo());
        }

        if(Listas.registrados.contains(login)) {
            finish();
            startActivity(new Intent(MainActivity.this, MenuProfesor.class));
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "No exite el usuraio \n O los campos estan llenados incorectamente ", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


}
