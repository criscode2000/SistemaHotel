/*
 * ============================================================================
 * CLASE: App
 * ============================================================================
 * PARA QUÉ SIRVE:
 *   Es el punto de entrada del programa (contiene main). Muestra un menú de
 *   consola, lee los datos que escribe el usuario y delega el trabajo en las
 *   clases del modelo (Hotel, Habitacion, Recepcionista, ...).
 *
 * RESPONSABILIDAD EN EL SISTEMA:
 *   Interacción con el usuario (entrada/salida) y CAPTURA de las excepciones
 *   para mostrar mensajes claros sin que el programa se caiga.
 *   La lógica de negocio NO está aquí: está en las clases del modelo.
 *
 * CONCEPTOS DE POO QUE APLICA:
 *   - MANEJO DE EXCEPCIONES: try/catch con las 4 excepciones personalizadas,
 *     IllegalArgumentException e InputMismatchException.
 *   - POLIMORFISMO: se crean HuespedRegular/HuespedVIP pero se guardan en
 *     ArrayList<Huesped>; se llama obtenerInfo() sin importar el tipo.
 *   - ARRAYLIST: lista temporal huespedesRegistrados.
 *   - ASOCIACIÓN: la reserva se hace con el huésped REAL encontrado (no null).
 *   - MODIFICADORES DE ACCESO: private static para ayudas internas.
 *
 * CLASES RELACIONADAS:
 *   Hotel, Habitacion, Recepcionista, Camarero, Huesped, HuespedRegular,
 *   HuespedVIP y las cuatro excepciones.
 *
 * DECISIÓN DE DISEÑO (opción 4):
 *   Hotel.buscarHuesped solo encuentra huéspedes ALOJADOS en una habitación.
 *   Por eso, para hacer una reserva (o buscar/hacer check-out) el huésped
 *   debe haber sido alojado antes con la opción 3.
 * ============================================================================
 */
package com.hotel.app;

import com.hotel.excepciones.EmpleadoNoEncontradoException;
import com.hotel.excepciones.HabitacionLlenaException;
import com.hotel.excepciones.HuespedNoEncontradoException;
import com.hotel.excepciones.ReservaInvalidaException;
import com.hotel.modelo.Camarero;
import com.hotel.modelo.Habitacion;
import com.hotel.modelo.Hotel;
import com.hotel.modelo.Huesped;
import com.hotel.modelo.HuespedRegular;
import com.hotel.modelo.HuespedVIP;
import com.hotel.modelo.Recepcionista;
import com.hotel.modelo.Reserva;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner; // Scanner: lee lo que el usuario escribe en consola

public class App {

