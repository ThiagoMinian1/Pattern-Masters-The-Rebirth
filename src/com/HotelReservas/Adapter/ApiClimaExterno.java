package com.HotelReservas.adapter;

// Simula una API externa de clima con interfaz incompatible
public class ApiClimaExterno {

    public String getWeatherReport(String location, String units) {
        // Simula respuesta de API externa
        return "{ location: " + location + ", temp: 22, units: " + units + ", condition: Soleado }";
    }
}
