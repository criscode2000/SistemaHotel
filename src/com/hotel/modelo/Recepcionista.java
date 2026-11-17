/*
 * ============================================================================
 * CLASE: Recepcionista  (extiende Empleado)
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Representa al empleado de recepción, que es quien hace las reservas.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Crear y guardar sus Reservas (dos versiones de hacerReserva) y generar su
 *   reporte.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - HERENCIA (extends Empleado).
 *   - SOBRECARGA / POLIMORFISMO ESTÁTICO: dos métodos hacerReserva con el
 *     mismo nombre y distintos parámetros; el compilador elige cuál usar
 *     según los argumentos.
 *   - SOBREESCRITURA / POLIMORFISMO DINÁMICO: generarReporte().
 *   - ARRAYLIST: lista de reservas.
 *   - ASOCIACIÓN: Recepcionista -> Reserva -> Huesped.
 *   - MANEJO DE EXCEPCIONES: lanza ReservaInvalidaException.
 *   - ENCAPSULAMIENTO y CONSTRUCTORES.
 *
 * CLASES RELACIONADAS:
 *   Empleado, Reserva, Huesped, ReservaInvalidaException, Hotel, App.
 * ============================================================================
 */
package com.hotel.modelo;

import com.hotel.excepciones.ReservaInvalidaException;
import java.util.ArrayList; // ARRAYLIST: clase de java.util, hay que importarla

public class Recepcionista extends Empleado {

    // ARRAYLIST<Reserva>: lista dinámica que solo admite objetos Reserva.
    private ArrayList<Reserva> reservas;

    /**
     * CONSTRUCTOR de Recepcionista.
     *
     * @param nombre nombre (se valida en Empleado)
     * @param id     identificador (ej: "R001")
     * @param cargo  cargo
     *
     * CONCEPTO: CONSTRUCTORES. Tras super(...), la lista se inicializa vacía
     * con "new ArrayList<>()" (si no, valdría null y daría error al usarla).
     */
    public Recepcionista(String nombre, String id, String cargo) {
        super(nombre, id, cargo);
        this.reservas = new ArrayList<>();
    }

    /**
     * PRIMERA VERSIÓN (SOBRECARGA): reserva con servicio estándar.
     *
     * @param fecha   fecha de la reserva
     * @param huesped huésped real que reserva
     * @throws ReservaInvalidaException si la fecha está vacía o el huésped es null
     *
     * CONCEPTO: SOBRECARGA / POLIMORFISMO ESTÁTICO (2 parámetros) y MANEJO DE
     * EXCEPCIONES. "throws" declara que este método puede lanzar la excepción
     * verificada, así quien lo llame debe capturarla.
     */
    public void hacerReserva(String fecha, Huesped huesped) throws ReservaInvalidaException {
        // VALIDACIONES de entrada
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new ReservaInvalidaException("La fecha no puede estar vacía.");
        }
        if (huesped == null) {
            throw new ReservaInvalidaException("Debe indicarse un huésped válido.");
        }

        // ASOCIACIÓN: "this" es este recepcionista; se asocia a la reserva.
        Reserva reserva = new Reserva(fecha, "Alojamiento estándar", huesped, this);
        reservas.add(reserva); // ARRAYLIST: add agrega al final
        System.out.println("Reserva hecha para " + fecha + " a nombre de " + huesped.getNombre());
    }

    /**
     * SEGUNDA VERSIÓN (SOBRECARGA): reserva con un servicio indicado.
     *
     * @param fecha    fecha de la reserva
     * @param servicio servicio solicitado (ej: "Spa")
     * @param huesped  huésped real que reserva
     * @throws ReservaInvalidaException si fecha o servicio están vacíos o el
     *         huésped es null
     *
     * CONCEPTO: SOBRECARGA / POLIMORFISMO ESTÁTICO (3 parámetros). El
     * compilador decide por la CANTIDAD y TIPO de argumentos de la llamada.
     */
    public void hacerReserva(String fecha, String servicio, Huesped huesped)
            throws ReservaInvalidaException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new ReservaInvalidaException("La fecha no puede estar vacía.");
        }
        if (huesped == null) {
            throw new ReservaInvalidaException("Debe indicarse un huésped válido.");
        }
        if (servicio == null || servicio.trim().isEmpty()) {
            throw new ReservaInvalidaException("El servicio no puede estar vacío.");
        }

        Reserva reserva = new Reserva(fecha, servicio, huesped, this);
        reservas.add(reserva);
        System.out.println("Reserva hecha para " + fecha + " con servicio " + servicio
                + " a nombre de " + huesped.getNombre());
    }

    /**
     * Genera el reporte del recepcionista.
     *
     * @return texto con nombre, id, cargo y cantidad de reservas
     *
     * CONCEPTO: SOBREESCRITURA (@Override) y POLIMORFISMO DINÁMICO. Se usan
     * los getters heredados porque los atributos de Empleado son private.
     */
    @Override
    public String generarReporte() {
        return "=== RECEPCIONISTA ===\nNombre: " + getNombre()
                + "\nID: " + getId()
                + "\nCargo: " + getCargo()
                + "\nReservas gestionadas: " + reservas.size(); // size() = cantidad
    }

    /**
     * @return la lista de reservas gestionadas por este recepcionista
     *         (ENCAPSULAMIENTO: se accede al atributo private mediante getter)
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
}
