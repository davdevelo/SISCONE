package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import moledos.Login;

public class MenuProfesor extends AppCompatActivity {

    private String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesor);

        recogerParametro();

        findViewById(R.id.buttonAdministrarRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuProfesor.this, AdministracionRepresentantes.class);
                intent.putExtra("cursoID", curso);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonMenuProfesorSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MenuProfesor.this, MainActivity.class));
            }
        });
        findViewById(R.id.buttonComunicados).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuProfesor.this, EnvioComunicados.class));
            }
        });
        findViewById(R.id.buttonAdministracionMaterias).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuProfesor.this, AdminMaterias.class);
                intent.putExtra("cursoID", curso);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonEnvioTareas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuProfesor.this, EnvioTareas.class));
            }
        });
        findViewById(R.id.buttonMenuProfesorSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuProfesor.this, MainActivity.class));
            }
        });
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        curso = "";
        if (extra != null) {
            curso = (String) extra.get("cursoID");
            Log.i("CursoID", curso);
        }
    }
}






