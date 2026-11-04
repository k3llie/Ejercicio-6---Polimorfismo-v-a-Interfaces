public abstract class IntegracionAPI implements Transaccional,
        Documentable, Auditable, Comparable<IntegracionAPI> {

    private String identificador;
    private String nombre;
    private boolean activa;
    private boolean conectada;
    private double latenciaPromedio;
    private String urlBase;
    private String parametrosConfiguracion;

    // Constructor con los datos básicos.
    protected IntegracionAPI(String identificador, String nombre, String urlBase) {

        this(identificador, nombre, true, false, 0.0, urlBase, "");
    }

    // Constructor con todos los datos.
    protected IntegracionAPI(String identificador, String nombre, boolean activa, boolean conectada, double latenciaPromedio,
            String urlBase, String parametrosConfiguracion) {

        this.identificador = identificador;
        this.nombre = nombre;
        this.activa = activa;
        this.conectada = conectada;
        this.urlBase = urlBase;

        setLatenciaPromedio(latenciaPromedio);
        setParametrosConfiguracion(parametrosConfiguracion);
    }

    // Cada clase hija implementará su propia consulta.
    public abstract String consultarEstado(String referencia);

    @Override
    public String auditar(String operacion) {
        return "Integración: " + identificador + " - " + nombre + " | Operación: " + operacion;
    }

    @Override
    public int compareTo(IntegracionAPI otra) {
        return Double.compare(this.latenciaPromedio, otra.latenciaPromedio);
    }

    @Override
    public String toString() {
        return "ID: " + identificador + " | Nombre: " + nombre + " | Activa: " + (activa ? "Sí" : "No") 
            + " | Conexión: " + (conectada ? "Conectada" : "Desconectada") + " | Latencia promedio: " + latenciaPromedio + " ms";
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isActiva() {
        return activa;
    }

    public boolean isConectada() {
        return conectada;
    }

    public double getLatenciaPromedio() {
        return latenciaPromedio;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public String getParametrosConfiguracion() {
        return parametrosConfiguracion;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void setConectada(boolean conectada) {
        this.conectada = conectada;
    }

    public void setLatenciaPromedio(double latenciaPromedio) {
        if (Double.isFinite(latenciaPromedio) && latenciaPromedio >= 0) {

            this.latenciaPromedio = latenciaPromedio;
        }
    }

    public void setParametrosConfiguracion(String parametrosConfiguracion) {

        if (parametrosConfiguracion == null) {
            this.parametrosConfiguracion = "";
        } 
        else {
            this.parametrosConfiguracion = parametrosConfiguracion;
        }
    }
}