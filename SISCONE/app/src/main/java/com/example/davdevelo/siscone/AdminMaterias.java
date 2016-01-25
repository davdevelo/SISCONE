package com.example.davdevelo.siscone;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import apoyo.OnTouchEvent;
import moledos.Estudiante;
import moledos.Materia;

/**
 * Created by davdevelo on 14/12/2015.
 */
public class AdminMaterias extends AppCompatActivity {

    private EditText nombreMateria;
    private ListView listaMaterias;
    private List<String> listaNombreMaterias;
    private List<Materia> materias;
    private ArrayAdapter adaptador;
    private String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_materias);
        iniciarEntorno();
    }

    private void iniciarEntorno() {
        buscarElementos();
        recogerParametro();
        listaNombreMaterias = new ArrayList<>();
        materias = new ArrayList<>();
        listaMaterias.setOnTouchListener(new OnTouchEvent());

        findViewById(R.id.buttonAgregarMateria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarMateria();
            }
        });

        findViewById(R.id.buttonQuitarMateria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        findViewById(R.id.buttonGuardarMaterias).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultasBase consulta = new ConsultasBase();
                consulta.execute(1);
            }
        });

        findViewById(R.id.buttonRegresarMenuMateria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMaterias.this, MenuProfesor.class);
                intent.putExtra("cursoID", curso);
                startActivity(intent);
            }
        });
    }


    private void buscarElementos() {
        listaMaterias = (ListView) findViewById(R.id.listViewMaterias);
        nombreMateria = (EditText) findViewById(R.id.editTextNombreMateria);
    }


    private void agregarMateria() {
        String nombre = nombreMateria.getText().toString();
        if (nombre.equals("")) {
            Toast mensaje = Toast.makeText(getApplicationContext(), "Debe Llenar el Campo Nombre Materia ", Toast.LENGTH_LONG);
            mensaje.show();
        } else {
            listaNombreMaterias.add(nombre);
            adaptador = new ArrayAdapter(
                    this, android.R.layout.simple_expandable_list_item_1, listaNombreMaterias
            );
            listaMaterias.setAdapter(adaptador);
            materias.add(new Materia(nombre, curso));
        }
        limpiar();
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        curso = "";
        if (extra != null) {
            curso = (String) extra.get("cursoID");
            Log.i("cursoID", curso);
        }
    }

    private void limpiar() {
        nombreMateria.setText("");
    }

    private class ConsultasBase extends AsyncTask<Integer, Void, String> {

        public ConsultasBase() {
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (Materia m : materias) {
                m.registrarMateria();
            }
            return "Correcto";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            finish();
            Intent intent = new Intent(AdminMaterias.this, MenuProfesor.class);
            intent.putExtra("cursoID", curso);
            startActivity(intent);
            Toast mensaje = Toast.makeText(getApplicationContext(), "Las Materias han sido registradas", Toast.LENGTH_LONG);
            mensaje.show();

        }
    }
}

