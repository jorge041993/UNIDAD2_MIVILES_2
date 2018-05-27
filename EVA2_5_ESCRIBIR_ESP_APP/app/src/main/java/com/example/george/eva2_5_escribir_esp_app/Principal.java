package com.example.george.eva2_5_escribir_esp_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Principal extends AppCompatActivity {
    EditText archivo;
    final String ARCHIVO= "MIARCHIVO.txt";

    @Override
    protected void onStart() {
        super.onStart();
        String sCade;
        try {
            InputStream isAbrir = openFileInput(ARCHIVO);
            InputStreamReader isLeer = new InputStreamReader(isAbrir);
            BufferedReader brLeerTexto = new BufferedReader(isLeer);
            while ((sCade = brLeerTexto.readLine()) !=null){
               archivo.append(sCade);
               archivo.append("\n");
            }
            brLeerTexto.close();
        }catch(IOException E){
            E.printStackTrace();

        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        archivo = (EditText)findViewById(R.id.archivo);


    }

    public void onClick (View v){
        String[] asCade= archivo.getText().toString().split("\n");
        try {
            OutputStream osAbrir = openFileOutput(ARCHIVO, 0);
            OutputStreamWriter oswEscribir = new OutputStreamWriter(osAbrir);
            BufferedWriter bwEscribir = new BufferedWriter(oswEscribir);
            for (int i=0; i<asCade.length; i++){
                bwEscribir.append(asCade[i]);
                bwEscribir.append("\n");
            }
            bwEscribir.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
