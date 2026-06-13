package com.HotelReservas.Adapter;

// Interfaz que el sistema espera para enviar emails
public interface ServicioEmail {
    void enviarConfirmacion(String destinatario, String mensaje);
}
