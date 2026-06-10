package com.HotelReservas.observer;
 
import com.HotelReservas.Modelos.Habitacion;
import com.HotelReservas.Modelos.Huesped;
 
public class HuespedObservador implements Observador {
 
    private Huesped huesped;
 
    public HuespedObservador(Huesped huesped) {
        this.huesped = huesped;
    }
 
    @Override
    public void actualizar(Habitacion habitacion) {
        System.out.println("Notificación para " + huesped.getNombre()
            + ": la habitación " + habitacion.getNumero()
            + " (" + habitacion.getTipo() + ") ya está disponible.");
    }
 
    public Huesped getHuesped() {
        return huesped;
    }
}
