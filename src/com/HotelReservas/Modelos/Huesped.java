package com.HotelReservas.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Huesped {

    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String categoria; // ESTANDAR, FRECUENTE, VIP

    private List<String> historialEstadias;

    public Huesped(String nombre, String dni, String email, String telefono, String categoria) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        if (dni == null || !dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos.");
        }

        if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            throw new IllegalArgumentException(
                    "Email inválido. Debe tener formato usuario@gmail.com");
        }

        if (telefono == null || !telefono.matches("\\d{10,11}")) {
            throw new IllegalArgumentException(
                    "Teléfono inválido. Debe tener 10 u 11 dígitos.");
        }

        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.categoria = categoria;
        this.historialEstadias = new ArrayList<>();
    }

    // GETTERS

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<String> getHistorialEstadias() {
        return historialEstadias;
    }

    // SETTERS

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido.");
        }
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            throw new IllegalArgumentException(
                    "Email inválido. Debe tener formato usuario@gmail.com");
        }
        this.email = email;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{10,11}")) {
            throw new IllegalArgumentException(
                    "Teléfono inválido. Debe tener 10 u 11 dígitos.");
        }
        this.telefono = telefono;
    }

    // HISTORIAL

    public void agregarEstadia(String resumen) {
        historialEstadias.add(resumen);
    }

    public void mostrarHistorial() {

        if (historialEstadias.isEmpty()) {
            System.out.println(nombre + " no posee estadías registradas.");
            return;
        }

        String palabra = historialEstadias.size() == 1 ? "estadía" : "estadías";

        System.out.println(
                nombre + " ha tenido " + historialEstadias.size() + " " + palabra + ":");

        for (int i = 0; i < historialEstadias.size(); i++) {
            System.out.println((i + 1) + ". " + historialEstadias.get(i));
        }
    }

    @Override
    public String toString() {
        return "Huesped{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}