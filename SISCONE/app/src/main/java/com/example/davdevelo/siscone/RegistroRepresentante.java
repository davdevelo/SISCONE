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
import momentario.Listas;

public class RegistroRepresentante extends AppCompatActivity {

    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText correo;
    private EditText alumno;
    private ListView alumnos;
    private EditText elementos[];
    private ArrayAdapter adaptador;
    private List<String> listaAlumnos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_representante);

        buscarElementos();
        elementos = new EditText[]{cedula, nombre, apellido, correo, alumno};
        listaAlumnos = new ArrayList<>();

        findViewById(R.id.buttonRegresarMenuProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegistroRepresentante.this, MenuProfesor.class));
            }
        });

        findViewById(R.id.buttonAgregarAlumno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAlumno();
            }
        });

        findViewById(R.id.buttonLimpiarForRegisRepr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
        findViewById(R.id.buttonRegistrarRepr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarRepresentante();
            }
        });
        findViewById(R.id.buttonRegresarMenuProf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroRepresentante.this, MenuProfesor.class));
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
                , "Representante",alumnosRepresentante);

        Login login = new Login(
                cedula.getText().toString(),
                "12345",
                "Representante");

        Object datos[] = new Object[]{representante,login};
        return datos;
    }

    private void limpiar() {
        for (EditText e : elementos) {
            e.setText("");
        }
        listaAlumnos = new ArrayList<>();
        adaptador = new ArrayAdapter(
                this,android.R.layout.simple_expandable_list_item_1, listaAlumnos
        );
        alumnos.setAdapter(adaptador);

    }

    private void registrarRepresentante(){
        Object datos [] = recuperrarDatos();
        Representante representante = (Representante) datos[0];
        Login login = (Login) datos[1];

        Listas.representantes.add(representante);
        Listas.registrados.add(login);
        limpiar();
    }

}
