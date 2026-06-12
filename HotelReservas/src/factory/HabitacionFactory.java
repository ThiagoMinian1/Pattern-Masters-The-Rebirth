package com.HotelReservas.factory;

import com.HotelReservas.Modelos.Habitacion;

public abstract class HabitacionFactory {

    // Factory Method
    public abstract Habitacion crearHabitacion(int numero);
}