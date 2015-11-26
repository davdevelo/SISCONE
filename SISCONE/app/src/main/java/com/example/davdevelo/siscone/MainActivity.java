package com.example.davdevelo.siscone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    Spinner sistemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sistemas =(Spinner) findViewById(R.id.comboBoxTipoUSR);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.tipoUsuario,android.R.layout.simple_spinner_item);
        sistemas.setAdapter(adapter);
    }



}
