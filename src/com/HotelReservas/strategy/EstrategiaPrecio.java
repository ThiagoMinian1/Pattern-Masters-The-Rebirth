package com.HotelReservas.strategy;

public interface EstrategiaPrecio {

    public double calcularPrecio(double precioPorNoche, long cantidadNoches);
    public String getNombre();
}
