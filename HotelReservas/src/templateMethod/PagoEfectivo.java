package templateMethod;
import com.HotelReservas.Modelos.Reserva;

public class PagoEfectivo extends ProcesadorPago {

    private double montoEntregado;

    public PagoEfectivo(double montoEntregado) {
        this.montoEntregado = montoEntregado;
    }

    @Override
    protected void validarDatos(Reserva reserva) {
        double total = reserva.calcularCostoTotal();
        if (montoEntregado < total)
            throw new IllegalArgumentException("Monto insuficiente. Falta $"
                    + String.format("%.2f", total - montoEntregado));
        System.out.println("Efectivo validado. Monto entregado: $" + String.format("%.2f", montoEntregado));
    }

    @Override
    protected void realizarPago(double monto) {
        double vuelto = montoEntregado - monto;
        System.out.println("Pago en efectivo realizado. Vuelto: $" + String.format("%.2f", vuelto));
    }

    @Override
    protected String getMetodoPago() {
        return "Efectivo";
    }
}

