package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuRepresentante extends AppCompatActivity {

    String cedulaRepresentante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_representante);
        recogerParametro();

        findViewById(R.id.buttonAdministrarCuentaRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuRepresentante.this, CuentaRepresentante
                        .class);
                intent.putExtra("usuario", cedulaRepresentante);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonAdministrarBuzon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuRepresentante.this, BuzonRepresentante.class);
                intent.putExtra("usuario", cedulaRepresentante);
                startActivity(intent);
            }
        });

    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        cedulaRepresentante = "";
        if (extra != null) {
            cedulaRepresentante = (String) extra.get("usuario");
            Log.i("representante", cedulaRepresentante);
        }
    }

}
