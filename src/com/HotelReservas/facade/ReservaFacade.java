package com.HotelReservas.facade;

import com.HotelReservas.Modelos.Reserva;
import com.HotelReservas.templateMethod.ProcesadorPago;

public class ReservaFacade {

    private EmailService email;

    public ReservaFacade() {
        this.email = new EmailService();
    }

    public void checkIn(Reserva reserva) {
        reserva.checkIn();
        email.enviarConfirmacion(
                reserva.getHuesped().getEmail(),
                "Bienvenido/a " + reserva.getHuesped().getNombre()
                        + "! Su check-in para la habitación "
                        + reserva.getHabitacion().getNumero() + " fue realizado con éxito."
        );
        System.out.println("CheckIn completado — Reserva #" + reserva.getId());
    }

    public void checkOut(Reserva reserva, ProcesadorPago procesadorPago) {
        procesadorPago.procesarPago(reserva);
        reserva.checkOut();
        email.enviarConfirmacion(
                reserva.getHuesped().getEmail(),
                "Gracias " + reserva.getHuesped().getNombre()
                        + "! Su check-out fue realizado. Total cobrado: $"
                        + String.format("%.2f", reserva.calcularCostoTotal())
        );
        System.out.println("CheckOut completado — Reserva #" + reserva.getId());
    }
}