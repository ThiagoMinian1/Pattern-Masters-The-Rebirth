package com.HotelReservas.Modelos;

public class ServicioExtra {
    private String nombre;
    private String tipo;        // desayuno, spa, traslado, etc.
    private double costoAdicional;
    private String temporada;   // ALTA, BAJA, TODAS

    public ServicioExtra(String nombre, String tipo, double costoAdicional, String temporada) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.costoAdicional = costoAdicional;
        this.temporada = temporada;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public double getCostoAdicional() { return costoAdicional; }
    public String getTemporada() { return temporada; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCostoAdicional(double costo) { this.costoAdicional = costo; }
    public void setTemporada(String temporada) { this.temporada = temporada; }
}