package com.example.george.eva2_7_objetos_archivos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Principal extends AppCompatActivity {
    EditText nombre, apellido, edad;
    CheckBox activo;
    RadioButton mas, fem, esp;
    TextView textViewDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        nombre = (EditText)findViewById(R.id.nombre);
        apellido = (EditText)findViewById(R.id.apellido);
        edad = (EditText)findViewById(R.id.edad);
        activo = (CheckBox)findViewById(R.id.activo);
        mas = (RadioButton)findViewById(R.id.mas);
        fem = (RadioButton)findViewById(R.id.fem);
        esp = (RadioButton)findViewById(R.id.esp);
        textViewDatos = (TextView)findViewById(R.id.textViewDatos);
    }
    public void Guardar (View v){
        String snom = nombre.getText().toString();
        String sape = apellido.getText().toString();
        int iedad = Integer.parseInt(edad.getText().toString());
        boolean bEstuAct= activo.isChecked();
        int isex;
        if (mas.isChecked()){
            isex=0;
        }else if (mas.isChecked()){
            isex=1;
        }else {
            isex=2;
        }
        Datos dDatos = new Datos(snom, sape, iedad, bEstuAct, isex );
        try{
            FileOutputStream fosAbrir = openFileOutput("mis_objetos.xxx", 0);
            ObjectOutputStream oosGuardarObj = new ObjectOutputStream(fosAbrir);
            oosGuardarObj.writeObject(dDatos);
            oosGuardarObj.writeObject(dDatos);
            oosGuardarObj.writeObject(dDatos);
            oosGuardarObj.close();
        }catch (IOException E){
            E.printStackTrace();
        }
    }
    public void Leer (View v){
        FileInputStream fisAbrir = null;
        ObjectInputStream iosLeer = null;
        try{
            fisAbrir = openFileInput("mis_objetos.xxx");
            iosLeer = new ObjectInputStream(fisAbrir);
            Datos dDatos = (Datos)iosLeer.readObject();
            while (true){
                textViewDatos.append(("Nombre:" + dDatos.getNombre()));
                textViewDatos.append(("Apellido:" + dDatos.getApellido()));
                textViewDatos.append(("Edad:" + dDatos.getEdad()));
                if (dDatos.isEstuArchivo())
                    textViewDatos.append("Estudiante Activo");
                else
                    textViewDatos.append("Estudiante inactivo");
                switch (dDatos.getGenero()){
                    case 0:
                        textViewDatos.append("Genero Masculino");
                        break;
                    case 1:
                        textViewDatos.append("Genero Famenino");
                        break;
                    default:
                        textViewDatos.append("No fue especifico");
                }
                dDatos= (Datos)iosLeer.readObject();
            }
        }catch (IOException E){
            E.printStackTrace();
        }catch (ClassNotFoundException E){
            E.printStackTrace();
        }finally {
            if (iosLeer !=null){
                try{
                    iosLeer.close();
                }catch (IOException E){
                    E.printStackTrace();
                }

            }
        }


    }
}

class  Datos implements Serializable{
    private String nombre;
    private String apellido;
    private int edad;
    private boolean estuArchivo;
    private int genero;

    public Datos(String nombre, String apellido, int edad, boolean estuArchivo, int genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.estuArchivo = estuArchivo;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getApellido() {

        return apellido;
    }

    public void setApellido(String apellido) {

        this.apellido = apellido;
    }

    public int getEdad() {

        return edad;
    }

    public void setEdad(int edad) {

        this.edad = edad;
    }

    public boolean isEstuArchivo() {

        return estuArchivo;
    }

    public void setEstuArchivo(boolean estuArchivo) {

        this.estuArchivo = estuArchivo;
    }

    public int getGenero() {

        return genero;
    }

    public void setGenero(int genero) {

        this.genero = genero;
    }
}


