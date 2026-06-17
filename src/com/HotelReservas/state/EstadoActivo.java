package com.HotelReservas.state;

import com.HotelReservas.Modelos.Reserva;

public class EstadoActivo implements EstadoReserva {
    @Override
    public void confirmar(Reserva reserva) {
        System.out.println("La reserva ya está activa.");
    }
    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("No se puede cancelar una reserva activa.");
    }
    @Override
    public void checkIn(Reserva reserva) {
        System.out.println("El huésped ya hizo check-in.");
    }
    @Override
    public void checkOut(Reserva reserva) {
        System.out.println("Check-out realizado. Reserva finalizada.");
        reserva.setEstadoActual(new EstadoFinalizado());
    }
    @Override
    public String getNombre() { return "ACTIVO"; }
}