import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorIntegraciones {

    private ArrayList<IntegracionAPI> integraciones;

    public GestorIntegraciones() {
        integraciones = new ArrayList<>();
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Integraciones de pagos.
        agregarIntegracion(new PasarelaPago("PAG01", "Pago con tarjeta", "https://tarjetas.ej/api", "GTQ", 3.5));

        agregarIntegracion(new PasarelaPago("PAG02", "Transferencia bancaria", "https://transferencias.ej/api", "GTQ", 1.0));

        agregarIntegracion(new PasarelaPago("PAG03", "Billetera digital", "https://billetera.ej/api", "GTQ", 2.0));

        agregarIntegracion(new PasarelaPago("PAG04", "Pagos internacionales", "https://pagos.ej/api", "USD", 4.0));

        // Integraciones de logística.
        agregarIntegracion(new ProveedorLogistico("LOG01", "Envíos urbanos", "https://urbanos.ej/api", 20.0, 5.0));

        agregarIntegracion(new ProveedorLogistico("LOG02", "Envíos departamentales", "https://departamentales.ej/api", 35.0, 8.0));

        agregarIntegracion(new ProveedorLogistico("LOG03", "Entregas de última milla", "https://entregas.ej/api", 15.0, 4.0));

        // Integraciones de facturación.
        agregarIntegracion(new CertificadorFEL("FEL01", "Certificador Uno", "https://feluno.ej/api", "1234567-8"));

        agregarIntegracion(new CertificadorFEL("FEL02", "Certificador Dos", "https://feldos.ej/api", "2345678-9"));

        agregarIntegracion(new CertificadorFEL("FEL03", "Certificador Tres", "https://feltres.ej/api", "3456789-0"));

        double latencia = 50.0;

        for (IntegracionAPI integracion : integraciones) {
            integracion.setConectada(true);
            integracion.setLatenciaPromedio(latencia);
            integracion.setParametrosConfiguracion("Modo: simulación; tiempo de espera: 5000 ms");

            latencia = latencia + 25.0;
        }

        IntegracionAPI desconectada = buscarIntegracion("LOG03");

        if (desconectada != null) {
            desconectada.setConectada(false);
        }
    }

    public boolean agregarIntegracion(IntegracionAPI integracion) {
        if (integracion == null) {
            return false;
        }

        String identificador = integracion.getIdentificador();

        if (identificador == null || identificador.trim().isEmpty()) {
            return false;
        }

        if (buscarIntegracion(identificador) != null) {
            return false;
        }

        integraciones.add(integracion);
        return true;
    }

    public List<IntegracionAPI> getIntegraciones() {
        return integraciones;
    }

    public IntegracionAPI buscarIntegracion(String identificador) {
        if (identificador == null) {
            return null;
        }

        for (IntegracionAPI integracion : integraciones) {
            if (integracion.getIdentificador().equalsIgnoreCase(identificador.trim())) {

                return integracion;
            }
        }

        return null;
    }

    public void ordenarPorLatencia() {
        Collections.sort(integraciones);
    }

    // Primera versión: realiza un cobro.
    public String ejecutarOperacion(Transaccional servicio, double monto) {
        if (servicio == null) {
            return "No se encontró el servicio solicitado.";
        }

        return servicio.procesarCobro(monto);
    }

    // Segunda versión: genera un documento.
    public String ejecutarOperacion(Documentable servicio, String datos) {
        if (servicio == null) {
            return "No se encontró el servicio solicitado.";
        }

        return servicio.generarDocumento(datos);
    }

    // Tercera versión: genera un registro de auditoría.
    public String ejecutarOperacion(Auditable servicio, String operacion) {
        if (servicio == null) {
            return "No se encontró el servicio solicitado.";
        }

        return servicio.auditar(operacion);
    }

    public String procesarReintegro(Transaccional servicio, double monto) {
        if (servicio == null) {
            return "No se encontró el servicio solicitado.";
        }

        return servicio.procesarReintegro(monto);
    }
}