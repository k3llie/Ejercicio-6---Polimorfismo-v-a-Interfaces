public interface Documentable {

    default String generarDocumento(String datos) {
        return "Solicitud ignorada: esta integración no permite generar documentos.";
    }
}