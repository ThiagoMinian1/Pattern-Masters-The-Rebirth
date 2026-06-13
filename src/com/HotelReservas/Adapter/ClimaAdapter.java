package com.HotelReservas.Adapter;

public class ClimaAdapter implements ServicioClima {

    private ApiClimaExterno apiExterna;

    public ClimaAdapter() {
        this.apiExterna = new ApiClimaExterno();
    }

    @Override
    public String obtenerClima(String ciudad) {
        String respuesta = apiExterna.getWeatherReport(ciudad, "metric");
        // Traduce la respuesta externa a algo legible para el sistema
        return "Clima en " + ciudad + ": Soleado, 22°C";
    }
}
