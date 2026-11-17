/*
 * ============================================================================
 * CLASE: HuespedVIP  (extiende Huesped)  ·  FINAL
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Representa a un huésped VIP: además de las noches tiene un porcentaje de
 *   descuento (0 a 50).
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Aportar su propia versión de obtenerInfo() y exponer el descuento.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA (extends Huesped).
 *   - "final": la clase NO puede heredarse (nadie puede hacer
 *     "class X extends HuespedVIP").
 *   - CONSTRUCTORES + VALIDACIONES (noches y descuento).
 *   - SOBREESCRITURA + POLIMORFISMO DINÁMICO.
 *   - ENCAPSULAMIENTO.
 *
 * CLASES RELACIONADAS:
 *   Huesped (superclase), Habitacion, App.
 * ============================================================================
 */
package com.hotel.modelo;

// "final": palabra reservada. Aplicada a una clase impide que otras la extiendan.
public final class HuespedVIP extends Huesped {

    private int noches;
    private int descuento; // porcentaje: 0 a 50

    /**
     * CONSTRUCTOR de HuespedVIP.
     *
     * @param nombre    nombre del huésped
     * @param documento documento del huésped
     * @param edad      edad del huésped
     * @param noches    noches de estadía (mayor a 0)
     * @param descuento porcentaje de descuento (entre 0 y 50)
     * @throws IllegalArgumentException si noches o descuento son inválidos
     */
    public HuespedVIP(String nombre, String documento, int edad, int noches, int descuento) {
        super(nombre, documento, edad);
        if (noches <= 0) {
            throw new IllegalArgumentException("Las noches deben ser mayores a 0.");
        }
        if (descuento < 0 || descuento > 50) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 50.");
        }
        this.noches = noches;
        this.descuento = descuento;
    }

    /**
     * Describe al huésped VIP.
     *
     * @return texto con nombre, documento, edad, noches y descuento
     *
     * CONCEPTO: SOBREESCRITURA (@Override) + POLIMORFISMO DINÁMICO.
     */
    @Override
    public String obtenerInfo() {
        return "Huésped VIP: " + getNombre()
                + " | Documento: " + getDocumento()
                + " | Edad: " + getEdad()
                + " | Noches: " + noches
                + " | Descuento: " + descuento + "%";
    }

    /** @return el porcentaje de descuento del huésped VIP. */
    public int getDescuento() {
        return descuento;
    }
}
