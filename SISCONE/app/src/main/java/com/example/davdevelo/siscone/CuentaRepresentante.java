package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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
        buscarElementos();
        recogerParametro();
        iniciarEntorno();
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

    private void buscarElementos() {
        correo = (EditText) findViewById(R.id.editTextCorreoRepresentante);
        contrasenaNueva = (EditText) findViewById(R.id.editTextContraseñaAntigua);
        confirmarContrasena = (EditText) findViewById(R.id.editCorreoRepresentante);
        datosPeronales = (TextView) findViewById(R.id.textViewDatosPerosnales);
    }

    private void iniciarEntorno(){
        StringBuilder datos = new StringBuilder();
        datos.append(usuario.getNombre()).append(" ").append(usuario.getApellido());
        datos.append("  ").append(usuario.getCedula());
        datosPeronales.setText(datos.toString());
        correo.setText(usuario.getCorreo());
    }


}
