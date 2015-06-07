package com.example.alejndro.mi_bd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alejndro on 02/06/2015.
 */
public class adaptador extends RecyclerView.Adapter<adaptador.datosViewHolder> {

    //Arreglo llamado del java modelo material
    private List<modelo> items;

    //Clase material View Holder
    public static class datosViewHolder extends RecyclerView.ViewHolder {

        //Variables TextView utilizadas
        public TextView clave;
        public TextView asunto;
        public TextView descripcion;
        public TextView fecha;
        public TextView lugar;

        //Enlazar variables con el elemento grafico correspondiente
        public datosViewHolder(View v) {
            super(v);
            clave = (TextView) v.findViewById(R.id.d1);
            asunto = (TextView) v.findViewById(R.id.d2);
            descripcion = (TextView) v.findViewById(R.id.d3);
            fecha = (TextView) v.findViewById(R.id.d4);
            lugar = (TextView) v.findViewById(R.id.d5);
        }
    }

    //Acomoda los TextView en un arreglo
    public adaptador(List<modelo> items) {
        this.items = items;
    }

    //Metodo que crea el arreglo
    @Override
    public datosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new datosViewHolder(v);
    }

    //Metodo que crea la lisa del arreglo obteniendo informacion del modelo material
    @Override
    public void onBindViewHolder(datosViewHolder viewHolder, int i) {
        //el String.valuOf
        viewHolder.clave.setText(items.get(i).getClave());
        viewHolder.asunto.setText(items.get(i).getAsunto());
        viewHolder.descripcion.setText(items.get(i).getDescripcion());
        viewHolder.fecha.setText(items.get(i).getFecha());
        viewHolder.lugar.setText(items.get(i).getLugar());
    }

    @Override
    public int getItemCount() {
        return  items.size();
    }

}
