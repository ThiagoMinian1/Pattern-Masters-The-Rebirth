package com.HotelReservas.strategy;

public class EstrategiaTemporadaMedia implements EstrategiaPrecio {

    @Override
    public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
        return precioPorNoche * cantidadNoches;
    }

    @Override
    public String getNombre() {
        return "Temporada Media (precio base)";
    }
}