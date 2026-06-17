package com.HotelReservas.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Huesped {
    private String nombre;
    private String dni;
    private String email;
    private String categoria; // ESTANDAR, FRECUENTE, VIP

    private List<String> historialEstadias = new ArrayList<>();

    public Huesped(String nombre, String dni, String email, String categoria) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }
    public String getCategoria() { return categoria; }
    public List<String> getHistorialEstadias() { return historialEstadias; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public void agregarEstadia(String resumen) {
        historialEstadias.add(resumen);
    }

    public void mostrarHistorial() {
        String palabra = historialEstadias.size() == 1 ? "estadía" : "estadías";
        System.out.println(nombre + " ha tenido " + historialEstadias.size() + " " + palabra + ":");
        for (int i = 0; i < historialEstadias.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + historialEstadias.get(i));
        }
    }
}