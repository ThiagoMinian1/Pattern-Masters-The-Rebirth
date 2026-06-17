package com.HotelReservas.adapter;

// Simula una pasarela de pago externa con interfaz incompatible
public class ApiPasarelaPago {

    public String executePayment(String customerId, double amount, String currency) {
        // Simula respuesta de pasarela externa
        return amount > 0 ? "APPROVED" : "REJECTED";
    }
}
