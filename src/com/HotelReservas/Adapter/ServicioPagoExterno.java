package com.HotelReservas.adapter;

// Interfaz que el sistema espera para procesar pagos externos
public interface ServicioPagoexterno {
    boolean procesarPago(String cliente, double monto);
}




