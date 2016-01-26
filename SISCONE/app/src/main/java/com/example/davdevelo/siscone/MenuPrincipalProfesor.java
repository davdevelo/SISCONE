package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuPrincipalProfesor extends AppCompatActivity {

    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_profesor);
        recogerParametro();
        findViewById(R.id.buttonAdministracionCurso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalProfesor.this, MenuCurso.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });

    }

    private void recogerParametro(){
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        usuario = "";
        if (extra != null) {
            usuario = (String) extra.get("usuario");
            Log.i("usuario", usuario);
        }
    }
}
