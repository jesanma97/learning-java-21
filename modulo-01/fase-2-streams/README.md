# Fase 2 — Ejercicio: streams sobre los pedidos de la tienda

**Duración:** 45 minutos · **Formato:** por parejas

## Contexto

El departamento comercial quiere unos informes rápidos sobre los pedidos de la tienda. Tienes los datos en memoria y tienes que calcularlos **con streams, sin bucles `for` ni `while`**.

## Pasos

1. Abre la carpeta `ejercicio/` en IntelliJ (**File → Open**, selecciona el `pom.xml` y ábrelo como proyecto).
2. Echa un vistazo a los datos en `DatosTienda`: 5 clientes, 5 productos y 16 pedidos.
3. Ejecuta `EjerciciosStreamsTest`. Todos los tests fallan: es lo esperado.
4. Implementa los métodos de `EjerciciosStreams` hasta que los tests pasen a verde.

## Regla común

**Los pedidos `CANCELADO` no cuentan en ningún ejercicio.** Los tests están pensados para que, si se te olvida, falle el resultado.

## Ejercicios

| # | Método | Qué calcula |
|---|---|---|
| 1 | `totalFacturado` | El total facturado. |
| 2 | `pedidosPorCliente` | Los pedidos agrupados por cliente. |
| 3 | `productoMasVendido` | El producto con más unidades vendidas. |
| 4 | `clientesConMasDe3Pedidos` | Los clientes con más de 3 pedidos, ordenados por nombre. |
| 5 | `facturacionPorCategoria` | *Extra:* importe facturado por categoría. |
| 6 | `pedidosDelMasRecienteAlMasAntiguo` | *Extra:* pedidos del más reciente al más antiguo. |

## Herramientas que vas a necesitar

`filter`, `map`, `flatMap`, `sorted`, `reduce`, `max`, `collect`, `Collectors.groupingBy`, `Collectors.summingInt`, `Comparator.comparing` y `Optional`.

## Para pensar (lo preguntaremos al final)

- En el ejercicio 2, la clave del mapa es un `Cliente`. ¿Qué necesita una clase para funcionar bien como clave de un `HashMap`?
- ¿Por qué el ejercicio 3 devuelve un `Optional` y no un `Producto`?
- ¿Qué diferencia hay entre `map` y `flatMap`? ¿En qué ejercicio has necesitado cada uno?
