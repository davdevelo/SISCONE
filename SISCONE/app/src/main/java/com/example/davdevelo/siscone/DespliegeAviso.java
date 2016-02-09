package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import moledos.Aviso;
import moledos.Persona;

public class DespliegeAviso extends AppCompatActivity {

    private Persona usuario;
    private Aviso aviso;
    private TextView titulo;
    private TextView descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despliege_aviso);
        recogerParametro();
        iniciarEntrono();

        findViewById(R.id.buttonRegresarListaAvisos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DespliegeAviso.this,BuzonRepresentante.class);
                intent.putExtra("usuario",usuario.getCedula());
                intent.putExtra("persona", usuario);
                startActivity(intent);
                finish();
            }
        });
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if (extra != null) {
            usuario = (Persona) extra.get("persona");
            aviso = (Aviso) extra.get("aviso");
        }
    }


    private void iniciarEntrono(){
        titulo = (TextView) findViewById(R.id.textViewTituloAviso);
        descripcion = (TextView) findViewById(R.id.textViewDescripcionAviso);
        titulo.setText(aviso.getTitulo());
        descripcion.setText(aviso.getDescripcionAviso());

    }
}
