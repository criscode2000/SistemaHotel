/*
 * ============================================================================
 * CLASE: HuespedNoEncontradoException
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Excepción PERSONALIZADA que indica que no existe un huésped con el
 *   documento buscado.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Centralizar el mensaje de error de "huésped no encontrado" para que sea
 *   idéntico sin importar quién la lance.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA (extends Exception).
 *   - MANEJO DE EXCEPCIONES (excepción verificada / checked).
 *   - CONSTRUCTORES (el mensaje se arma dentro del propio constructor).
 *
 * CLASES RELACIONADAS:
 *   - Habitacion.buscarHuesped(...) y Habitacion.liberarHuesped(...) la lanzan.
 *   - Hotel.buscarHuesped(...) la captura para seguir con otra habitación y
 *     la vuelve a lanzar si ninguna tiene al huésped.
 *   - App (opciones 3, 4, 7 y 8) la captura.
 * ============================================================================
 */
package com.hotel.excepciones;

public class HuespedNoEncontradoException extends Exception {

    /**
     * CONSTRUCTOR. Recibe el DOCUMENTO (no el mensaje) y construye el mensaje.
     *
     * @param documento documento del huésped que no se encontró
     *
     * CONCEPTO: CONSTRUCTORES + HERENCIA. El mensaje exigido por el enunciado
     * se concatena aquí y se entrega a super(...).
     */
    public HuespedNoEncontradoException(String documento) {
        super("Huésped con documento " + documento + " no encontrado.");
    }
}
