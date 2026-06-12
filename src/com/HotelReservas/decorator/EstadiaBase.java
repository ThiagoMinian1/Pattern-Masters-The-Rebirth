package com.HotelReservas.decorator;

import com.HotelReservas.Modelos.Reserva;

// Componente concreto — wrappea una Reserva existente
public class EstadiaBase implements EstadiaDecorable {

    private Reserva reserva;

    public EstadiaBase(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public double calcularCosto() {
        return reserva.calcularCostoTotal();
    }

    @Override
    public String getDescripcion() {
        return "Estadía en habitación " + reserva.getHabitacion().getNumero()
            + " (" + reserva.getHabitacion().getTipo() + ")";
    }
}
