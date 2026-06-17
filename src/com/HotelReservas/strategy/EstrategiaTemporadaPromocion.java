package com.HotelReservas.strategy;

public class EstrategiaTemporadaPromocion implements EstrategiaPrecio {

    private static final double DESCUENTO = 0.40;

    @Override
    public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
        return precioPorNoche * cantidadNoches * (1 - DESCUENTO);
    }

    @Override
    public String getNombre() {
        return "Promoción (40% descuento)";
    }
}