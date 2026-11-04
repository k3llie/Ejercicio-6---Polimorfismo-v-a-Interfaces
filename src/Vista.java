import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// Muestra las ventanas y recibe los datos del usuario.
public class Vista {

    public Vista() {
    }

    public int mostrarMenu() {
        String[] opciones = {
            "1. Listar integraciones activas",
            "2. Buscar integración",
            "3. Ordenar integraciones por latencia",
            "4. Consultar estado de una operación",
            "5. Realizar cobro",
            "6. Realizar reintegro",
            "7. Generar documento",
            "8. Generar registro de auditoría",
            "0. Salir"
        };

        String seleccion = (String) JOptionPane.showInputDialog(null, "Selecciona una opción:", 
                "Gestor de integraciones", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == null) {
            return 0;
        }

        return Integer.parseInt(seleccion.substring(0, 1));
    }

    public String leerTexto(String mensaje) {
        String texto = JOptionPane.showInputDialog(null, mensaje, "Ingreso de datos", JOptionPane.QUESTION_MESSAGE);

        if (texto == null) {
            return "";
        } 
        else {
            return texto.trim();
        }
    }

    public int leerEntero(String mensaje) {
        int numero = 0;
        boolean valido = false;
        boolean cancelado = false;

        do {
            String entrada = JOptionPane.showInputDialog(null, mensaje, "Ingreso de datos", JOptionPane.QUESTION_MESSAGE);

            if (entrada == null) {
                cancelado = true;
            } 
            else {
                try {
                    numero = Integer.parseInt(entrada.trim());
                    valido = true;
                } 
                catch (NumberFormatException e) {
                    mostrarMensaje("Debes ingresar un número entero válido.");
                }
            }
        } 
        while (!valido && !cancelado);

        return numero;
    }

    public double leerDouble(String mensaje) {
        double numero = 0;
        boolean valido = false;
        boolean cancelado = false;

        do {
            String entrada = JOptionPane.showInputDialog(null, mensaje, "Ingreso de datos", JOptionPane.QUESTION_MESSAGE);

            if (entrada == null) {
                cancelado = true;
            } 
            else {
                try {
                    numero = Double.parseDouble(entrada.trim());

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
        } 
        while (!valido && !cancelado);

        return numero;
    }

    public void mostrarIntegraciones(List<IntegracionAPI> integraciones, boolean soloActivas) {
        String texto = "";

        for (IntegracionAPI integracion : integraciones) {
            if (!soloActivas || integracion.isActiva()) {
                texto = texto + integracion.toString() + "\n\n";
            }
        }

        if (texto.isEmpty()) {
            mostrarMensaje("No hay integraciones para mostrar.");
        } 
        else {
            JTextArea area = new JTextArea(texto, 15, 65);
            area.setEditable(false);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setCaretPosition(0);

            JScrollPane desplazamiento = new JScrollPane(area);

            JOptionPane.showMessageDialog(null, desplazamiento, "Catálogo de integraciones", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarConfiguracion(IntegracionAPI integracion) {
        String texto = integracion.toString() + "\n\nURL base: " + integracion.getUrlBase() + "\nParámetros: " + integracion.getParametrosConfiguracion();

        JOptionPane.showMessageDialog(null, texto, "Configuración de la integración", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Gestor de integraciones", JOptionPane.INFORMATION_MESSAGE);
    }
}