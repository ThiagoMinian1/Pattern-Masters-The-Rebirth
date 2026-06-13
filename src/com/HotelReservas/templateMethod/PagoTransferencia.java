package com.HotelReservas.templateMethod;
import com.HotelReservas.Modelos.Reserva;

public class PagoTransferencia extends ProcesadorPago {

    private String cbu;
    private String alias;

    public PagoTransferencia(String cbu, String alias) {
        this.cbu = cbu;
        this.alias = alias;
    }

    @Override
    protected void validarDatos(Reserva reserva) {
        if (cbu == null || cbu.length() != 22)
            throw new IllegalArgumentException("CBU inválido.");
        System.out.println("Transferencia validada. CBU: " + cbu + " | Alias: " + alias);
    }

    @Override
    protected void realizarPago(double monto) {
        System.out.println("Transferencia bancaria enviada por $" + String.format("%.2f", monto)
                + " al alias: " + alias);
    }

    @Override
    protected String getMetodoPago() {
        return "Transferencia Bancaria";
    }
}
