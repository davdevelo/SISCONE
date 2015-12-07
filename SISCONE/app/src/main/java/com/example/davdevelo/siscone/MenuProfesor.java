package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import moledos.Login;

public class MenuProfesor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesor);
        findViewById(R.id.buttonAdministrarRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuProfesor.this, RegistroRepresentante.class));
            }
        });

        findViewById(R.id.buttonMenuProfesorSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MenuProfesor.this, MainActivity.class));
            }
        });
    }
}






