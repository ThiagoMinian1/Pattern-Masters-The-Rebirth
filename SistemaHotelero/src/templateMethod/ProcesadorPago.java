package templateMethod;
import com.HotelReservas.Modelos.Reserva;

public abstract class ProcesadorPago {

    // Template Method — define el esqueleto del proceso
    public final void procesarPago(Reserva reserva) {
        double monto = reserva.calcularCostoTotal();
        validarDatos(reserva);
        iniciarTransaccion(monto);
        realizarPago(monto);
        confirmarPago(reserva, monto);
        emitirComprobante(reserva, monto);
    }

    // Pasos fijos
    private void iniciarTransaccion(double monto) {
        System.out.println("Iniciando transacción por $" + String.format("%.2f", monto));
    }

    private void confirmarPago(Reserva reserva, double monto) {
        System.out.println("Pago confirmado para reserva #" + reserva.getId()
                + " por $" + String.format("%.2f", monto));
    }

    private void emitirComprobante(Reserva reserva, double monto) {
        System.out.println("Comprobante emitido — Reserva #" + reserva.getId()
                + " | Método: " + getMetodoPago()
                + " | Total: $" + String.format("%.2f", monto));
    }

    // Pasos abstractos — cada subclase los implementa
    protected abstract void validarDatos(Reserva reserva);
    protected abstract void realizarPago(double monto);
    protected abstract String getMetodoPago();
}

