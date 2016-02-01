package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import apoyo.OnTouchEvent;
import moledos.Aviso;

public class BuzonRepresentante extends AppCompatActivity {


    private ArrayAdapter adaptador;
    private List<Aviso> avisos;
    private List<String> titulos;
    private String representante;
    private ListView listaAvisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon_representante);
        iniciarEntorno();
    }


    private void iniciarEntorno(){
        recogerParametro();

        avisos = new ArrayList<>();
        titulos = new ArrayList<>();
        listaAvisos = (ListView) findViewById(R.id.listViewAvisosBuzon);
        listaAvisos.setOnTouchListener(new OnTouchEvent());

    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        representante = "";
        if (extra != null) {
            representante = (String) extra.get("usuario");
            Log.i("representante", representante);
        }
    }


    private class ConsultarAvisos extends AsyncTask<Integer, Void, String> {

        public ConsultarAvisos() {
        }

        @Override
        protected String doInBackground(Integer... params) {
            Aviso aviso = new Aviso();
            avisos = aviso.buscarAvisos(representante);


            return "nada";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            for (Aviso aviso : avisos){
                titulos.add(aviso.getTitulo());
            }
            adaptador = new ArrayAdapter(
                    BuzonRepresentante.this, android.R.layout.simple_expandable_list_item_1, titulos
            );
            listaAvisos.setAdapter(adaptador);
        }
    }


}
