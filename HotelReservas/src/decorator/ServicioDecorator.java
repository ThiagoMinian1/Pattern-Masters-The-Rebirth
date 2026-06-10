package com.HotelReservas.decorator;

// Decorator abstracto — todos los servicios extra extienden de este
public abstract class ServicioDecorator implements EstadiaDecorable {

    protected EstadiaDecorable estadia;

    public ServicioDecorator(EstadiaDecorable estadia) {
        this.estadia = estadia;
    }

    @Override
    public double calcularCosto() {
        return estadia.calcularCosto();
    }

    @Override
    public String getDescripcion() {
        return estadia.getDescripcion();
    }
}