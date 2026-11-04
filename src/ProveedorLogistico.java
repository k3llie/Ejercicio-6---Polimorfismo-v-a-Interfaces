public class ProveedorLogistico extends IntegracionAPI {

    private double costoBase;
    private double tarifaPorKg;

    public ProveedorLogistico(String identificador, String nombre, String urlBase, double costoBase, double tarifaPorKg) {

        super(identificador, nombre, urlBase);

        this.costoBase = costoBase;
        this.tarifaPorKg = tarifaPorKg;
    }

    // Devuelve -1 cuando no se puede realizar la cotización.
    public double cotizarEnvio(double pesoKg) {
        if (!isActiva() || !isConectada()) {
            return -1;
        }

        if (!Double.isFinite(pesoKg) || pesoKg <= 0) {
            return -1;
        }

        return costoBase + tarifaPorKg * pesoKg;
    }

    @Override
    public String consultarEstado(String referencia) {
        if (!isActiva() || !isConectada()) {
            return "No se pudo consultar el envío: " + "la integración está inactiva o desconectada.";
        }

        if (referencia == null || referencia.trim().isEmpty()) {
            return "Debes indicar la referencia del envío.";
        }

        return "Consulta simulada mediante " + getNombre() + " | Envío: " + referencia + " | Estado simulado: en tránsito.";
    }

    @Override
    public String generarDocumento(String datos) {
        if (!isActiva() || !isConectada()) {
            return "No se pudo generar la guía: " + "la integración está inactiva o desconectada.";
        }

        if (datos == null || datos.trim().isEmpty()) {
            return "Debes indicar los datos del envío.";
        }

        return "GUÍA DE ENVÍO SIMULADA" + "\nProveedor: " + getNombre() + "\nIdentificador de integración: " + getIdentificador() + "\nDatos del envío: " + datos;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public double getTarifaPorKg() {
        return tarifaPorKg;
    }
}