    /**
     * MÉTODO main: Java empieza a ejecutar el programa aquí.
     * "static": se puede ejecutar sin crear un objeto App.
     * "void": no devuelve nada.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ARRAYLIST: huéspedes que se registran (aún sin habitación).
        // Es una lista TEMPORAL: se pierde al cerrar el programa.
        ArrayList<Huesped> huespedesRegistrados = new ArrayList<>();

        // El hotel crea internamente sus 3 habitaciones iniciales (COMPOSICIÓN).
        Hotel hotel = new Hotel("Hotel Sabana Real");

        // Personal inicial. Se guardan como Empleado dentro del hotel (POLIMORFISMO).
        Recepcionista r1 = new Recepcionista("Camila Prieto", "R001", "Recepción día");
        Recepcionista r2 = new Recepcionista("Andrés Mora", "R002", "Recepción noche");
        Camarero c1 = new Camarero("Luz Marina Díaz", "C001", "Housekeeping", "Piso 1");
        hotel.agregarEmpleado(r1);
        hotel.agregarEmpleado(r2);
        hotel.agregarEmpleado(c1);

        int opcion;
        // do-while: el menú se muestra al menos una vez y se repite hasta opción 0.
        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Elija una opción: ");

            // switch: elige qué bloque ejecutar según el valor de "opcion".
            // "break" evita que se ejecuten los casos siguientes.
            switch (opcion) {
                case 1:
                    registrarHuesped(sc, huespedesRegistrados);
                    break;
                case 2:
                    listarHuespedesRegistrados(huespedesRegistrados);
                    break;
                case 3:
                    alojarHuesped(sc, hotel, huespedesRegistrados);
                    break;
                case 4:
                    hacerReserva(sc, hotel);
                    break;
                case 5:
                    verHuespedesDeHabitacion(sc, hotel);
                    break;
                case 6:
                    verReservasDeRecepcionista(sc, hotel);
                    break;
                case 7:
                    hacerCheckOut(sc, hotel);
                    break;
                case 8:
                    buscarHuesped(sc, hotel);
                    break;
                case 9:
                    hotel.generarReporteGeneral();
                    break;
                case 10:
                    crearHabitacion(sc, hotel);
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                    break;
                default: // cualquier otro número
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);

        sc.close(); // libera el recurso Scanner
    }

    /**
     * Lee un entero del usuario y NO termina hasta recibir uno válido.
     *
     * @param sc      Scanner con el que se lee la consola
     * @param mensaje texto que se muestra al pedir el dato
     * @return el entero ingresado
     *
     * CONCEPTO: MANEJO DE EXCEPCIONES. sc.nextInt() lanza InputMismatchException
     * si el usuario escribe algo que no es un entero; se captura aquí dentro
     * y se limpia el buffer con sc.nextLine() para no entrar en un ciclo
     * infinito (el texto inválido seguiría en el buffer).
     */
    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) { // se repite hasta que "return" termine el método
            System.out.print(mensaje);
            try {
                int numero = sc.nextInt();
                sc.nextLine(); // consume el salto de línea que deja nextInt()
                return numero;
            } catch (InputMismatchException e) {
                sc.nextLine(); // descarta el texto inválido
                System.out.println("Error: debe ingresar un número entero.");
            }
        }
    }

    /** Imprime las opciones del menú principal. */
    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========== SISTEMA DE GESTIÓN HOTELERA ==========");
        System.out.println("1.  Registrar huésped");
        System.out.println("2.  Listar huéspedes registrados");
        System.out.println("3.  Alojar huésped en una habitación");
        System.out.println("4.  Hacer una reserva");
        System.out.println("5.  Ver huéspedes de una habitación");
        System.out.println("6.  Ver reservas de un recepcionista");
        System.out.println("7.  Check-out de un huésped");
        System.out.println("8.  Buscar huésped por documento");
        System.out.println("9.  Reporte general del hotel");
        System.out.println("10. Crear nueva habitación");
        System.out.println("0.  Salir");
        System.out.println("=================================================");
    }

    // ------------------------------------------------------------------
    // OPCIÓN 1: registrar huésped
    // ------------------------------------------------------------------

    /**
     * Pide los datos y crea un HuespedRegular o un HuespedVIP.
     *
     * @param sc         Scanner de consola
     * @param registrados lista donde se guarda el huésped creado
     *
     * CONCEPTO: POLIMORFISMO + VALIDACIONES. Ambos tipos se guardan en
     * ArrayList<Huesped>. Si algún dato es inválido, el constructor lanza
     * IllegalArgumentException y aquí se captura.
     */
    private static void registrarHuesped(Scanner sc, ArrayList<Huesped> registrados) {
        int tipo = leerEntero(sc, "Tipo de huésped (1 = Regular, 2 = VIP): ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido.");
            return; // vuelve al menú
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        int edad = leerEntero(sc, "Edad: ");
        int noches = leerEntero(sc, "Noches: ");

        try {
            Huesped nuevo; // variable del tipo ABSTRACTO: puede guardar cualquier subtipo
            if (tipo == 1) {
                nuevo = new HuespedRegular(nombre, documento, edad, noches);
            } else {
                int descuento = leerEntero(sc, "Descuento (0 a 50): ");
                nuevo = new HuespedVIP(nombre, documento, edad, noches, descuento);
            }
            registrados.add(nuevo);
            System.out.println("Huésped registrado: " + nuevo.obtenerInfo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 2: listar huéspedes registrados
    // ------------------------------------------------------------------

    /**
     * Muestra todos los huéspedes registrados, tengan o no habitación.
     *
     * CONCEPTO: POLIMORFISMO DINÁMICO (obtenerInfo()).
     */
    private static void listarHuespedesRegistrados(ArrayList<Huesped> registrados) {
        if (registrados.isEmpty()) {
            System.out.println("No hay huéspedes registrados.");
            return;
        }
        for (Huesped h : registrados) {
            System.out.println(h.obtenerInfo());
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 3: alojar huésped
    // ------------------------------------------------------------------

    /**
     * Aloja un huésped ya registrado en una habitación existente.
     *
     * CONCEPTO: AGREGACIÓN (el huésped ya existía y se agrega a la habitación)
     * y MANEJO DE EXCEPCIONES (HabitacionLlenaException).
     */
    private static void alojarHuesped(Scanner sc, Hotel hotel, ArrayList<Huesped> registrados) {
        System.out.print("Número de habitación: ");
        String numero = sc.nextLine();
        Habitacion habitacion = hotel.buscarHabitacion(numero);
        if (habitacion == null) { // buscarHabitacion devuelve null si no existe
            System.out.println("Error: la habitación " + numero + " no existe.");
            return;
        }

        System.out.print("Documento del huésped: ");
        String documento = sc.nextLine();
        Huesped huesped = buscarEnRegistrados(registrados, documento);
        if (huesped == null) {
            System.out.println("Error: el huésped con documento " + documento
                    + " no está registrado.");
            return;
        }

        // Evita alojar dos veces al mismo huésped (mantiene los datos coherentes).
        if (estaAlojado(hotel, documento)) {
            System.out.println("Error: el huésped ya está alojado en una habitación.");
            return;
        }

        try {
            habitacion.alojarHuesped(huesped);
        } catch (HabitacionLlenaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 4: hacer reserva (sigue el pseudocódigo del enunciado)
    // ------------------------------------------------------------------

    /**
     * Hace una reserva con un recepcionista y un huésped REALES.
     *
     * CONCEPTO: ASOCIACIÓN (Recepcionista -> Reserva -> Huesped) y
     * SOBRECARGA: según la respuesta se llama a una u otra versión de
     * hacerReserva (el compilador distingue por los parámetros).
     */
    private static void hacerReserva(Scanner sc, Hotel hotel) {
        hotel.listarRecepcionistas(); // lista el personal REAL, sin datos quemados
        System.out.print("ID del recepcionista: ");
        String id = sc.nextLine();

        try {
            Recepcionista recepcionista = hotel.buscarRecepcionista(id);

            System.out.print("Documento del huésped (debe estar alojado): ");
            String documento = sc.nextLine();
            Huesped huesped = hotel.buscarHuesped(documento); // puede lanzar excepción

            System.out.print("Fecha de la reserva: ");
            String fecha = sc.nextLine();
            System.out.print("¿Desea agregar un servicio? (s/n): ");
            String respuesta = sc.nextLine();

            if (respuesta.trim().equalsIgnoreCase("s")) {
                System.out.print("Servicio: ");
                String servicio = sc.nextLine();
                recepcionista.hacerReserva(fecha, servicio, huesped); // 3 parámetros
            } else {
                recepcionista.hacerReserva(fecha, huesped);           // 2 parámetros
            }
        } catch (EmpleadoNoEncontradoException | HuespedNoEncontradoException
                | ReservaInvalidaException e) {
            // multi-catch: varias excepciones con el mismo tratamiento
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 5: ver huéspedes de una habitación
    // ------------------------------------------------------------------

    private static void verHuespedesDeHabitacion(Scanner sc, Hotel hotel) {
        System.out.print("Número de habitación: ");
        String numero = sc.nextLine();
        Habitacion habitacion = hotel.buscarHabitacion(numero);
        if (habitacion == null) {
            System.out.println("Error: la habitación " + numero + " no existe.");
        } else {
            habitacion.listarHuespedes();
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 6: ver reservas de un recepcionista
    // ------------------------------------------------------------------

    private static void verReservasDeRecepcionista(Scanner sc, Hotel hotel) {
        hotel.listarRecepcionistas();
        System.out.print("ID del recepcionista: ");
        String id = sc.nextLine();
        try {
            Recepcionista recepcionista = hotel.buscarRecepcionista(id);
            if (recepcionista.getReservas().isEmpty()) {
                System.out.println("Este recepcionista aún no tiene reservas.");
            } else {
                // Se recorre su ArrayList<Reserva> y se muestra cada getInfo().
                for (Reserva r : recepcionista.getReservas()) {
                    System.out.println(r.getInfo());
                }
            }
        } catch (EmpleadoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 7: check-out
    // ------------------------------------------------------------------

    /**
     * Hace el check-out: busca al huésped en el hotel, localiza su habitación
     * y lo libera. (El huésped sigue en la lista de registrados.)
     */
    private static void hacerCheckOut(Scanner sc, Hotel hotel) {
        System.out.print("Documento del huésped: ");
        String documento = sc.nextLine();
        try {
            Huesped huesped = hotel.buscarHuesped(documento); // lanza si no está alojado
            for (Habitacion h : hotel.getHabitaciones()) {
                if (h.getHuespedes().contains(huesped)) { // ¿está en esta habitación?
                    h.liberarHuesped(documento);
                    break; // ya se hizo el check-out: se sale del for
                }
            }
        } catch (HuespedNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 8: buscar huésped
    // ------------------------------------------------------------------

    private static void buscarHuesped(Scanner sc, Hotel hotel) {
        System.out.print("Documento a buscar: ");
        String documento = sc.nextLine();
        try {
            Huesped h = hotel.buscarHuesped(documento);
            System.out.println(h.obtenerInfo()); // POLIMORFISMO DINÁMICO
        } catch (HuespedNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // OPCIÓN 10: crear habitación en tiempo de ejecución
    // ------------------------------------------------------------------

    /**
     * Crea una habitación nueva. El Hotel crea el objeto (COMPOSICIÓN); si la
     * capacidad es <= 0, Habitacion lanza IllegalArgumentException.
     */
    private static void crearHabitacion(Scanner sc, Hotel hotel) {
        System.out.print("Número de la nueva habitación: ");
        String numero = sc.nextLine();
        int capacidad = leerEntero(sc, "Capacidad: ");
        try {
            hotel.agregarHabitacion(numero, capacidad);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // Métodos auxiliares
    // ------------------------------------------------------------------

    /**
     * Busca un huésped en la lista de registrados por documento.
     *
     * @return el huésped, o null si no está registrado (aquí null solo indica
     *         "no encontrado"; nunca se usa para una reserva)
     */
    private static Huesped buscarEnRegistrados(ArrayList<Huesped> registrados, String documento) {
        for (Huesped h : registrados) {
            if (h.getDocumento().equals(documento)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Indica si un huésped ya está alojado en alguna habitación del hotel.
     *
     * @return true si Hotel.buscarHuesped lo encuentra; false si lanza la excepción
     */
    private static boolean estaAlojado(Hotel hotel, String documento) {
        try {
            hotel.buscarHuesped(documento);
            return true;
        } catch (HuespedNoEncontradoException e) {
            return false;
        }
    }
}
