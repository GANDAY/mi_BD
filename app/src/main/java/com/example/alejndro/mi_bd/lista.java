package com.example.alejndro.mi_bd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class lista extends ActionBarActivity {


    //variable dle recycler view
    private RecyclerView recycler;
    //variable del adaptador recycler view
    private RecyclerView.Adapter adapter;
    //variable del administrador de recycler view
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        //Arreglo obtenido del modelo material
        List<modelo> items = new ArrayList<>();

        //Establece la conexion con la tabla de la base de datos
        ConexionSQL funcion = new ConexionSQL(this, "pendientes", null, 1);
        SQLiteDatabase BD = funcion.getWritableDatabase();
        //Ejecuta el comando select en mysql
        Cursor fila = BD.rawQuery("select clave,asunto,descripcion,fecha,lugar from pendientes", null);
        //El resultado del comando es buscado en cada uno de los registros
        for (fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()) {
            items.add(new modelo(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4)));
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.lista);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new adaptador(items);
        recycler.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
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
