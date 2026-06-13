package com.HotelReservas.templateMethod;
import com.HotelReservas.Modelos.Reserva;

public class PagoTarjeta extends ProcesadorPago {

    private String numeroTarjeta;
    private String titular;

    public PagoTarjeta(String numeroTarjeta, String titular) {
        this.numeroTarjeta = numeroTarjeta;
        this.titular = titular;
    }

    @Override
    protected void validarDatos(Reserva reserva) {
        if (numeroTarjeta == null || numeroTarjeta.length() != 16)
            throw new IllegalArgumentException("Número de tarjeta inválido.");
        System.out.println("Tarjeta validada para: " + titular);
    }

    @Override
    protected void realizarPago(double monto) {
        System.out.println("Procesando cobro en tarjeta **** "
                + numeroTarjeta.substring(12) + " por $" + String.format("%.2f", monto));
    }

    @Override
    protected String getMetodoPago() {
        return "Tarjeta de Crédito";
    }
}
