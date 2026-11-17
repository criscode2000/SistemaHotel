/*
 * ============================================================================
 * CLASE: ReservaInvalidaException
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Excepción PERSONALIZADA que indica que los datos de una reserva no son
 *   válidos (fecha vacía, servicio vacío o huésped nulo).
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Hacer cumplir las reglas de negocio de Recepcionista.hacerReserva(...).
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA (extends Exception).
 *   - MANEJO DE EXCEPCIONES (excepción verificada / checked).
 *   - CONSTRUCTORES.
 *
 * CLASES RELACIONADAS:
 *   - Recepcionista.hacerReserva(...) (ambas versiones) la LANZA.
 *   - App (opción 4) la CAPTURA.
 * ============================================================================
 */
package com.hotel.excepciones;

public class ReservaInvalidaException extends Exception {

    /**
     * CONSTRUCTOR. Recibe el mensaje que explica por qué la reserva es inválida.
     *
     * @param mensaje descripción del problema
     */
    public ReservaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
