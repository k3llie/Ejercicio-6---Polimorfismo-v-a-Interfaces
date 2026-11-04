public class PasarelaPago extends IntegracionAPI {

    private String moneda;
    private double porcentajeComision;

    public PasarelaPago(String identificador, String nombre, String urlBase, String moneda, double porcentajeComision) {

        super(identificador, nombre, urlBase);

        this.moneda = moneda;
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public String procesarCobro(double monto) {
        if (!isActiva() || !isConectada()) {
            return "No se pudo realizar el cobro: " + "la integración está inactiva o desconectada.";
        }

        if (!Double.isFinite(monto) || monto <= 0) {
            return "El monto del cobro debe ser un número positivo.";
        }

        double comision = monto * porcentajeComision / 100;

        return "Cobro simulado mediante " + getNombre() + " | Monto: " + monto + " " + moneda + " | Comisión: " + comision + " " + moneda;
    }

    @Override
    public String procesarReintegro(double monto) {
        if (!isActiva() || !isConectada()) {
            return "No se pudo realizar el reintegro: " + "la integración está inactiva o desconectada.";
        }

        if (!Double.isFinite(monto) || monto <= 0) {
            return "El monto del reintegro debe ser un número positivo.";
        }

        return "Reintegro simulado mediante " + getNombre() + " | Monto: " + monto + " " + moneda;
    }

    @Override
    public String consultarEstado(String referencia) {
        if (!isActiva() || !isConectada()) {
            return "No se pudo consultar la transacción: " + "la integración está inactiva o desconectada.";
        }

        if (referencia == null || referencia.trim().isEmpty()) {
            return "Debes indicar la referencia de la transacción.";
        }

        return "Consulta simulada mediante " + getNombre() + " | Transacción: " + referencia + " | Estado simulado: procesada.";
    }

    public String getMoneda() {
        return moneda;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }
}