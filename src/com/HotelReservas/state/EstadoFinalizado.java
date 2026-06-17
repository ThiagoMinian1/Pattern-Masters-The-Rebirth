package com.HotelReservas.state;

import com.HotelReservas.Modelos.Reserva;

public class EstadoFinalizado implements EstadoReserva {
    @Override
    public void confirmar(Reserva reserva) {
        System.out.println("La reserva ya fue finalizada.");
    }
    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("No se puede cancelar una reserva finalizada.");
    }
    @Override
    public void checkIn(Reserva reserva) {
        System.out.println("La reserva ya fue finalizada.");
    }
    @Override
    public void checkOut(Reserva reserva) {
        System.out.println("La reserva ya fue finalizada.");
    }
    @Override
    public String getNombre() { return "FINALIZADO"; }
}