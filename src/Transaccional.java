public interface Transaccional {

    default String procesarCobro(double monto) {
        return "Cobro ignorado: esta integración no permite realizar cobros.";
    }

    default String procesarReintegro(double monto) {
        return "Reintegro ignorado: esta integración no permite realizar reintegros.";
    }
}