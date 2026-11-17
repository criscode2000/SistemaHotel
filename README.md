# Sistema de Gestión Hotelera — Proyecto Final de POO (Java)

Aplicación de consola para administrar habitaciones, huéspedes, empleados y
reservas de un hotel. Cada archivo `.java` está documentado pedagógicamente
(propósito, conceptos de POO aplicados, parámetros, retornos y relaciones).

## Estructura

```
SistemaHotel/
├── diagrama-hotel.puml            ← diagrama de clases (PlantUML)
└── src/com/hotel/
    ├── modelo/        Empleado (abstracta), Recepcionista, Camarero,
    │                  Huesped (abstracta), HuespedRegular, HuespedVIP (final),
    │                  Reserva, Habitacion, Hotel, Validador
    ├── excepciones/   HabitacionLlenaException, HuespedNoEncontradoException,
    │                  EmpleadoNoEncontradoException, ReservaInvalidaException
    └── app/           App (menú principal)
```

## Cómo compilar y ejecutar

Requiere JDK 17 o superior (probado con JDK 21). Desde la carpeta `SistemaHotel`:

```bash
# Linux / macOS
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.hotel.app.App
```

```powershell
# Windows (PowerShell)
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
chcp 65001
java -cp out com.hotel.app.App
```

**En VS Code:** abre la carpeta `SistemaHotel` (con la extensión *Extension Pack
for Java*), abre `src/com/hotel/app/App.java` y pulsa *Run*. Si las tildes se ven
mal en la terminal, ejecuta `chcp 65001` (Windows) antes de correr el programa.

## Menú

| Opción | Acción |
|---|---|
| 1 | Registrar huésped (1 = Regular, 2 = VIP) |
| 2 | Listar huéspedes registrados |
| 3 | Alojar huésped en una habitación |
| 4 | Hacer una reserva |
| 5 | Ver huéspedes de una habitación |
| 6 | Ver reservas de un recepcionista |
| 7 | Check-out de un huésped |
| 8 | Buscar huésped por documento |
| 9 | Reporte general del hotel |
| 10 | Crear nueva habitación |
| 0 | Salir |

Datos iniciales: hotel "Hotel Sabana Real"; habitaciones 101 (cap. 2), 102 (cap. 3)
y 201 (cap. 4); recepcionistas R001 y R002; camarero C001. No hay huéspedes
precargados: se registran desde el menú.

## Decisión de diseño documentada (enunciado, opción 4)

`Hotel.buscarHuesped(documento)` solo recorre las habitaciones, por lo que
**únicamente encuentra huéspedes ya alojados**. Decidimos **exigir alojar primero
al huésped (opción 3)** antes de hacerle una reserva (opción 4), buscarlo (8) o
hacerle check-out (7). Así la reserva siempre queda asociada a un huésped real
(nunca `null`) y no se modifica la clase `Hotel` respecto al diagrama.

Otras decisiones menores:

- El check-out (opción 7) saca al huésped de la habitación, pero **sigue en la
  lista de registrados**, por lo que puede alojarse de nuevo.
- La opción 3 impide alojar dos veces al mismo huésped (mantiene coherentes los datos).
- La lista `huespedesRegistrados` es temporal: se pierde al cerrar el programa.

## Conceptos de POO en el proyecto

| Concepto | Dónde |
|---|---|
| Abstracción | `Empleado`, `Huesped` (clases y métodos abstractos) |
| Encapsulamiento | Todos los atributos `private`; acceso por getters `public` |
| Herencia | `Recepcionista`/`Camarero` → `Empleado`; `HuespedRegular`/`HuespedVIP` → `Huesped` |
| Clase `final` | `HuespedVIP` |
| Polimorfismo estático (sobrecarga) | `Recepcionista.hacerReserva` (2 versiones) |
| Polimorfismo dinámico (sobreescritura) | `generarReporte()` y `obtenerInfo()` |
| Composición | `Hotel` crea sus `Habitacion` |
| Agregación | `Habitacion` aloja `Huesped` (`ArrayList<Huesped>`) |
| Asociación | `Recepcionista` → `Reserva` → `Huesped` |
| Validaciones | `Validador` + constructores |
| Excepciones | 4 personalizadas + `IllegalArgumentException` + `InputMismatchException` |

## Diagrama de clases

Genera la imagen pegando `diagrama-hotel.puml` en https://www.plantuml.com/plantuml/uml/
o usa la extensión *PlantUML* de VS Code (`Alt+D`). Es el diagrama del enunciado y
coincide con las firmas del código.
