/*
 * ============================================================================
 * CLASE: Hotel
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Es la clase principal del modelo: administra las habitaciones y el
 *   personal del hotel.
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Crear/consultar habitaciones, registrar empleados, buscar huéspedes y
 *   recepcionistas y generar el reporte general.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - COMPOSICIÓN con Habitacion: el Hotel CREA sus habitaciones con "new"
 *     (en el constructor y en agregarHabitacion). Si el hotel desaparece, sus
 *     habitaciones también.
 *   - ARRAYLIST<Habitacion> y ARRAYLIST<Empleado> (este último guarda
 *     Recepcionista y Camarero juntos: polimorfismo).
 *   - POLIMORFISMO DINÁMICO: e.generarReporte() en generarReporteGeneral().
 *   - MANEJO DE EXCEPCIONES: captura y lanza excepciones personalizadas.
 *   - "instanceof" y CAST para distinguir recepcionistas.
 *
 * CLASES RELACIONADAS:
 *   Habitacion, Empleado, Recepcionista, Camarero, Huesped, excepciones, App.
 * ============================================================================
 */
package com.hotel.modelo;

import com.hotel.excepciones.EmpleadoNoEncontradoException;
import com.hotel.excepciones.HuespedNoEncontradoException;
import java.util.ArrayList;

public class Hotel {

    private String nombre;
    private ArrayList<Habitacion> habitaciones; // COMPOSICIÓN
    private ArrayList<Empleado> empleados;      // guarda cualquier subtipo de Empleado

    /**
     * CONSTRUCTOR de Hotel. Crea las listas y las habitaciones iniciales.
     *
     * @param nombre nombre del hotel
     *
     * CONCEPTO: COMPOSICIÓN. Las habitaciones se crean AQUÍ DENTRO con "new";
     * no llegan desde afuera. Así la vida de cada Habitacion depende del Hotel.
     */
    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new ArrayList<>();
        this.empleados = new ArrayList<>();

        habitaciones.add(new Habitacion("101", 2));
        habitaciones.add(new Habitacion("102", 3));
        habitaciones.add(new Habitacion("201", 4));
    }

    /**
     * Crea una nueva habitación y la agrega al hotel.
     *
     * @param numero    número de la nueva habitación
     * @param capacidad capacidad (mayor a 0)
     * @throws IllegalArgumentException si capacidad &lt;= 0 (la lanza Habitacion)
     *
     * CONCEPTO: COMPOSICIÓN. Aunque App dispare la acción, el objeto
     * Habitacion lo crea el Hotel. Si la capacidad es inválida, el constructor
     * de Habitacion lanza la excepción y no se agrega ni se imprime nada.
     */
    public void agregarHabitacion(String numero, int capacidad) {
        Habitacion nueva = new Habitacion(numero, capacidad);
        habitaciones.add(nueva);
        System.out.println("Habitación '" + numero + "' creada con capacidad " + capacidad);
    }

    /**
     * Registra un empleado en el hotel.
     *
     * @param e empleado (Recepcionista, Camarero, ...)
     *
     * CONCEPTO: POLIMORFISMO + ARRAYLIST. El parámetro es del tipo abstracto
     * Empleado, así que acepta cualquier subclase.
     */
    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
        System.out.println(e.getNombre() + " registrado en el hotel.");
    }

    /**
     * Busca una habitación por número.
     *
     * @param numero número a buscar
     * @return la habitación, o null si no existe
     */
    public Habitacion buscarHabitacion(String numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero().equals(numero)) {
                return h;
            }
        }
        return null; // "null": no se encontró ninguna habitación
    }

    /**
     * Busca un huésped por documento en TODAS las habitaciones.
     *
     * @param documento documento a buscar
     * @return el huésped encontrado
     * @throws HuespedNoEncontradoException si ninguna habitación lo tiene
     *
     * CONCEPTO: MANEJO DE EXCEPCIONES. Cada habitación lanza la excepción si
     * no lo tiene; aquí se CAPTURA para seguir con la siguiente habitación.
     * Solo si se recorren todas sin éxito, se lanza la excepción al final.
     */
    public Huesped buscarHuesped(String documento) throws HuespedNoEncontradoException {
        for (Habitacion hab : habitaciones) {
            try {
                return hab.buscarHuesped(documento); // si lo halla, termina aquí
            } catch (HuespedNoEncontradoException e) {
                // No estaba en esta habitación: no es un error del hotel,
                // se continúa con la siguiente iteración del for.
            }
        }
        throw new HuespedNoEncontradoException(documento);
    }

    /**
     * Busca un recepcionista por id entre el personal real.
     *
     * @param id id a buscar (ej: "R001")
     * @return el recepcionista encontrado
     * @throws EmpleadoNoEncontradoException si no hay un recepcionista con ese id
     *
     * CONCEPTO: "instanceof" pregunta si el objeto es realmente de ese tipo.
     * Luego se hace un CAST "(Recepcionista) e" para tratarlo como tal,
     * porque la lista guarda el tipo general Empleado.
     */
    public Recepcionista buscarRecepcionista(String id) throws EmpleadoNoEncontradoException {
        for (Empleado e : empleados) {
            if (e instanceof Recepcionista && e.getId().equals(id)) {
                return (Recepcionista) e;
            }
        }
        throw new EmpleadoNoEncontradoException(id);
    }

    /**
     * Imprime los recepcionistas registrados: "id - nombre (cargo)".
     * Consulta la lista real de empleados (sin datos quemados).
     */
    public void listarRecepcionistas() {
        boolean hay = false;
        for (Empleado e : empleados) {
            if (e instanceof Recepcionista) {
                System.out.println(e.getId() + " - " + e.getNombre() + " (" + e.getCargo() + ")");
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay recepcionistas registrados.");
        }
    }

    /**
     * Imprime el reporte general: ocupación de cada habitación, total de
     * empleados y el reporte de cada empleado.
     *
     * CONCEPTO: POLIMORFISMO DINÁMICO. e.generarReporte() ejecuta la versión
     * de Recepcionista o de Camarero según el objeto real.
     */
    public void generarReporteGeneral() {
        System.out.println("===== REPORTE GENERAL: " + nombre + " =====");
        for (Habitacion h : habitaciones) {
            System.out.println("Habitación: " + h.getNumero()
                    + " | Ocupación: " + h.getHuespedes().size() + "/" + h.getCapacidad());
        }
        System.out.println("Total de empleados: " + empleados.size());
        for (Empleado e : empleados) {
            System.out.println(e.generarReporte());
            System.out.println();
        }
    }

    /** @return la lista de habitaciones del hotel. */
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /** @return la lista de empleados del hotel. */
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
