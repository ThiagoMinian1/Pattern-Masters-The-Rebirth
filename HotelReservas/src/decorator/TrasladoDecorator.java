package com.HotelReservas.decorator;

public class TrasladoDecorator extends ServicioDecorator {

    private static final double COSTO = 30.0;

    public TrasladoDecorator(EstadiaDecorable estadia) {
        super(estadia);
    }

    @Override
    public double calcularCosto() {
        return estadia.calcularCosto() + COSTO;
    }

    @Override
    public String getDescripcion() {
        return estadia.getDescripcion() + " + Traslado ($" + COSTO + ")";
    }
}