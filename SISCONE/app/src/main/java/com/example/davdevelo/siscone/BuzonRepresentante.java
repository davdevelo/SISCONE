package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apoyo.OnTouchEvent;
import moledos.Aviso;
import moledos.Persona;

public class BuzonRepresentante extends AppCompatActivity {


    private ArrayAdapter adaptador;
    private List<Aviso> avisos;
    private List<String> titulos;
    private String representante;
    private ListView listaAvisos;
    private Map<Integer,Aviso> mapaAvisos;
    Persona usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon_representante);
        iniciarEntorno();

        findViewById(R.id.buttonRegresarBuzon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuzonRepresentante.this, MenuRepresentante.class);
                intent.putExtra("usuario", representante);
                intent.putExtra("persona",usuario);
                startActivity(intent);
                finish();
            }
        });
    }


    private void iniciarEntorno(){
        recogerParametro();
        avisos = new ArrayList<>();
        titulos = new ArrayList<>();
        listaAvisos = (ListView) findViewById(R.id.listViewAvisosBuzon);
        listaAvisos.setOnTouchListener(new OnTouchEvent());
        consultar();

        listaAvisos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aviso aviso = mapaAvisos.get(position);
                Intent intent = new Intent(BuzonRepresentante.this, DespliegeAviso.class);
                intent.putExtra("aviso", aviso);
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
        representante = "";
        if (extra != null) {
            representante = (String) extra.get("usuario");
            usuario = (Persona) extra.get("persona");
            Log.i("representante", representante);
        }
    }

    private void consultar(){
        ConsultarAvisos consulta = new ConsultarAvisos();
        consulta.execute(1);
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
            mapaAvisos = new HashMap<>();
            for (int i=0;i<avisos.size();i++){
                titulos.add(avisos.get(i).getTitulo());
                mapaAvisos.put(i,avisos.get(i));
            }
            adaptador = new ArrayAdapter(
                    BuzonRepresentante.this, android.R.layout.simple_expandable_list_item_1, titulos
            );
            listaAvisos.setAdapter(adaptador);
        }
    }


}
