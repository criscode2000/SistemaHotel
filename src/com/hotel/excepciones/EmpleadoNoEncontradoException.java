/*
 * ============================================================================
 * CLASE: EmpleadoNoEncontradoException
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Excepción PERSONALIZADA que indica que no existe un empleado
 *   (recepcionista) registrado con el id buscado.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Señalar el error cuando Hotel.buscarRecepcionista(id) no encuentra nada.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA (extends Exception).
 *   - MANEJO DE EXCEPCIONES (excepción verificada / checked).
 *   - CONSTRUCTORES.
 *
 * CLASES RELACIONADAS:
 *   - Hotel.buscarRecepcionista(...) la LANZA.
 *   - App (opciones 4 y 6) la CAPTURA.
 * ============================================================================
 */
package com.hotel.excepciones;

public class EmpleadoNoEncontradoException extends Exception {

    /**
     * CONSTRUCTOR. Recibe el id del empleado y arma el mensaje.
     *
     * @param id identificador buscado (ej: "R009")
     */
    public EmpleadoNoEncontradoException(String id) {
        super("No existe empleado registrado con id " + id + ".");
    }
}
