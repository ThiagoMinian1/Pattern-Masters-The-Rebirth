package com.HotelReservas.Modelos;

import com.HotelReservas.state.EstadoReserva;
import com.HotelReservas.state.EstadoPendiente;
import com.HotelReservas.strategy.EstrategiaPrecio;
import com.HotelReservas.strategy.EstrategiaEstandar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private int id;
    private Huesped huesped;
    private Habitacion habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private EstadoReserva estadoActual;
    private ServicioExtra[] serviciosExtra;
    private int cantidadServicios;
    private EstrategiaPrecio estrategia;

    public Reserva(int id, Huesped huesped, Habitacion habitacion,
                   LocalDate fechaIngreso, LocalDate fechaEgreso) {
        this.id = id;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.estadoActual = new EstadoPendiente();
        this.serviciosExtra = new ServicioExtra[50];
        this.cantidadServicios = 0;
        this.estrategia = new EstrategiaEstandar();
    }

    // Getters
    public int getId() { return id; }
    public Huesped getHuesped() { return huesped; }
    public Habitacion getHabitacion() { return habitacion; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaEgreso() { return fechaEgreso; }
    public EstadoReserva getEstadoActual() { return estadoActual; }
    public String getEstado() { return estadoActual.getNombre(); }
    public ServicioExtra[] getServiciosExtra() { return serviciosExtra; }
    public int getCantidadServicios() { return cantidadServicios; }

    // Setters
    public void setEstadoActual(EstadoReserva estado) { this.estadoActual = estado; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaEgreso(LocalDate fechaEgreso) { this.fechaEgreso = fechaEgreso; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }
    public void setEstrategia(EstrategiaPrecio estrategia) { this.estrategia = estrategia; }

    // Operaciones
    public void confirmar() {
        estadoActual.confirmar(this);
        habitacion.ocupar(fechaIngreso, fechaEgreso);
    }

    public void cancelar() {
        estadoActual.cancelar(this);
        habitacion.liberar();
    }

    public void checkIn() {
        estadoActual.checkIn(this);
    }

    public void checkOut() {
        estadoActual.checkOut(this);
        huesped.agregarEstadia(toString());
    }

    public void agregarServicioExtra(ServicioExtra servicio) {
        serviciosExtra[cantidadServicios] = servicio;
        cantidadServicios++;
    }

    public double calcularCostoTotal() {
        long noches = ChronoUnit.DAYS.between(fechaIngreso, fechaEgreso);
        double totalHabitacion = estrategia.calcularPrecio(habitacion.getPrecioPorNoche(), noches);
        double totalServicios = 0;
        for (int i = 0; i < cantidadServicios; i++) {
            totalServicios += serviciosExtra[i].getCostoAdicional();
        }
        return totalHabitacion + totalServicios;
    }
}