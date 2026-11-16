# Fase 2 — Ejercicio: streams sobre los pedidos de la tienda

**Duración:** 45 minutos · **Formato:** por parejas

## Contexto

El departamento comercial quiere unos informes rápidos sobre los pedidos de la tienda. Tienes los datos en memoria y tienes que calcularlos **con streams, sin bucles `for` ni `while`**.

## Pasos

1. Abre la carpeta `ejercicio/` en IntelliJ (**File → Open**, selecciona el `pom.xml` y ábrelo como proyecto).
2. Echa un vistazo a los datos en `DatosTienda`: 5 clientes, 5 productos y 16 pedidos.
3. **Calentamiento:** implementa `CalentamientoStreams` hasta que `CalentamientoStreamsTest` pase a verde. Son ejercicios cortos con una operación de stream cada uno (ver más abajo).
4. Ejecuta `EjerciciosStreamsTest`. Todos los tests fallan: es lo esperado.
5. Implementa los métodos de `EjerciciosStreams` hasta que los tests pasen a verde.

## Calentamiento (`CalentamientoStreams`)

Cada ejercicio muestra en su Javadoc cómo se haría con un bucle `for` y qué operación de stream lo sustituye. Hazlos en orden: cada uno añade una sola pieza nueva.

**Aquí no hay regla común:** cada ejercicio dice si los cancelados cuentan o no.

| # | Método | Operación nueva |
|---|---|---|
| 1 | `idsDePedidos` | `map` + `toList` |
| 2 | `pedidosPendientes` | `filter` |
| 3 | `contarCancelados` | `count` |
| 4 | `nombresDeClientes` | `distinct` + `sorted` |
| 5 | `ciudades` | dos `map` seguidos + `Collectors.toSet` |
| 6 | `haPedidoAlgo` | `anyMatch` |
| 7 | `buscarPorId` | `findFirst` y `Optional` |
| 8 | `pedidoMasCaro` | `max` con `Comparator.comparing` |
| 9 | `pedidosPorEstado` | `groupingBy` + `counting` |
| 10 | `unidadesVendidas` | `flatMap` + `mapToInt` + `sum` |

**Regla para traducir un bucle a un stream:** el `if` se convierte en `filter`, lo que metes en la lista se convierte en `map`, un bucle dentro de otro se convierte en `flatMap` y lo que devuelves al final es la operación terminal (`toList`, `count`, `sum`...).

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
