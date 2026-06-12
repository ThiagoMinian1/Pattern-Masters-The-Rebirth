package com.HotelReservas.decorator;
 
// Componente base que toda estadía decorable debe implementar
public interface EstadiaDecorable {
    double calcularCosto();
    String getDescripcion();
}