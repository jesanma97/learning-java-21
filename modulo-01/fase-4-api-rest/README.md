# Fase 4 — Simulacro: API REST de pedidos

**Duración:** 45 minutos, con reloj · **Formato:** individual · **Material permitido:** tu proyecto, la documentación oficial y tu código de sesiones anteriores

## Contexto

Esto es un simulacro de prueba técnica de live coding. El enunciado es deliberadamente parecido a los que se piden en entrevistas reales:

> "Crea una API REST para gestionar pedidos: crear, listar, consultar por id y cancelar. Valida los datos de entrada y devuelve errores claros. Los datos pueden estar en memoria."

Trabaja sobre **tu proyecto `tienda-online`** de la fase 3 (o sobre el de la demo de hoy). El catálogo de productos ya existe: úsalo para obtener el nombre y el precio de cada producto.

## Requisitos funcionales

### Un pedido tiene

| Dato | Detalle |
|---|---|
| `id` | Lo genera el servidor (número secuencial) |
| `cliente` | Email del cliente |
| `fecha` | Momento de creación; lo pone el servidor |
| `estado` | `PENDIENTE` al crearlo. Puede pasar a `CANCELADO` |
| `lineas` | Una o más: SKU del producto, nombre, precio unitario, cantidad y subtotal |
| `total` | Suma de los subtotales |

### Operaciones

1. **Crear un pedido.** El cliente envía el email y las líneas (SKU y cantidad). El servidor completa nombre y precio desde el catálogo.
2. **Listar** todos los pedidos.
3. **Consultar** un pedido por su id.
4. **Cancelar** un pedido. Solo se pueden cancelar pedidos `PENDIENTE` o `PAGADO`.

**Diseña tú las URLs, los verbos HTTP y los códigos de respuesta.** Tendrás que justificarlos.

### Reglas de validación

- `cliente`: obligatorio y con formato de email.
- `lineas`: al menos una.
- En cada línea: `sku` obligatorio; `cantidad` entre 1 y 99.

### Errores

Todos los errores deben devolver un cuerpo JSON **con el mismo formato**, que explique qué ha fallado. Como mínimo, contempla:

- Datos de entrada inválidos (indicando qué campos fallan).
- Pedido que no existe.
- SKU que no está en el catálogo.
- Cancelar un pedido que ya no se puede cancelar.

## Cómo enfocarlo (consejo de entrevista)

1. **Primero que funcione** el camino feliz de crear y consultar. Luego listar y cancelar. Luego validación y errores.
2. **Haz commit** cada vez que algo funcione.
3. **Prueba cada endpoint** según lo terminas (navegador, `curl`, Bruno, Postman o el cliente HTTP del IDE).
4. Si dudas de un requisito, **pregunta**: en una entrevista real, preguntar es una virtud.
5. Mejor 4 endpoints sencillos y funcionando que 2 perfectos y 2 sin hacer.

## Al terminar

Haz commit con el mensaje `feat: API REST de pedidos con validación y errores` y prepárate para explicar en 2 minutos:

- Qué URL y qué verbo has elegido para cancelar, y por qué.
- Qué código de respuesta devuelves en cada caso de error.
- Por qué no devuelves directamente tu objeto de dominio.
