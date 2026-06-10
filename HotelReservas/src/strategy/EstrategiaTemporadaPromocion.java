package com.HotelReservas.strategy;
 
public class EstrategiaTemporadaPromocion implements EstrategiaPrecio {
 
   private static final double DESCUENTO = 0.50;
 
   @Override
   public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
       return precioPorNoche * cantidadNoches * (1 - DESCUENTO);
   }
 
   @Override
   public String getNombre() {
       return "Promoción Especial (50% descuento)";
   }
}
