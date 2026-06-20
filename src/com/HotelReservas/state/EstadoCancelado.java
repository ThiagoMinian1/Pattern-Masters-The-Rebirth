package com.HotelReservas.state;

import com.HotelReservas.Modelos.Reserva;

public class EstadoCancelado implements EstadoReserva {

    @Override
    public void confirmar(Reserva reserva) {
        System.out.println("No se puede confirmar una reserva cancelada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("La reserva ya está cancelada.");
    }

    @Override
    public void checkIn(Reserva reserva) {
        System.out.println("No se puede hacer check-in en una reserva cancelada.");
    }

    @Override
    public void checkOut(Reserva reserva) {
        System.out.println("No se puede hacer check-out en una reserva cancelada.");
    }

    @Override
    public String getNombre() { return "CANCELADO"; }
}