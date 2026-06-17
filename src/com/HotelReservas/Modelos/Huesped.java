package com.HotelReservas.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Huesped {

    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String categoria; // ESTANDAR, FRECUENTE, VIP

    private List<String> historialEstadias = new ArrayList<>();

    public Huesped(String nombre, String dni, String email, String telefono, String categoria) {
        this.nombre = nombre;
        this.dni = dni;
        this.categoria = categoria;

        setEmail(email);
        setTelefono(telefono);
    }

    // Getters
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

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            throw new IllegalArgumentException(
                    "Email inválido. Debe tener formato usuario@gmail.com"
            );
        }
        this.email = email;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{10,11}")) {
            throw new IllegalArgumentException(
                    "Teléfono inválido. Debe contener 10 u 11 dígitos."
            );
        }
        this.telefono = telefono;
    }

    // Historial de estadías
    public void agregarEstadia(String resumen) {
        historialEstadias.add(resumen);
    }

    public void mostrarHistorial() {
        String palabra = historialEstadias.size() == 1 ? "estadía" : "estadías";

        System.out.println(nombre + " ha tenido "
                + historialEstadias.size() + " "
                + palabra + ":");

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