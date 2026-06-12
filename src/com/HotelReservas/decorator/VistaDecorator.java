package com.HotelReservas.decorator;
 
public class VistaDecorator extends ServicioDecorator {
 
    private static final double COSTO = 25.0;
 
    public VistaDecorator(EstadiaDecorable estadia) {
        super(estadia);
    }
 
    @Override
    public double calcularCosto() {
        return estadia.calcularCosto() + COSTO;
    }
 
    @Override
    public String getDescripcion() {
        return estadia.getDescripcion() + " + Vista al mar ($" + COSTO + ")";
    }
}