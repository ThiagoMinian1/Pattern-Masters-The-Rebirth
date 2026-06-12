package com.HotelReservas.decorator;

public class SpaDecorator extends ServicioDecorator {

    private static final double COSTO = 50.0;

    public SpaDecorator(EstadiaDecorable estadia) {
        super(estadia);
    }

    @Override
    public double calcularCosto() {
        return estadia.calcularCosto() + COSTO;
    }

    @Override
    public String getDescripcion() {
        return estadia.getDescripcion() + " + Spa ($" + COSTO + ")";
    }
}