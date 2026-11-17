/*
 * ============================================================================
 * CLASE: Reserva
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Representa una reserva hecha por un recepcionista a nombre de un huésped,
 *   para una fecha y un servicio.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Guardar los datos de la reserva y conectar (asociar) a un Recepcionista
 *   con un Huesped.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - ASOCIACIÓN: Reserva "conoce" a un Huesped y a un Recepcionista mediante
 *     referencias. Ninguno depende de Reserva para existir (no hay relación
 *     todo-parte).
 *   - ENCAPSULAMIENTO (atributos private, getters public).
 *   - CONSTRUCTORES.
 *
 * CLASES RELACIONADAS:
 *   Recepcionista (la crea y la guarda), Huesped.
 * ============================================================================
 */
package com.hotel.modelo;

public class Reserva {

    private String fecha;
    private String servicio;
    private Huesped huesped;               // ASOCIACIÓN con Huesped
    private Recepcionista recepcionista;   // ASOCIACIÓN con Recepcionista

    /**
     * CONSTRUCTOR de Reserva: asigna los cuatro atributos.
     *
     * @param fecha         fecha de la reserva (texto)
     * @param servicio      servicio reservado
     * @param huesped       huésped REAL (nunca null; lo valida Recepcionista)
     * @param recepcionista recepcionista que gestionó la reserva
     *
     * CONCEPTO: CONSTRUCTORES + ASOCIACIÓN. Se guardan las REFERENCIAS a los
     * objetos existentes; no se crean copias.
     */
    public Reserva(String fecha, String servicio, Huesped huesped, Recepcionista recepcionista) {
        this.fecha = fecha;
        this.servicio = servicio;
        this.huesped = huesped;
        this.recepcionista = recepcionista;
    }

    /**
     * Resume la reserva en una línea.
     *
     * @return texto con fecha, servicio, recepcionista y huésped
     *
     * CONCEPTO: ASOCIACIÓN. Para mostrar nombres se "navega" a los objetos
     * asociados llamando a sus getters.
     */
    public String getInfo() {
        return "Reserva: " + fecha
                + " | Servicio: " + servicio
                + " | Recepcionista: " + recepcionista.getNombre()
                + " | Huésped: " + huesped.getNombre();
    }

    /** @return el huésped asociado a la reserva. */
    public Huesped getHuesped() {
        return huesped;
    }

    /** @return el recepcionista asociado a la reserva. */
    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    /** @return la fecha de la reserva. */
    public String getFecha() {
        return fecha;
    }
}
