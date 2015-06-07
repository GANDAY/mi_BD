package com.example.alejndro.mi_bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejndro on 01/06/2015.
 */
public class ConexionSQL extends SQLiteOpenHelper {
    public ConexionSQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory ,version);
    }

    //metodo para crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase BD){
        BD.execSQL("create table pendientes (clave integer primary key unique, asunto text, descripcion text, fecha text, lugar text)");
    }
    //metodo para reinstalas la aplicacion y borre y cre una nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase BD, int oldVersion, int newVersion) {
        BD.execSQL("drop table if exists pendientes");
        BD.execSQL("create table pendientes (clave integer primary key unique, asunto text, descripcion text, fecha text, lugar text)");
    }
}
