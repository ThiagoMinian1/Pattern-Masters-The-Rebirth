package com.HotelReservas.state;

import com.HotelReservas.Modelos.Reserva;

public class EstadoConfirmado implements EstadoReserva {
    @Override
    public void confirmar(Reserva reserva) {
        System.out.println("La reserva ya está confirmada.");
    }
    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("Reserva #" + reserva.getId() + " cancelada.");
        reserva.setEstadoActual(new EstadoCancelado());
    }
    @Override
    public void checkIn(Reserva reserva) {
        System.out.println("Check-in realizado. Reserva activa.");
        reserva.setEstadoActual(new EstadoActivo());
    }
    @Override
    public void checkOut(Reserva reserva) {
        System.out.println("No se puede hacer check-out: el huésped no hizo check-in.");
    }
    @Override
    public String getNombre() { return "CONFIRMADO"; }
}