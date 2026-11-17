/*
 * ============================================================================
 * CLASE: Validador
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Es una clase de UTILIDAD: agrupa métodos estáticos que validan datos
 *   (nombres y documentos) y que son reutilizados por varias clases.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Evitar repetir las mismas validaciones en Empleado y Huesped. Si la regla
 *   cambia, se cambia en un solo lugar.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - VALIDACIONES: lanza IllegalArgumentException si el dato es inválido.
 *   - MANEJO DE EXCEPCIONES: IllegalArgumentException es "no verificada"
 *     (unchecked), por eso no obliga a try/catch ni a "throws".
 *   - MODIFICADORES DE ACCESO: constructor private (no se puede instanciar)
 *     y métodos public static.
 *   - ENCAPSULAMIENTO: la regla de validación queda oculta en esta clase.
 *
 * CLASES RELACIONADAS:
 *   - Empleado (constructor) usa validarNombre.
 *   - Huesped (constructor) usa validarNombre y validarDocumento.
 * ============================================================================
 */
package com.hotel.modelo;

public class Validador {

    /**
     * CONSTRUCTORES + MODIFICADORES DE ACCESO.
     * Al ser "private" nadie fuera de esta clase puede hacer "new Validador()".
     * Tiene sentido: solo contiene métodos estáticos, no hay nada que instanciar.
     */
    private Validador() {
    }

    /**
     * Valida que un nombre no sea nulo/vacío y que no contenga dígitos.
     *
     * @param nombre texto a validar
     * @throws IllegalArgumentException si el nombre está vacío o tiene números
     *
     * CONCEPTO: VALIDACIONES + palabra reservada "static": el método pertenece
     * a la CLASE, así que se invoca como Validador.validarNombre(...) sin crear
     * un objeto. Devuelve "void" (nada): si el dato es válido simplemente
     * termina sin hacer ruido; si no, lanza excepción.
     */
    public static void validarNombre(String nombre) {
        // "null" significa "sin objeto". trim() quita espacios de los extremos,
        // así "   " también se considera vacío. El orden importa: se pregunta
        // primero por null para no llamar trim() sobre null (daría error).
        if (nombre == null || nombre.trim().isEmpty()) {
            // "throw": palabra reservada que LANZA una excepción.
            // "new": crea el objeto excepción.
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        // Se recorre cada carácter del nombre buscando dígitos.
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i); // carácter en la posición i
            if (c >= '0' && c <= '9') { // ¿es un dígito del 0 al 9?
                throw new IllegalArgumentException(
                        "El nombre no puede contener números: " + nombre);
            }
        }
    }

    /**
     * Valida que un documento tenga entre 5 y 12 caracteres y sean solo dígitos.
     *
     * @param documento texto a validar
     * @throws IllegalArgumentException si la longitud es incorrecta o hay
     *         caracteres que no son dígitos
     *
     * CONCEPTO: VALIDACIONES + método estático.
     */
    public static void validarDocumento(String documento) {
        // Primero la longitud (y el null, antes de pedir length()).
        if (documento == null || documento.length() < 5 || documento.length() > 12) {
            throw new IllegalArgumentException(
                    "El documento debe tener entre 5 y 12 dígitos.");
        }

        // Luego, cada carácter debe ser un dígito.
        for (int i = 0; i < documento.length(); i++) {
            char c = documento.charAt(i);
            if (c < '0' || c > '9') { // si NO es un dígito
                throw new IllegalArgumentException(
                        "El documento solo puede contener números.");
            }
        }
    }
}
