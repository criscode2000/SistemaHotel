/*
 * ============================================================================
 * CLASE: Huesped  (ABSTRACTA)
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Superclase de todos los huéspedes. Reúne los datos comunes (nombre,
 *   documento, edad) y obliga a cada tipo de huésped a describirse con
 *   obtenerInfo().
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Ser el tipo general que usan Habitacion (ArrayList<Huesped>) y Reserva,
 *   de modo que puedan trabajar con huéspedes regulares y VIP sin
 *   distinguirlos.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - ABSTRACCIÓN (clase y método abstractos).
 *   - ENCAPSULAMIENTO (atributos private + getters public).
 *   - CONSTRUCTORES + VALIDACIONES (Validador y rango de edad).
 *   - HERENCIA (HuespedRegular y HuespedVIP la extienden).
 *   - POLIMORFISMO DINÁMICO (obtenerInfo()).
 *
 * CLASES RELACIONADAS:
 *   Validador, HuespedRegular, HuespedVIP, Habitacion, Reserva.
 * ============================================================================
 */
package com.hotel.modelo;

public abstract class Huesped {

    private String nombre;
    private String documento;
    private int edad;

    /**
     * CONSTRUCTOR de Huesped (lo invocan las subclases con super(...)).
     *
     * @param nombre    nombre del huésped (sin vacío ni dígitos)
     * @param documento documento (5 a 12 dígitos)
     * @param edad      edad entre 0 y 120
     * @throws IllegalArgumentException si algún dato es inválido
     *
     * CONCEPTO: CONSTRUCTORES + VALIDACIONES. Si algo falla, la excepción
     * se lanza y el objeto NO llega a crearse.
     */
    public Huesped(String nombre, String documento, int edad) {
        Validador.validarNombre(nombre);
        Validador.validarDocumento(documento);
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("Edad inválida: " + edad);
        }
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
    }

    /** @return el nombre del huésped. */
    public String getNombre() {
        return nombre;
    }

    /** @return el documento del huésped. */
    public String getDocumento() {
        return documento;
    }

    /** @return la edad del huésped. */
    public int getEdad() {
        return edad;
    }

    /**
     * MÉTODO ABSTRACTO: cada subclase define cómo se describe.
     *
     * @return texto con la información del huésped
     *
     * CONCEPTO: ABSTRACCIÓN + POLIMORFISMO DINÁMICO.
     */
    public abstract String obtenerInfo();
}
