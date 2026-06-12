package com.HotelReservas.factory;

import com.HotelReservas.Modelos.Habitacion;

public class HabitacionDobleFactory extends HabitacionFactory {

    @Override
    public Habitacion crearHabitacion(int numero) {
        return new Habitacion(numero, "DOBLE", 2, 90.0);
    }
}