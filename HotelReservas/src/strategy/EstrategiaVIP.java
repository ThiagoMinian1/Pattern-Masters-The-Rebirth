package com.HotelReservas.strategy;
 
public class EstrategiaVIP implements EstrategiaPrecio {
 
   private static final double DESCUENTO = 0.25;
 
   @Override
   public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
       return precioPorNoche * cantidadNoches * (1 - DESCUENTO);
   }
 
   @Override
   public String getNombre() {
       return "VIP (25% descuento)";
   }
}

