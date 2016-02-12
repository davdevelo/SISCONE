package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import moledos.Persona;

public class MenuPrincipalProfesor extends AppCompatActivity {

    String cedula;
    Persona usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_profesor);
        recogerParametro();
        findViewById(R.id.buttonAdministracionCurso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalProfesor.this, MenuCurso.class);
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.buttonAdministracionCuenta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalProfesor.this, CuentaProfesor.class);
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.buttonSalirMPR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalProfesor.this, MainActivity.class);
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
}
