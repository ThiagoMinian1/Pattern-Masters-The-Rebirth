package com.HotelReservas.observer;

import com.HotelReservas.Modelos.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class ListaEspera {

    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(Habitacion habitacion) {
        for (Observador observador : observadores) {
            observador.actualizar(habitacion);
        }
    }

    public void publicarAviso(String s) {
    }
}