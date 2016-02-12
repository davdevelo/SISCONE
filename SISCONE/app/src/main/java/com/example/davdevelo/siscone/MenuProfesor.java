package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import moledos.Login;
import moledos.Persona;

public class MenuProfesor extends AppCompatActivity {

    private String curso;
    private String cedula;
    private Persona usuario;

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
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonMenuProfesorSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MenuProfesor.this, MenuPrincipalProfesor.class);
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);

            }
        });
        findViewById(R.id.buttonComunicados).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuProfesor.this, EnvioComunicados.class);
                intent.putExtra("cursoID", curso);
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);
                finish();

            }
        });
        findViewById(R.id.buttonAdministracionMaterias).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuProfesor.this, AdminMaterias.class);
                intent.putExtra("cursoID", curso);
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.buttonEnvioTareas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MenuProfesor.this, EnvioTareas.class);
                intent.putExtra("cursoID", curso);
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
        curso = "";
        if (extra != null) {
            curso = (String) extra.get("cursoID");
            cedula = (String) extra.get("usuario");
            usuario = (Persona) extra.get("persona");
            Log.i("CursoID", curso);
        }
    }
}






