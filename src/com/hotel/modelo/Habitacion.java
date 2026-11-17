/*
 * ============================================================================
 * CLASE: Habitacion
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Representa una habitación del hotel con una capacidad máxima y la lista
 *   de huéspedes que están alojados en ella.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Alojar, buscar, liberar (check-out) y listar huéspedes.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - AGREGACIÓN con Huesped: la habitación puede existir vacía y los
 *     huéspedes existen aunque no tengan habitación (se crean fuera y se
 *     RECIBEN por parámetro; la habitación no los crea).
 *   - ARRAYLIST<Huesped>: se declara con el tipo ABSTRACTO para guardar
 *     regulares y VIP juntos.
 *   - POLIMORFISMO DINÁMICO: al llamar h.obtenerInfo() Java ejecuta la
 *     versión de HuespedRegular o de HuespedVIP según el objeto real.
 *   - MANEJO DE EXCEPCIONES: HabitacionLlenaException y
 *     HuespedNoEncontradoException.
 *   - VALIDACIONES, CONSTRUCTORES, ENCAPSULAMIENTO.
 *
 * CLASES RELACIONADAS:
 *   Huesped (y subclases), Hotel (la crea: composición), excepciones, App.
 * ============================================================================
 */
package com.hotel.modelo;

import com.hotel.excepciones.HabitacionLlenaException;
import com.hotel.excepciones.HuespedNoEncontradoException;
import java.util.ArrayList;

public class Habitacion {

    private String numero;
    private int capacidad;
    private ArrayList<Huesped> huespedes; // AGREGACIÓN + ARRAYLIST

    /**
     * CONSTRUCTOR de Habitacion.
     *
     * @param numero    número de la habitación (ej: "101")
     * @param capacidad cantidad máxima de huéspedes (mayor a 0)
     * @throws IllegalArgumentException si capacidad &lt;= 0
     *
     * CONCEPTO: CONSTRUCTORES + VALIDACIONES. La lista nace vacía: una
     * habitación puede existir sin huéspedes (agregación).
     */
    public Habitacion(String numero, int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser mayor a 0.");
        }
        this.numero = numero;
        this.capacidad = capacidad;
        this.huespedes = new ArrayList<>();
    }

    /**
     * Aloja un huésped si hay cupo.
     *
     * @param h huésped (regular o VIP) ya creado fuera de la habitación
     * @throws HabitacionLlenaException si ya se alcanzó la capacidad
     *
     * CONCEPTO: AGREGACIÓN + POLIMORFISMO. El parámetro es de tipo Huesped
     * (abstracto), por lo que acepta cualquier subclase.
     */
    public void alojarHuesped(Huesped h) throws HabitacionLlenaException {
        if (huespedes.size() >= capacidad) {
            throw new HabitacionLlenaException("Habitación " + numero + " sin cupo disponible.");
        }
        huespedes.add(h);
        System.out.println("Huésped " + h.getNombre() + " alojado en habitación " + numero);
    }

    /**
     * Realiza el check-out: elimina al huésped con ese documento.
     *
     * @param documento documento del huésped a liberar
     * @throws HuespedNoEncontradoException si no está en esta habitación
     *
     * CONCEPTO: ARRAYLIST (remove) + MANEJO DE EXCEPCIONES. Se reutiliza
     * buscarHuesped, que ya lanza la excepción si no lo encuentra.
     */
    public void liberarHuesped(String documento) throws HuespedNoEncontradoException {
        Huesped encontrado = buscarHuesped(documento); // puede lanzar la excepción
        huespedes.remove(encontrado); // quita ese objeto de la lista
        System.out.println("Check-out realizado.");
    }

    /**
     * Busca un huésped por documento dentro de esta habitación.
     *
     * @param documento documento a buscar
     * @return el huésped encontrado
     * @throws HuespedNoEncontradoException si ninguno coincide
     *
     * CONCEPTO: ARRAYLIST (recorrido con for-each) + MANEJO DE EXCEPCIONES.
     */
    public Huesped buscarHuesped(String documento) throws HuespedNoEncontradoException {
        // for-each: "para cada Huesped h dentro de la lista huespedes".
        for (Huesped h : huespedes) {
            // equals compara el CONTENIDO de los textos (== compararía referencias).
            if (h.getDocumento().equals(documento)) {
                return h; // "return" termina el método devolviendo el huésped
            }
        }
        throw new HuespedNoEncontradoException(documento);
    }

    /**
     * Imprime la información de todos los huéspedes de la habitación.
     *
     * CONCEPTO: POLIMORFISMO DINÁMICO. La variable h es de tipo Huesped, pero
     * obtenerInfo() ejecuta la versión del tipo REAL del objeto.
     */
    public void listarHuespedes() {
        if (huespedes.isEmpty()) { // isEmpty(): true si la lista no tiene elementos
            System.out.println("No hay huéspedes en esta habitación.");
        } else {
            for (Huesped h : huespedes) {
                System.out.println(h.obtenerInfo());
            }
        }
    }

    /** @return el número de la habitación. */
    public String getNumero() {
        return numero;
    }

    /** @return la capacidad máxima de la habitación. */
    public int getCapacidad() {
        return capacidad;
    }

    /** @return la lista de huéspedes alojados. */
    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }
}
