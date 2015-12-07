package com.example.davdevelo.siscone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import moledos.Login;
import moledos.Persona;
import moledos.Representante;

public class RegistroRepresentante extends AppCompatActivity {

    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText correo;
    private EditText alumno;
    private ListView alumnos;
    private ArrayAdapter adaptador;
    private List<String> listaAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_representante);

        buscarElementos();
        listaAlumnos = new ArrayList<>();

        findViewById(R.id.buttonRegresarMenuProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroRepresentante.this, MenuProfesor.class));
            }
        });

        findViewById(R.id.buttonAgregarAlumno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAlumno();
            }
        });


    }

    private void buscarElementos(){
        cedula = (EditText) findViewById(R.id.editCedulaRepresentante);
        nombre = (EditText) findViewById(R.id.editNombreRepresentante);
        apellido = (EditText) findViewById(R.id.editApellidoRepresentante);
        correo = (EditText) findViewById(R.id.editCorreoRepresentante);
        alumno = (EditText) findViewById(R.id.editNombreAlumno);
        alumnos = (ListView) findViewById(R.id.listAlumnosRepresentante);
    }

    private void agregarAlumno(){
        listaAlumnos.add(alumno.getText().toString());
        adaptador = new ArrayAdapter(
                this,android.R.layout.simple_expandable_list_item_1, listaAlumnos
        );
        alumnos.setAdapter(adaptador);
        alumno.setText("");
    }

    private Object[] recuperrarDatos(){

        List<String> alumnosRepresentante = new ArrayList<>();

        for(int i=0; i<alumnos.getAdapter().getCount();i++){
            alumnosRepresentante.add((alumnos.getAdapter().getItem(i).toString()));
        }

        Representante representante = new Representante(
                cedula.getText().toString(),
                nombre.getText().toString(),
                apellido.getText().toString(),
                correo.getText().toString(),
                "12345"
                , "Profesor",alumnosRepresentante);

        Login login = new Login(
                cedula.getText().toString(),
                "12345",
                "Representate");

        Object datos[] = new Object[]{representante,login};
        return datos;
    }

    private void registrarRepresentante(){

    }


}
