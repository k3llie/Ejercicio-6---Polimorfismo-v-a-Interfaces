public class CertificadorFEL extends IntegracionAPI {

    private String nitEmisor;

    public CertificadorFEL(String identificador, String nombre, String urlBase, String nitEmisor) {

        super(identificador, nombre, urlBase);

        this.nitEmisor = nitEmisor;
    }

    @Override
    public String generarDocumento(String datos) {
        if (!isActiva() || !isConectada()) {
            return "No se pudo generar la factura: " + "la integración está inactiva o desconectada.";
        }

        if (datos == null || datos.trim().isEmpty()) {
            return "Debes indicar los datos de la factura.";
        }

        if (nitEmisor == null || nitEmisor.trim().isEmpty()) {
            return "Debes configurar el NIT del emisor.";
        }

        return "FACTURA ELECTRÓNICA SIMULADA" + "\nCertificador: " + getNombre() + "\nNIT del emisor: " + nitEmisor + "\nDatos de la factura: " + datos;
    }

    @Override
    public String consultarEstado(String referencia) {
        if (!isActiva() || !isConectada()) {
            return "No se pudo consultar la certificación: " + "la integración está inactiva o desconectada.";
        }

        if (referencia == null || referencia.trim().isEmpty()) {
            return "Debes indicar la referencia de la certificación.";
        }

        return "Consulta simulada mediante " + getNombre() + " | Certificación: " + referencia + " | Estado simulado: certificada.";
    }

    public String getNitEmisor() {
        return nitEmisor;
    }
}