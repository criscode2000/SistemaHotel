/*
 * ============================================================================
 * CLASE: HuespedRegular  (extiende Huesped)
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Representa a un huésped estándar, que se hospeda cierto número de noches.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Aportar su propia versión de obtenerInfo().
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA (extends Huesped).
 *   - CONSTRUCTORES (super(...) + validación propia de noches).
 *   - VALIDACIONES.
 *   - SOBREESCRITURA + POLIMORFISMO DINÁMICO (obtenerInfo()).
 *   - ENCAPSULAMIENTO (noches private).
 *
 * CLASES RELACIONADAS:
 *   Huesped (superclase), Habitacion, App.
 * ============================================================================
 */
package com.hotel.modelo;

public class HuespedRegular extends Huesped {

    private int noches;

    /**
     * CONSTRUCTOR de HuespedRegular.
     *
     * @param nombre    nombre del huésped
     * @param documento documento del huésped
     * @param edad      edad del huésped
     * @param noches    noches de estadía (debe ser mayor a 0)
     * @throws IllegalArgumentException si noches &lt;= 0 (o dato heredado inválido)
     */
    public HuespedRegular(String nombre, String documento, int edad, int noches) {
        super(nombre, documento, edad); // valida y asigna los datos heredados
        if (noches <= 0) {
            throw new IllegalArgumentException("Las noches deben ser mayores a 0.");
        }
        this.noches = noches;
    }

    /**
     * Describe al huésped regular.
     *
     * @return texto con nombre, documento, edad y noches
     *
     * CONCEPTO: SOBREESCRITURA (@Override) + POLIMORFISMO DINÁMICO.
     */
    @Override
    public String obtenerInfo() {
        return "Huésped regular: " + getNombre()
                + " | Documento: " + getDocumento()
                + " | Edad: " + getEdad()
                + " | Noches: " + noches;
    }
}
