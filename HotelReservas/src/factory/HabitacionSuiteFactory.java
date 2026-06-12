package com.HotelReservas.factory;

import com.HotelReservas.Modelos.Habitacion;

public class HabitacionSuiteFactory extends HabitacionFactory {

    @Override
    public Habitacion crearHabitacion(int numero) {
        return new Habitacion(numero, "SUITE", 3, 200.0);
    }
}