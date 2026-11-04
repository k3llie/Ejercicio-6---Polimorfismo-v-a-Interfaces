import java.util.List;
import java.util.Scanner;

public class Vista {

    private Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n---- GESTOR DE INTEGRACIONES ----");
        System.out.println("1. Listar integraciones activas");
        System.out.println("2. Buscar integración");
        System.out.println("3. Ordenar integraciones por latencia");
        System.out.println("4. Consultar estado de una operación");
        System.out.println("5. Realizar cobro");
        System.out.println("6. Realizar reintegro");
        System.out.println("7. Generar documento");
        System.out.println("8. Generar registro de auditoría");
        System.out.println("0. Salir\n");

        return leerEntero("Selecciona una opción: ");
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        int numero = 0;
        boolean valido = false;

        do {
            String entrada = leerTexto(mensaje);

            try {
                numero = Integer.parseInt(entrada);
                valido = true;
            } 
            catch (NumberFormatException e) {
                mostrarMensaje("Debes ingresar un número entero válido.");
            }
        } 
        while (!valido);

        return numero;
    }

    public double leerDouble(String mensaje) {
        double numero = 0;
        boolean valido = false;

        do {
            String entrada = leerTexto(mensaje);

            try {
                numero = Double.parseDouble(entrada);

                if (Double.isFinite(numero)) {
                    valido = true;
                } 
                else {
                    mostrarMensaje("Debes ingresar un número finito.");
                }
            } 
            catch (NumberFormatException e) {
                mostrarMensaje("Debes ingresar un número válido.");
            }
        } 
        while (!valido);

        return numero;
    }

    public void mostrarIntegraciones(List<IntegracionAPI> integraciones, boolean soloActivas) {

        boolean encontrada = false;

        for (IntegracionAPI integracion : integraciones) {
            if (!soloActivas || integracion.isActiva()) {
                System.out.println(integracion);
                encontrada = true;
            }
        }

        if (!encontrada) {
            mostrarMensaje("No hay integraciones para mostrar.");
        }
    }

    public void mostrarConfiguracion(IntegracionAPI integracion) {
        System.out.println("\n---- CONFIGURACIÓN ----");
        System.out.println(integracion);
        System.out.println("URL base: " + integracion.getUrlBase());
        System.out.println("Parámetros: " + integracion.getParametrosConfiguracion());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}