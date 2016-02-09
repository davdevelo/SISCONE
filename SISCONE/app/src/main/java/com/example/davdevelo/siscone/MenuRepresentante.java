package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import moledos.Persona;

public class MenuRepresentante extends AppCompatActivity {


    private Persona usuario;
    private String cedulaRepresentante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_representante);
        recogerParametro();

        findViewById(R.id.buttonAdministrarCuentaRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuRepresentante.this, CuentaRepresentante.class);
                intent.putExtra("usuario",cedulaRepresentante);
                intent.putExtra("persona",usuario);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.buttonAdministrarBuzon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuRepresentante.this, BuzonRepresentante.class);
                intent.putExtra("usuario", cedulaRepresentante);
                intent.putExtra("persona",usuario);
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



}
