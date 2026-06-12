package com.HotelReservas.factory;

import com.HotelReservas.Modelos.Habitacion;

public class HabitacionSuitePresidencialFactory extends HabitacionFactory {

    @Override
    public Habitacion crearHabitacion(int numero) {
        return new Habitacion(numero, "SUITE PRESIDENCIAL", 4, 500.0);
    }
}