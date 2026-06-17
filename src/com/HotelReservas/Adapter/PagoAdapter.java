package com.HotelReservas.adapter;

public class PagoAdapter implements ServicioPagoexterno {

    private ApiPasarelaPago pasarela;

    public PagoAdapter() {
        this.pasarela = new ApiPasarelaPago();
    }

    @Override
    public boolean procesarPago(String cliente, double monto) {
        String resultado = pasarela.executePayment(cliente, monto, "ARS");
        return "APPROVED".equals(resultado);
    }
}
