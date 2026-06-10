public package com.HotelReservas.strategy;
 
public class EstrategiaFrecuente implements EstrategiaPrecio {
 
   private static final double DESCUENTO = 0.10;
 
   @Override
   public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
       return precioPorNoche * cantidadNoches * (1 - DESCUENTO);
   }
 
   @Override
   public String getNombre() {
       return "Frecuente (10% descuento)";
   }
}

