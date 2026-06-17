package com.HotelReservas.state;

import com.HotelReservas.Modelos.Reserva;

public interface EstadoReserva {
    void confirmar(Reserva reserva);
    void cancelar(Reserva reserva);
    void checkIn(Reserva reserva);
    void checkOut(Reserva reserva);
    String getNombre();
}