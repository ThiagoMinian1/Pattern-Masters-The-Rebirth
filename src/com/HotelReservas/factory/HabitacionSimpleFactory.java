package com.HotelReservas.factory;

import com.HotelReservas.Modelos.Habitacion;

public class HabitacionSimpleFactory extends HabitacionFactory {

    @Override
    public Habitacion crearHabitacion(int numero) {
        return new Habitacion(numero, "SIMPLE", 1, 50.0);
    }
}
