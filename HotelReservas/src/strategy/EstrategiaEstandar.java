package com.HotelReservas.strategy;
 
public class EstrategiaEstandar implements EstrategiaPrecio {
 
   @Override
   public double calcularPrecio(double precioPorNoche, long cantidadNoches) {
       return precioPorNoche * cantidadNoches;
   }
 
   @Override
   public String getNombre() {
       return "Estándar (sin descuento)";
   }
}


