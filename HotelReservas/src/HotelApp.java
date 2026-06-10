public class HotelApp {

    public static void main(String[] args) {

        // =========================
        // FACTORY
        // =========================

        HabitacionFactory factory = new HabitacionSuiteFactory();
        Habitacion habitacion = factory.crearHabitacion();

        // =========================
        // BUILDER
        // =========================

        Huesped juan = new Huesped(
                "Juan Perez",
                "12345678",
                "juan@gmail.com",
                "VIP"
        );

        Reserva reserva = new ReservaBuilder()
                .setHuesped(juan)
                .setHabitacion(habitacion)
                .setFechaIngreso("10/06/2026")
                .setFechaSalida("15/06/2026")
                .build();

        System.out.println("Reserva creada");

        // =========================
        // STATE
        // =========================

        reserva.setEstado(new PendienteState());

        System.out.println(
                "Estado actual: "
                + reserva.getEstado().getNombre()
        );

        reserva.setEstado(new ConfirmadaState());

        System.out.println(
                "Nuevo estado: "
                + reserva.getEstado().getNombre()
        );

        // =========================
        // OBSERVER
        // =========================

        ListaEspera listaEspera = new ListaEspera();

        listaEspera.agregarObserver(
                new HuespedObserver("Maria")
        );

        listaEspera.agregarObserver(
                new HuespedObserver("Pedro")
        );

        // =========================
        // DECORATOR
        // =========================

        EstadiaDecorable estadia =
                new DesayunoDecorator(
                        new SpaDecorator(
                                new TrasladoDecorator(
                                        new EstadiaBase()
                                )
                        )
                );

        System.out.println(
                "Costo estadia: "
                + estadia.getCosto()
        );

        // =========================
        // STRATEGY
        // =========================

        CostoStrategy strategy =
                new ClienteVipStrategy();

        double precioFinal =
                strategy.calcularPrecio(
                        estadia.getCosto()
                );

        System.out.println(
                "Precio final VIP: "
                + precioFinal
        );

        // =========================
        // FACADE
        // =========================

        ReservaFacade facade =
                new ReservaFacade();

        facade.realizarCheckIn(reserva);

        facade.realizarCheckOut(reserva);

        // =========================
        // TEMPLATE METHOD
        // =========================

        Pago pago =
                new PagoTarjeta();

        pago.procesarPago();

        // =========================
        // ADAPTER
        // =========================

        ServicioReservaExterno booking =
                new BookingAdapter(
                        new BookingAPI()
                );

        booking.consultarDisponibilidad();

        // =========================
        // OBSERVER NOTIFICA
        // =========================

        listaEspera.notificarHuespedes();
    }
}