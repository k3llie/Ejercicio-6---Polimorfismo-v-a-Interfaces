public class Controlador {

    private Vista vista;
    private GestorIntegraciones gestor;

    public Controlador() {
        vista = new Vista();
        gestor = new GestorIntegraciones();
    }

    public void iniciar() {
        int opcion;

        do {
            opcion = vista.mostrarMenu();

            switch (opcion) {
                case 1:
                    gestionarListado();
                    break;

                case 2:
                    gestionarBusqueda();
                    break;

                case 3:
                    gestionarOrdenamiento();
                    break;

                case 4:
                    gestionarConsulta();
                    break;

                case 5:
                    gestionarCobro();
                    break;

                case 6:
                    gestionarReintegro();
                    break;

                case 7:
                    gestionarDocumento();
                    break;

                case 8:
                    gestionarAuditoria();
                    break;

                case 0:
                    vista.mostrarMensaje("Programa finalizado.");
                    break;

                default:
                    vista.mostrarMensaje("Selecciona una opción válida.");
                    break;
            }
        } 
        while (opcion != 0);
    }

    private void gestionarListado() {
        vista.mostrarIntegraciones(gestor.getIntegraciones(), true);
    }

    private void gestionarBusqueda() {
        String identificador = vista.leerTexto("Identificador de la integración: ");

        IntegracionAPI integracion = gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            vista.mostrarMensaje("No se encontró la integración.");
        } 
        else {
            vista.mostrarConfiguracion(integracion);
        }
    }

    private void gestionarOrdenamiento() {
        gestor.ordenarPorLatencia();

        vista.mostrarMensaje("Integraciones ordenadas de menor a mayor latencia:");

        vista.mostrarIntegraciones(gestor.getIntegraciones(), false);
    }

    private void gestionarConsulta() {
        String identificador = vista.leerTexto("Identificador de la integración: ");

        IntegracionAPI integracion = gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            vista.mostrarMensaje("No se encontró la integración.");
        } 
        else {
            String referencia = vista.leerTexto("Referencia de la operación: ");

            String resultado = integracion.consultarEstado(referencia);

            vista.mostrarMensaje(resultado);
        }
    }

    private void gestionarCobro() {
        String identificador = vista.leerTexto("Identificador de la integración: ");

        IntegracionAPI integracion = gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            vista.mostrarMensaje("No se encontró la integración.");
        } 
        else {
            double monto = vista.leerDouble("Monto del cobro: ");

            Transaccional servicio = integracion;

            String resultado = gestor.ejecutarOperacion(servicio, monto);

            vista.mostrarMensaje(resultado);
        }
    }

    private void gestionarReintegro() {
        String identificador = vista.leerTexto("Identificador de la integración: ");

        IntegracionAPI integracion = gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            vista.mostrarMensaje("No se encontró la integración.");
        } 
        else {
            double monto = vista.leerDouble("Monto del reintegro: ");

            Transaccional servicio = integracion;

            String resultado = gestor.procesarReintegro(servicio, monto);

            vista.mostrarMensaje(resultado);
        }
    }

    private void gestionarDocumento() {
        String identificador = vista.leerTexto("Identificador de la integración: ");

        IntegracionAPI integracion = gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            vista.mostrarMensaje("No se encontró la integración.");
        } 
        else {
            String datos = vista.leerTexto("Datos para generar el documento: ");

            Documentable servicio = integracion;

            String resultado = gestor.ejecutarOperacion(servicio, datos);

            vista.mostrarMensaje(resultado);
        }
    }

    private void gestionarAuditoria() {
        String identificador = vista.leerTexto("Identificador de la integración: ");

        IntegracionAPI integracion = gestor.buscarIntegracion(identificador);

        if (integracion == null) {
            vista.mostrarMensaje("No se encontró la integración.");
        } 
        else {
            String operacion = vista.leerTexto("Descripción de la operación que deseas registrar: ");

            Auditable servicio = integracion;

            String resultado = gestor.ejecutarOperacion(servicio, operacion);

            vista.mostrarMensaje(resultado);
        }
    }
}