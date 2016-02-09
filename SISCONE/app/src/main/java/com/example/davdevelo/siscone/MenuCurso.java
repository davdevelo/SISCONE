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
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apoyo.OnTouchEvent;
import moledos.Curso;
import moledos.Persona;

public class MenuCurso extends AppCompatActivity {

    private ArrayAdapter adaptador;
    private ListView cursos;
    private String cedula;
    private Persona usuario;
    private Map<String, String> mapaCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_curso);
        iniciarEntorno();
    }

    private void iniciarEntorno() {
        recogerParametro();
        findViewById(R.id.buttonRegistrarCurso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redireccionarRegistro();
            }
        });
        cursos = (ListView) findViewById(R.id.listViewCusros);
        cursos.setOnTouchListener(new OnTouchEvent());
        ConsultasCurso consulta = new ConsultasCurso(cedula);
        consulta.execute(1);

        cursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreCurso = (String) cursos.getItemAtPosition(position);
                String cursoId = mapaCursos.get(nombreCurso);
                Intent intent = new Intent(MenuCurso.this, MenuProfesor.class);
                intent.putExtra("cursoID", cursoId);
                intent.putExtra("usuario", cedula);
                intent.putExtra("persona", usuario);
                startActivity(intent);
            }
        });

    }

    private void redireccionarRegistro() {
        finish();
        Intent intentMF = new Intent(MenuCurso.this, RegistroCurso.class);
        intentMF.putExtra("cedulaProfesor", cedula);
        intentMF.putExtra("persona", usuario);
        startActivity(intentMF);
    }

    private void recogerParametro() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        cedula = "";
        if (extra != null) {
            cedula = (String) extra.get("usuario");
            usuario = (Persona) extra.get("persona");
            Log.i("representante", cedula);
        }
    }

    private class ConsultasCurso extends AsyncTask<Integer, Void, ResultSet> {
        private List<String> listadoCursos;
        private String cedula;
        private String nombreCurso;
        private String cursoId;

        public ConsultasCurso(String cedula) {
            this.cedula = cedula;
        }

        @Override
        protected ResultSet doInBackground(Integer... params) {
            ResultSet cursosConsultados;
            Persona persona = new Persona();
            cursosConsultados = persona.buscarCursoProfesor(cedula);
            return cursosConsultados;
        }

        @Override
        protected void onPostExecute(ResultSet resultado) {
            super.onPostExecute(resultado);
            listadoCursos = new ArrayList<>();
            mapaCursos = new HashMap<>();
            try {
                while (resultado.next()) {
                    nombreCurso = resultado.getString("nombre_curso");
                    cursoId = resultado.getString("id_curso");
                    listadoCursos.add(nombreCurso);
                    mapaCursos.put(nombreCurso, cursoId);
                }
                adaptador = new ArrayAdapter(
                        MenuCurso.this, android.R.layout.simple_expandable_list_item_1, listadoCursos
                );
                cursos.setAdapter(adaptador);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
