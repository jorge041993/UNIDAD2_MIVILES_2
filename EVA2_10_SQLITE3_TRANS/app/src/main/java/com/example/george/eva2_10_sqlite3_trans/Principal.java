package com.example.george.eva2_10_sqlite3_trans;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    TextView datos;
    SQLiteDatabase miBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        datos = (TextView) findViewById(R.id.datos);
        miBD = openOrCreateDatabase("prueva_trans", MODE_PRIVATE, null);

        try {
            miBD.execSQL("create table datos(" +
                    "id integr primary key autoincrement," +
                    "nombe text);");
        } catch (SQLiteException E) {
            E.printStackTrace();
        }
        //
        miBD.beginTransaction();
        try{
            miBD.execSQL("insert into datos (nombre)values('Jorge');");
            miBD.execSQL("insert into datos (nombre)values('Maritza');");
            miBD.execSQL("insert into datos (nombre)values('Memo');");
            miBD.execSQL("insert into datos (nombre)values('Blanca');");
            miBD.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            miBD.endTransaction();
        }
        Cursor cursor = miBD.rawQuery("select * from datos", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            datos.append(cursor.getString(cursor.getColumnIndex("nombre")));
            datos.append("\n");
            cursor.moveToNext();
        }

    }
}