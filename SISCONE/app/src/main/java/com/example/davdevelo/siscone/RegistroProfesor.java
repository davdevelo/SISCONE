package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import moledos.Login;
import moledos.Persona;
import momentario.Listas;

public class RegistroProfesor extends AppCompatActivity {

    EditText cedulaProf;
    EditText nombreProf;
    EditText apellidoProf;
    EditText correoProf;
    EditText contraseñaProf;
    EditText confContraseñaProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesor);

        buscarElementos();

        findViewById(R.id.buttonRegresarLoginProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroProfesor.this, MainActivity.class));
            }
        });
        findViewById(R.id.buttonIngresarRegProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProfesor();

            }
        });
        findViewById(R.id.buttonLimpiarForRegisProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarRegistroProf();
            }
        });
    }

    private void buscarElementos() {
        cedulaProf = (EditText) findViewById(R.id.editCedulaProf);
        nombreProf = (EditText) findViewById(R.id.editNombreProf);
        apellidoProf = (EditText) findViewById(R.id.editApellidoProf);
        correoProf = (EditText) findViewById(R.id.editCorreoProf);
        contraseñaProf = (EditText) findViewById(R.id.editContraseñaRegProf1);
        confContraseñaProf = (EditText) findViewById(R.id.editContraseñaRegProf2);

    }

    private void limpiarRegistroProf() {

        cedulaProf.setText("");
        nombreProf.setText("");
        apellidoProf.setText("");
        correoProf.setText("");
        correoProf.setText("");
        contraseñaProf.setText("");
        confContraseñaProf.setText("");
    }

    private Object[] recuperraDatos(){

        buscarElementos();
        Persona profesor = new Persona(
                cedulaProf.getText().toString(),
                nombreProf.getText().toString(),
                apellidoProf.getText().toString(),
                correoProf.getText().toString(),
                contraseñaProf.getText().toString()
                , "Profesor");

        Login login = new Login(
                cedulaProf.getText().toString(),
                contraseñaProf.getText().toString(),
                "Profesor");

        Object datos[] = new Object[]{profesor,login};
        return datos;
    }




    public void registrarProfesor() {

        Object datos[] = recuperraDatos();
        Persona profesor = (Persona)datos[0];
        Login login = (Login) datos[1];

        if (contraseñaProf.getText().toString().equals(confContraseñaProf.getText().toString())) {
            Toast registroCorrecto = Toast.makeText(getApplicationContext(), "Usted se ha registrado correctamente", Toast.LENGTH_LONG);
            registroCorrecto.show();

            Listas.registrados.add(login);
            Listas.profesores.add(profesor);

            Intent intent = new Intent(RegistroProfesor.this, MainActivity.class);
            startActivity(intent);

        } else {
            Toast registroIncorrecto = Toast.makeText(getApplicationContext(), "Las contrasenas deben ser iguales", Toast.LENGTH_LONG);
            registroIncorrecto.show();
        }

    }

}
