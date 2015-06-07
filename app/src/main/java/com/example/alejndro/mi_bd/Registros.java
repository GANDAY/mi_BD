package com.example.alejndro.mi_bd;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class Registros extends ActionBarActivity {

    EditText edtclave,edtasunto,edtdescripcion,edtfecha,edtlugar;

    Button btnfecha;

    int a,m,d;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

       // enlazar las variables con lo grafico
        edtclave = (EditText) findViewById(R.id.txtclave);
        edtasunto=(EditText) findViewById(R.id.txtasunto);
        edtdescripcion=(EditText) findViewById(R.id.txtdescripcion);
        edtfecha = (EditText) findViewById(R.id.txtfecha);
        edtlugar=(EditText)findViewById(R.id.txtlugar);

        btnfecha = (Button)findViewById(R.id.btnfecha);
    }

    public void calendario (View v) {
        //Variable del calendario
        Calendar calendario = Calendar.getInstance();
        //Valor a�o en variable a
        a = calendario.get(Calendar.YEAR);
        //Valor mes en variable m
        m = calendario.get(Calendar.MONTH);
        //Valor dia en variable d
        d = calendario.get(Calendar.DAY_OF_MONTH);

        //Evento clic al seleccionar fecha
        DatePickerDialog.OnDateSetListener mbpd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                a = year;
                m = monthOfYear;
                d = dayOfMonth;
                int uno = 1;
                //Mostrar fecha seleccionada en un editText
                edtfecha.setText(""+(m+uno)+"/"+d+"/"+a);
            }
        };
        DatePickerDialog dpd = new DatePickerDialog(this, mbpd, a, m, d);
        dpd.show();
    }

    public void registrar (View v) {
        // conecxion con la base de datos
        ConexionSQL funcion = new ConexionSQL(this, "pendientes", null, 1);
        SQLiteDatabase BD = funcion.getWritableDatabase();

        //variables a enlazar a lo grafico
        String clave = edtclave.getText().toString();
        String asunto = edtasunto.getText().toString();
        String descripcion = edtdescripcion.getText().toString();
        String fecha = edtfecha.getText().toString();
        String lugar = edtlugar.getText().toString();

            ContentValues registro = new ContentValues();

            registro.put("clave", clave);
            registro.put("asunto",asunto);
            registro.put("descripcion",descripcion);
            registro.put("fecha",fecha);
            registro.put("lugar",lugar);
            BD.insert("pendientes", null, registro);
            BD.close();

            edtclave.setText("");
            edtasunto.setText("");
            edtdescripcion.setText("");
            edtfecha.setText("");
            edtlugar.setText("");

            Toast.makeText(this,"Se agrego el registro del pendiente",Toast.LENGTH_SHORT).show();
        edtclave.setFocusable(true);
    }

    public void buscar (View v) {
        ConexionSQL funcion = new ConexionSQL(this, "pendientes", null, 1);
        SQLiteDatabase BD = funcion.getWritableDatabase();

        String clave = edtclave.getText().toString();

        Cursor fila = BD.rawQuery("select clave,asunto,descripcion,fecha,lugar from pendientes where clave=" + clave, null);
        if (fila.moveToFirst()) {
            edtclave.setText(fila.getString(0));
            edtasunto.setText(fila.getString(1));
            edtdescripcion.setText(fila.getString(2));
            edtfecha.setText(fila.getString(3));
            edtlugar.setText(fila.getString(4));
        } else {
            //Mensaje de eeror en la consulta
            Toast.makeText(this,"No existe el registro del pendiente",Toast.LENGTH_SHORT).show();
        }
        BD.close();
    }

    public void actualizar (View v) {
        ConexionSQL funcion = new ConexionSQL(this, "pendientes", null, 1);
        SQLiteDatabase BD = funcion.getWritableDatabase();

        String clave = edtclave.getText().toString();
        String asunto = edtasunto.getText().toString();
        String descripcion = edtdescripcion.getText().toString();
        String fecha = edtfecha.getText().toString();
        String lugar = edtlugar.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("clave", clave);
        registro.put("asunto", asunto);
        registro.put("descripcion", descripcion);
        registro.put("fecha", fecha);
        registro.put("lugar", lugar);

        int contador = BD.update("pendientes", registro, "clave="+clave, null);
        BD.close();

        if (contador == 1) {
            Toast.makeText(this, "Se modificado el registro del pendiente",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No existe el registro del pendiente",Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar (View v) {
        ConexionSQL funcion = new ConexionSQL(this, "pendientes", null, 1);
        SQLiteDatabase BD = funcion.getWritableDatabase();

        String clave = edtclave.getText().toString();

        int contador = BD.delete("pendientes", "clave="+ clave, null);
        BD.close();

        edtclave.setText("");
        edtasunto.setText("");
        edtdescripcion.setText("");
        edtfecha.setText("");
        edtlugar.setText("");

        if (contador == 1) {
            Toast.makeText(this, "El registro del pendiente ha sido borrado",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No existe el registro del pendiente",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
