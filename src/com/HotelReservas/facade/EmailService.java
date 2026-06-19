package com.HotelReservas.facade;

public class EmailService {

    public void enviarConfirmacion(String destinatario, String mensaje) {
        System.out.println("[Email a " + destinatario + "]: " + mensaje);
    }
}