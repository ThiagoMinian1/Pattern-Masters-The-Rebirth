package com.HotelReservas.strategy;
 
public class EstrategiaTemporadaBaja implements EstrategiaPrecio {
 
   private static final double DESCUENTO = 0.30;
 
   @Override
   public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
       return precioPorNoche * cantidadNoches * (1 - DESCUENTO);
   }
 
   @Override
   public String getNombre() {
       return "Temporada Baja (30% descuento)";
   }
}
