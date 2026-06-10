package com.HotelReservas.builder;


import com.HotelReservas.Modelos.Habitacion;
import com.HotelReservas.Modelos.Huesped;
import com.HotelReservas.Modelos.Reserva;
import com.HotelReservas.Modelos.ServicioExtra;
import com.HotelReservas.strategy.EstrategiaPrecio;
import com.HotelReservas.strategy.EstrategiaEstandar;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ReservaBuilder {


    // Campos obligatorios
    private int id;
    private Huesped huesped;
    private Habitacion habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;


    // Campos opcionales
    private EstrategiaPrecio estrategia = new EstrategiaEstandar();
    private List<ServicioExtra> servicios = new ArrayList<>();


    public ReservaBuilder(int id, Huesped huesped, Habitacion habitacion,
        LocalDate fechaIngreso, LocalDate fechaEgreso) {
        this.id = id;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }


    public ReservaBuilder conEstrategia(EstrategiaPrecio estrategia) {
        this.estrategia = estrategia;
        return this;
    }


    public ReservaBuilder conServicioExtra(ServicioExtra servicio) {
        this.servicios.add(servicio);
        return this;
    }


    public Reserva build() {
        Reserva reserva = new Reserva(id, huesped, habitacion, fechaIngreso, fechaEgreso);
        reserva.setEstrategia(estrategia);
        for (ServicioExtra s : servicios) {
            reserva.agregarServicioExtra(s);
        }
        return reserva;
    }
}
