package com.HotelReservas.decorator;

public class DesayunoDecorator extends ServicioDecorator {

    private static final double COSTO = 15.0;

    public DesayunoDecorator(EstadiaDecorable estadia) {
        super(estadia);
    }

    @Override
    public double calcularCosto() {
        return estadia.calcularCosto() + COSTO;
    }

    @Override
    public String getDescripcion() {
        return estadia.getDescripcion() + " + Desayuno ($" + COSTO + ")";
    }
}