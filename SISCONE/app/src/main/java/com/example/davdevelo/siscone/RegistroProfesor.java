package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegistroProfesor extends AppCompatActivity {

    EditText cedulaProf;
    EditText nombreProf;
    EditText apellidoProf;
    EditText correoProf;
    EditText contraseñaProf;
    EditText confContraseñaProf;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesor);
        findViewById(R.id.buttonRegresarLoginProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroProfesor.this, MainActivity.class));
            }
        });
        findViewById(R.id.buttonIngresarRegProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroProfesor.this, MainActivity.class));
            }
        });
    }

    public void buscarElementos()
    {
        cedulaProf= (EditText) findViewById(R.id.editTextCedulaProf);
        nombreProf= (EditText) findViewById(R.id.editNombreProf);
        apellidoProf= (EditText) findViewById(R.id.editApellidoProf);
        correoProf= (EditText) findViewById(R.id.editCorreoProf);
        contraseñaProf= (EditText) findViewById(R.id.editContraseñaRegProf1);
        confContraseñaProf= (EditText) findViewById(R.id.editContraseñaRegProf2);
    }

    public void limpiarRegistroProf ()
    {
        cedulaProf.setText("");
        nombreProf.setText("");
        apellidoProf.setText("");
        correoProf.setText("");
        correoProf.setText("");
        contraseñaProf.setText("");
        confContraseñaProf.setText("");
    }

}
