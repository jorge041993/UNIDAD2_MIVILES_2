package com.example.george.eva2_8_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    SQLiteDatabase sqlMiBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //ABRIR LA BASE DE DATOS, Y CREAR SI NO EXISTE
        sqlMiBD = openOrCreateDatabase("mi_base_datos", MODE_PRIVATE, null);
        //CREAR UNA TABLA
        try{
            sqlMiBD.execSQL("create table mitabla(" +
                    "id integr primary key autoincrement, \n" +
            "nombe text," +
            "apellido text" +
            ");");
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        //INSERTAR VALORES
       /* sqlMiBD.execSQL("insert into mitabla (nombre, apellido)" +
                "values ('JORGE' , 'SANCHEZ');");
        sqlMiBD.execSQL("insert into mitabla (nombre, apellido)" +
                "values ('MEMO' , 'VILLARREAL');");
        sqlMiBD.execSQL("insert into mitabla (nombre, apellido)" +
                "values ('MARITZA' , 'GUADALUPE');");*/
       //ELIMINAR DATOS
       //sqlMiBD.execSQL("delete from mitabla where id = 3");

        //UN CURSOR PARA ALMACENAR UNA CONSUSLTA
        Cursor c1 = sqlMiBD.rawQuery("select * from mitabla", null);
        c1.moveToFirst();
        while (!c1.isAfterLast()){
            Toast.makeText(this, c1.getString(c1.getColumnIndex("nombre"))+ " " +
                          c1.getString  (c1.getColumnIndex("apellido")),
                    Toast.LENGTH_SHORT).show();
            c1.moveToNext();
        }
    }
}
