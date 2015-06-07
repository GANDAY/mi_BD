package com.example.alejndro.mi_bd;

/**
 * Created by Alejndro on 02/06/2015.
 */
public class modelo {

    private String clave;
    private String asunto;
    private String descripcion;
    private String fecha;
    private String lugar;

    public modelo(String clave, String asunto, String descripcion, String fecha, String lugar) {
        this.clave = clave;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public String getClave() {
        return clave;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }
}
