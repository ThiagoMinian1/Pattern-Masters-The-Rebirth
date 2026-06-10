package com.HotelReservas.Modelos;

import com.HotelReservas.observer.Observador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {

    private int numero;
    private String tipo;
    private int capacidad;
    private double precioPorNoche;
    private boolean disponible;
    private LocalDate fechaOcupadaDesde;
    private LocalDate fechaOcupadaHasta;

    private List<Observador> listaEspera = new ArrayList<>();

    public Habitacion(int numero, String tipo, int capacidad, double precioPorNoche) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioPorNoche = precioPorNoche;
        this.disponible = true;
    }

    // ── Observer ──────────────────────────────────────────────────────────────

    public void suscribir(Observador observador) {
        listaEspera.add(observador);
    }

    public void desuscribir(Observador observador) {
        listaEspera.remove(observador);
    }

    private void notificarObservadores() {
        for (Observador o : listaEspera) {
            o.actualizar(this);
        }
    }

    // ── Disponibilidad ────────────────────────────────────────────────────────

    public void ocupar(LocalDate desde, LocalDate hasta) {  // corregido: String -> LocalDate
        this.disponible = false;
        this.fechaOcupadaDesde = desde;
        this.fechaOcupadaHasta = hasta;
    }

    public void liberar() {
        this.disponible = true;
        this.fechaOcupadaDesde = null;
        this.fechaOcupadaHasta = null;
        notificarObservadores();
    }

    public boolean estaDisponible(LocalDate desde, LocalDate hasta) {
        if (disponible) return true;
        return hasta.isBefore(fechaOcupadaDesde) || desde.isAfter(fechaOcupadaHasta);
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────

    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public int getCapacidad() { return capacidad; }
    public double getPrecioPorNoche() { return precioPorNoche; }
    public boolean isDisponible() { return disponible; }
    public LocalDate getFechaOcupadaDesde() { return fechaOcupadaDesde; }
    public LocalDate getFechaOcupadaHasta() { return fechaOcupadaHasta; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}