package com.HotelReservas.adapter;

// Simula un servicio externo de email con interfaz incompatible
public class ApiEmailExterno {

    public void sendMail(String from, String to, String subject, String body) {
        System.out.println("Email enviado de " + from + " a " + to
                + " | Asunto: " + subject);
    }
}
