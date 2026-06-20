package com.HotelReservas.observer;

import com.HotelReservas.Modelos.Habitacion;
import com.HotelReservas.Modelos.Huesped;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern — Subject (sujeto observable).
 * Gestiona la cola de huéspedes que esperan una habitación.
 * Cuando la habitación se libera, notifica a todos los observadores en orden.
 *
 * También implementa Observador para poder suscribirse a una Habitacion
 * y reaccionar cuando se libere.
 */
public class ListaEspera implements Observador {

    private Habitacion habitacion;
    private List<HuespedObservador> cola = new ArrayList<>();

    public ListaEspera(Habitacion habitacion) {
        this.habitacion = habitacion;
        // Se suscribe como observador de la habitación
        habitacion.suscribir(this);
    }

    public void agregarHuesped(Huesped huesped) {
        cola.add(new HuespedObservador(huesped));
        System.out.println(huesped.getNombre() + " agregado a la lista de espera "
                + "para habitación " + habitacion.getNumero()
                + " (posición " + cola.size() + ").");
    }

    public void quitarHuesped(Huesped huesped) {
        cola.removeIf(o -> o.getHuesped() == huesped);
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int getCantidad() {
        return cola.size();
    }

    /**
     * FIX 5: publicarAviso ya no está vacío.
     * Se llama cuando la habitación se libera: notifica a todos en la cola.
     */
    public void publicarAviso(String mensaje) {
        System.out.println("[Lista de Espera — Hab. " + habitacion.getNumero() + "]: " + mensaje);
        for (HuespedObservador obs : cola) {
            obs.actualizar(habitacion);
        }
    }

    /**
     * FIX 4: implementa Observador.actualizar() — se llama automáticamente
     * cuando Habitacion.liberar() notifica a sus observers.
     */
    @Override
    public void actualizar(Habitacion habitacion) {
        if (!cola.isEmpty()) {
            publicarAviso("La habitación " + habitacion.getNumero()
                    + " (" + habitacion.getTipo() + ") ya está disponible.");
        }
    }
}