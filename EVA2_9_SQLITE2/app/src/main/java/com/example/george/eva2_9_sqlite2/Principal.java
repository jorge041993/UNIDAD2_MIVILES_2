package com.example.george.eva2_9_sqlite2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    SQLiteDatabase miBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        miBD = openOrCreateDatabase("mi_base_datos", MODE_PRIVATE, null);

        try {
            //crear tabla
            miBD.execSQL("create table datos(" +
                    "datosID integr primary key autoincrement, \n" +
                    "nombe text," +
                    "apellido text" +
                    ");");
        }catch (SQLiteException E){
            E.printStackTrace();
        }
        ContentValues cvdatos = new ContentValues();
        cvdatos.put("nombre", "Jorge");
        cvdatos.put("apellido", "Sanchez");
        miBD.insert("datos", null, cvdatos);
        cvdatos.clear();
        cvdatos.put("nombre", "Memo");
        cvdatos.put("apellido", "Villarreal");
        miBD.insert("datos", null, cvdatos);
       cvdatos.clear();
        cvdatos.put("nombre", "Maritza");
        cvdatos.put("apellido", "Ordoñez");
        miBD.insert("datos", null, cvdatos);
        long iclave;
        iclave = miBD.insert("datos", null, cvdatos);
        cvdatos.clear();
        Toast.makeText(this, "ultima llave:"+ iclave, Toast.LENGTH_LONG).show();
        //actualizar un campo:
        cvdatos.put("nombre", "xxxxxx");
        cvdatos.put("apellido", "yyyyyy");
        String[] sArgs ={"jorge"};
        miBD.update("datos", cvdatos, "datosid = ?",sArgs);
        miBD.delete("datos", "nombre like ?", sArgs);
    }
}
