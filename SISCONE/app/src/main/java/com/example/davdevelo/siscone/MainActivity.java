package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    Spinner sistemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);

        sistemas = (Spinner) findViewById(R.id.comboBoxTipoUSR);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipoUsuario, android.R.layout.simple_spinner_item);
        sistemas.setAdapter(adapter);
        findViewById(R.id.buttonCrearCuenta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroProfesor.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonLimpiar).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
    }

    public void limpiar(){

        EditText cedula = (EditText) findViewById(R.id.editCedula);
        EditText contrasena = (EditText) findViewById(R.id.editContraseña);
        sistemas.setSelection(0);


        cedula.setText("");
        contrasena.setText("");
    }



}
