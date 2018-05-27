package com.example.george.eva2_4_files_resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Principal extends AppCompatActivity {
    TextView archivo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        archivo =(TextView)findViewById(R.id.archivo);
    }
    @Override
    protected void onStart() {
        super.onStart();
        InputStream isAbrir = getResources().openRawResource(R.raw.miarchivo);

        InputStreamReader isLeerBytes = new InputStreamReader(isAbrir);

        BufferedReader brLeerTexto = new BufferedReader(isLeerBytes);
        try {
            String sCade;

            while ((sCade = brLeerTexto.readLine())!=null){
                archivo.append(sCade);
                archivo.append("\n");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
