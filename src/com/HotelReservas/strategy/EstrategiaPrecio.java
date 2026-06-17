package com.HotelReservas.strategy;

public interface EstrategiaPrecio {
    double calcularPrecio(double precioPorNoche, long cantidadNoches);
    String getNombre();
}
