package com.HotelReservas.adapter;


public class EmailAdapter implements ServicioEmail {

    @Override
    public void enviarConfirmacion(String destinatario, String mensaje) {
        System.out.println("[Email a " + destinatario + "]: " + mensaje);
    }
}