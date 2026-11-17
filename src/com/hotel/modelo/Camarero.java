/*
 * ============================================================================
 * CLASE: Camarero  (extiende Empleado)
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Representa al empleado de housekeeping/servicio que atiende una zona del
 *   hotel.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Aportar su propia versión de generarReporte() (incluye la zona).
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA: "extends Empleado" (un Camarero ES UN Empleado).
 *   - CONSTRUCTORES: llama a super(...) y luego asigna su atributo propio.
 *   - SOBREESCRITURA + POLIMORFISMO DINÁMICO: implementa generarReporte().
 *   - ENCAPSULAMIENTO: atributo zona private; usa getters heredados.
 *
 * CLASES RELACIONADAS:
 *   Empleado (superclase), Hotel (lo guarda en su lista de empleados).
 * ============================================================================
 */
package com.hotel.modelo;

public class Camarero extends Empleado {

    // Atributo PROPIO del camarero (no existe en Empleado).
    private String zona;

    /**
     * CONSTRUCTOR de Camarero.
     *
     * @param nombre nombre (se valida en Empleado)
     * @param id     identificador
     * @param cargo  cargo
     * @param zona   zona del hotel que atiende (ej: "Piso 1")
     *
     * CONCEPTO: CONSTRUCTORES + HERENCIA. "super(...)" DEBE ser la primera
     * instrucción: construye primero la parte heredada (Empleado).
     */
    public Camarero(String nombre, String id, String cargo, String zona) {
        super(nombre, id, cargo);
        this.zona = zona;
    }

    /**
     * Genera el reporte del camarero.
     *
     * @return texto con nombre, id, cargo y zona
     *
     * CONCEPTO: SOBREESCRITURA (@Override) y POLIMORFISMO DINÁMICO.
     * Como nombre/id/cargo son private en Empleado, se leen con los getters
     * heredados (getNombre(), getId(), getCargo()).
     */
    @Override
    public String generarReporte() {
        return "=== CAMARERO ===\nNombre: " + getNombre()
                + "\nID: " + getId()
                + "\nCargo: " + getCargo()
                + "\nZona: " + zona;
    }
}
