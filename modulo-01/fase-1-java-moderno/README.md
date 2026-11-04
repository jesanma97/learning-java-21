# Fase 1 — Ejercicio: modernizar código legacy

**Duración:** 40 minutos · **Formato:** por parejas

## Contexto

Acabas de entrar en un equipo que mantiene la tienda online. El código se escribió hace años en Java 8 y tu primera tarea es **modernizarlo a Java 21 sin cambiar lo que hace**.

Es exactamente lo que te puede tocar en una entrevista: "aquí tienes este código, mejóralo y explica por qué".

## Cómo trabajar en pareja

- Una persona **escribe** (conductor) y la otra **piensa en voz alta** (navegante).
- Cambiad de rol cada 15 minutos.
- El navegante explica *por qué* se hace cada cambio. Si no sabe explicarlo, no se hace.

## Pasos

1. Abre la carpeta `ejercicio/` en IntelliJ (**File → Open**, selecciona el `pom.xml` y ábrelo como proyecto).
2. **Antes de tocar nada**, ejecuta `DemoLegacy` y copia su salida en un fichero de texto. Es tu red de seguridad.
3. Moderniza el código del paquete `com.tienda.legacy`.
4. Ejecuta de nuevo la demo: la salida debe ser **la misma**, salvo donde hayas corregido un error a propósito.

## Qué tienes que modernizar

| Fichero | Qué buscar |
|---|---|
| `ProductoDTO` | ~150 líneas de código repetitivo. ¿Qué herramienta de Java moderno lo resuelve en una? |
| `Pago` y sus 3 subclases | Una jerarquía cerrada: no van a aparecer tipos de pago nuevos sin que lo sepamos. |
| `GestorTienda.describirEstado` / `sePuedeCancelar` | `switch` con `break` y estados como `String`. |
| `GestorTienda.calcularComision` | Cadena de `instanceof` con casts manuales. |
| `GestorTienda.toJson` / `consultaProductosActivos` | Texto multilínea construido a base de `+` y `append`. |
| `GestorTienda.agruparPorCategoria` | Tipos genéricos repetidos a ambos lados del `=`. |

**Herramientas que deberías usar:** records, `var`, text blocks, switch expressions, pattern matching (`instanceof`, `switch`, record patterns) y `sealed`.

Puedes cambiar tipos y firmas si lo justificas (por ejemplo, sustituir un `String` por algo más seguro).

## Reto extra

En este código hay **al menos un problema que no es de estilo**, sino un fallo de seguridad. Encuéntralo y corrígelo.

## Para pensar (lo preguntaremos al final)

- ¿Qué diferencia hay entre un record y una clase normal?
- ¿Un record puede tener métodos? ¿Y validación en el constructor?
- El `ProductoDTO` tiene `setStock`. Un record no tiene setters. ¿Cómo "cambias" el stock entonces?
