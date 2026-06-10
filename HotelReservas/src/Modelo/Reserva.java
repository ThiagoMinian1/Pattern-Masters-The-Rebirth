package com.HotelReservas.Modelos;

import com.HotelReservas.state.EstadoReserva;
import com.HotelReservas.strategy.EstrategiaPrecio;

import java.time.temporal.ChronoUnit;

import com.HotelReservas.state.EstadoPendiente;
import com.HotelReservas.strategy.EstrategiaEstandar;


public class Reserva {
    private int id;
    private Huesped huesped;
    private Habitacion habitacion;
    private String fechaIngreso;
    private String fechaEgreso;
    private EstadoReserva estadoActual;
    private ServicioExtra[] serviciosExtra;
    private int cantidadServicios;
    private EstrategiaPrecio estrategia;


    public Reserva(int id, Huesped huesped, Habitacion habitacion,
        String fechaIngreso, String fechaEgreso) {
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

    public int getId() { return id; }
    public Huesped getHuesped() { return huesped; }
    public Habitacion getHabitacion() { return habitacion; }
    public String getFechaIngreso() { return fechaIngreso; }
    public String getFechaEgreso() { return fechaEgreso; }
    public EstadoReserva getEstadoActual() { return estadoActual; }
    public String getEstado() { return estadoActual.getNombre(); }
    public ServicioExtra[] getServiciosExtra() { return serviciosExtra; }
    public int getCantidadServicios() { return cantidadServicios; }

    public void setEstadoActual(EstadoReserva estado) { this.estadoActual = estado; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public void setFechaEgreso(String fechaEgreso) { this.fechaEgreso = fechaEgreso; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }
    public void setEstrategia(EstrategiaPrecio estrategia) { this.estrategia = estrategia;}



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


    public void cancelar(ListaEspera lista) {
        estadoActual.cancelar(this);
        habitacion.liberar();
        lista.publicarAviso("La habitacion " + habitacion.getNumero() + 
                            " tipo " + habitacion.getTipo() + " está disponible.");
    }

}