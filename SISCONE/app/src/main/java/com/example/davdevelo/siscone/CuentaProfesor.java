package com.example.davdevelo.siscone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CuentaProfesor extends AppCompatActivity {


    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText correo;
    private EditText elementos[];
    private String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_profesor);
    }
}
