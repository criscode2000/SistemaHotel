/*
 * ============================================================================
 * CLASE: HabitacionLlenaException
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Es una excepción PERSONALIZADA que representa un error del negocio:
 *   intentar alojar un huésped en una habitación que ya no tiene cupo.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Avisar (con throw) que la capacidad de una Habitacion se agotó, para que
 *   la clase App pueda capturarla (catch) y mostrar un mensaje al usuario.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA: extiende (extends) la clase Exception de Java.
 *   - MANEJO DE EXCEPCIONES: al extender Exception es una excepción
 *     "verificada" (checked): el compilador obliga a capturarla (try/catch)
 *     o a declararla con "throws".
 *   - CONSTRUCTORES: usa super(...) para pasar el mensaje a la superclase.
 *
 * CLASES RELACIONADAS:
 *   - Habitacion.alojarHuesped(...) la LANZA.
 *   - App (opción 3) la CAPTURA.
 * ============================================================================
 */
package com.hotel.excepciones; // PAQUETE: carpeta lógica donde vive esta clase

// "public": visible desde cualquier paquete (MODIFICADORES DE ACCESO).
// "class": palabra reservada que declara una clase.
// "extends": palabra reservada que indica HERENCIA (hereda de Exception).
public class HabitacionLlenaException extends Exception {

    /**
     * CONSTRUCTOR. Recibe el mensaje que describe el error.
     *
     * @param mensaje texto explicativo (ej: "Habitación 101 sin cupo disponible.")
     *
     * CONCEPTO: CONSTRUCTORES + HERENCIA. "super(mensaje)" es una palabra
     * reservada que llama al constructor de Exception para que guarde el
     * mensaje; luego lo podremos leer con e.getMessage().
     */
    public HabitacionLlenaException(String mensaje) {
        super(mensaje);
    }
}
