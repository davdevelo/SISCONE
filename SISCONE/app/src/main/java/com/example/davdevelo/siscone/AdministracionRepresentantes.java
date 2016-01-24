package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AdministracionRepresentantes extends AppCompatActivity {

    private EditText cedulaRepresentante;
    private EditText nombreEstudiante;
    private EditText apellidoEstudiante;
    private EditText elementos[];
    private ArrayAdapter adaptador;
    private List<String> listaAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_representantes);
    }
    private void iniciarEntrono(){
        listaAlumnos = new ArrayList<>();
        buscarElementos();
        findViewById(R.id.buttonRegitrarNuevoRepresentante).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministracionRepresentantes.this, RegistroRepresentante.class);
                startActivity(intent);
            }
        });
    }
    private void buscarElementos(){
        cedulaRepresentante = (EditText) findViewById(R.id.editTextCedulaRepresentanteAR);
        nombreEstudiante = (EditText) findViewById(R.id.editTextNombreEstudianteAR);
        apellidoEstudiante = (EditText) findViewById((R.id.editTextApellidoEstudianteAR));

    }

    private void agregarAlumno(){
        listaAlumnos.add(apellidoEstudiante.getText().toString());
        adaptador = new ArrayAdapter(
                this,android.R.layout.simple_expandable_list_item_1, listaAlumnos
        );
        //alumnos.setAdapter(adaptador);
        //alumno.setText("");
    }


}
