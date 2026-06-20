package com.HotelReservas.state;

import com.HotelReservas.Modelos.Reserva;

public class EstadoPendiente implements EstadoReserva {

    @Override
    public void confirmar(Reserva reserva) {
        // FIX: solo cambia el estado, NO ocupa la habitación
        // La habitación se ocupa recién en el checkIn
        System.out.println("Reserva #" + reserva.getId() + " confirmada.");
        reserva.setEstadoActual(new EstadoConfirmado());
    }

    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("Reserva #" + reserva.getId() + " cancelada.");
        reserva.setEstadoActual(new EstadoCancelado());
        // Nota: Reserva.cancelar() llama habitacion.liberar() después de esto
    }

    @Override
    public void checkIn(Reserva reserva) {
        System.out.println("No se puede hacer check-in: la reserva no está confirmada.");
    }

    @Override
    public void checkOut(Reserva reserva) {
        System.out.println("No se puede hacer check-out: la reserva no está activa.");
    }

    @Override
    public String getNombre() { return "PENDIENTE"; }
}