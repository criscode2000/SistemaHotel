/*
 * ============================================================================
 * CLASE: Empleado  (ABSTRACTA)
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Es la superclase de todo el personal del hotel. Define lo que TODO
 *   empleado tiene en común (nombre, id, cargo) y obliga a cada tipo de
 *   empleado a saber generar su propio reporte.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Ser el tipo general con el que Hotel guarda a todo el personal en una
 *   sola lista (ArrayList<Empleado>) sin importar si es Recepcionista o
 *   Camarero.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - ABSTRACCIÓN: "abstract class" no se puede instanciar (no existe un
 *     "empleado genérico", solo recepcionistas, camareros, etc.).
 *   - ENCAPSULAMIENTO: atributos private + getters public.
 *   - CONSTRUCTORES + VALIDACIONES (usa Validador).
 *   - HERENCIA: Recepcionista y Camarero extienden esta clase.
 *   - POLIMORFISMO DINÁMICO: generarReporte() es abstracto; cada subclase lo
 *     sobreescribe y Java decide en ejecución cuál versión llamar.
 *
 * CLASES RELACIONADAS:
 *   Validador, Recepcionista, Camarero, Hotel.
 * ============================================================================
 */
package com.hotel.modelo;

// "abstract": palabra reservada. Una clase abstracta NO se puede instanciar
// con "new" y puede tener métodos abstractos (sin cuerpo).
public abstract class Empleado {

    // ENCAPSULAMIENTO: "private" = solo esta clase ve estos atributos.
    // Las subclases usarán los getters (encapsulamiento estricto).
    private String nombre;
    private String id;
    private String cargo;

    /**
     * CONSTRUCTOR de Empleado. Lo invocan las subclases con super(...).
     *
     * @param nombre nombre del empleado (no vacío, sin dígitos)
     * @param id     identificador único (ej: "R001")
     * @param cargo  cargo que desempeña
     * @throws IllegalArgumentException si el nombre no es válido
     *
     * CONCEPTO: CONSTRUCTORES + VALIDACIONES. Se valida ANTES de asignar, para
     * que nunca exista un Empleado con un nombre inválido.
     */
    public Empleado(String nombre, String id, String cargo) {
        Validador.validarNombre(nombre);
        // "this" es una referencia al objeto que se está construyendo; sirve
        // para distinguir el atributo (this.nombre) del parámetro (nombre).
        this.nombre = nombre;
        this.id = id;
        this.cargo = cargo;
    }

    /** @return el nombre del empleado. (ENCAPSULAMIENTO: getter público) */
    public String getNombre() {
        return nombre;
    }

    /** @return el id del empleado. */
    public String getId() {
        return id;
    }

    /** @return el cargo del empleado. */
    public String getCargo() {
        return cargo;
    }

    /**
     * MÉTODO ABSTRACTO: solo declara la firma, SIN cuerpo.
     * Cada subclase concreta está OBLIGADA a implementarlo.
     *
     * @return texto con el reporte del empleado
     *
     * CONCEPTO: ABSTRACCIÓN + POLIMORFISMO DINÁMICO.
     */
    public abstract String generarReporte();
}
