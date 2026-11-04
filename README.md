# Ejercicio-6 - Polimorfismo vía Interfaces

**Nombre completo:** Kellie Sophia López Torres 
**Carné:** 261551

## Descripción
El programa permite administrar integraciones con servicios de pago, logística y facturación electrónica. Lista las integraciones activas, busca por identificador, muestra su configuración y ordena el catálogo por latencia. También simula consultas de estado, cobros, reintegros, generación de documentos y registros de auditoría.

El programa está organizado con MVC: la Vista muestra el menú gráfico y pide los datos, el Controlador coordina las acciones y el modelo gestiona las integraciones.

La clase abstracta IntegracionAPI reúne los datos y comportamientos comunes. Sus clases hijas PasarelaPago, ProveedorLogistico y CertificadorFEL realizan las operaciones propias de cada servicio mediante polimorfismo. Todas las integraciones se almacenan en una única lista ArrayList.

Las interfaces Transaccional, Documentable y Auditable definen las operaciones disponibles. Los métodos predeterminados permiten ignorar las operaciones incompatibles sin interrumpir el programa. Comparable permite ordenar las integraciones por latencia.

Main inicia el programa y GestorIntegraciones carga 10 integraciones. Todas comienzan activas y una está desconectada. Las conexiones y los resultados son simulados. La interfaz gráfica utiliza Swing.

## Cómo ejecutar
```bash
javac -d bin src/*.java
java -cp bin Main
```