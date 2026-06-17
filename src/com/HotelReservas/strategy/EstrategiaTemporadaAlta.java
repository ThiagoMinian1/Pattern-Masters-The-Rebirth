package com.HotelReservas.strategy;

public class EstrategiaTemporadaAlta implements EstrategiaPrecio {

    private static final double RECARGO = 0.20;

    @Override
    public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
        return precioPorNoche * cantidadNoches * (1 + RECARGO);
    }

    @Override
    public String getNombre() {
        return "Temporada Alta (20% recargo)";
    }
}