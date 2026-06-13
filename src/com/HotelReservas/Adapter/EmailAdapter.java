package com.HotelReservas.Adapter;

public class EmailAdapter implements ServicioEmail {

    private ApiEmailExterno apiExterna;
    private static final String REMITENTE = "hotel@hotelreservas.com";

    public EmailAdapter() {
        this.apiExterna = new ApiEmailExterno();
    }

    @Override
    public void enviarConfirmacion(String destinatario, String mensaje) {
        apiExterna.sendMail(REMITENTE, destinatario, "Confirmación de Reserva", mensaje);
    }
}
