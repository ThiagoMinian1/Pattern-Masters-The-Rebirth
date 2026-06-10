package com.HotelReservas.strategy;
 
public class EstrategiaTemporadaAlta implements EstrategiaPrecio {
 
   @Override
   public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
       return precioPorNoche * cantidadNoches;
   }
 
   @Override
   public String getNombre() {
       return "Temporada Alta (sin descuento)";
   }
}
