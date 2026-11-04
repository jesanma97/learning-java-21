# Fase 4 — Rúbrica del simulacro

> Se reparte **después** del simulacro, para la revisión en grupo. Sirve para que cada alumno se autoevalúe y para comentar en grupo qué valoraría y qué penalizaría un entrevistador.

**Total: 100 puntos.** En una entrevista real no hay puntos, pero sí estas mismas preguntas en la cabeza del entrevistador.

## 1. Funcionalidad (30 puntos)

| Criterio | 0 | Parcial | Completo |
|---|---|---|---|
| Crear pedido (10) | No funciona | Crea, pero sin precio del catálogo o sin total | Crea con nombre, precio y total correctos; estado `PENDIENTE` |
| Listar (5) | No existe | — | Devuelve todos los pedidos |
| Consultar por id (5) | No existe | Funciona, pero un id inexistente da 500 o 200 vacío | 200 con el pedido; 404 si no existe |
| Cancelar (10) | No existe | Cancela, pero deja cancelar dos veces o cancela cualquier estado | Solo `PENDIENTE` o `PAGADO`; error claro en otro caso |

## 2. Diseño REST (20 puntos)

| Criterio | 0 | Parcial | Completo |
|---|---|---|---|
| Recursos y URLs (5) | Verbos en la URL (`/crearPedido`) | Mezcla | Recurso en plural: `/pedidos`, `/pedidos/{id}` |
| Verbos HTTP (5) | Todo con `GET` o todo con `POST` | Correctos salvo cancelar mal justificado | `POST` para crear, `GET` para leer; cancelar con un verbo **justificado** (no `DELETE`) |
| Códigos de estado (10) | Siempre 200 | Algunos correctos | 201 + `Location` al crear; 200; 400 validación; 404 no existe; 409 o 422 en reglas de negocio, de forma coherente |

## 3. Validación y errores (20 puntos)

| Criterio | 0 | Parcial | Completo |
|---|---|---|---|
| Validación de entrada (8) | Ninguna | `if` a mano en el controlador, o solo algunos campos | Bean Validation en el DTO, `@Valid`, líneas validadas una a una |
| Formato de error (7) | Texto plano, traza de la excepción o formatos distintos | JSON propio, pero inconsistente | Mismo formato en todos los errores (`ProblemDetail`); en validación, lista de campos con su mensaje |
| Manejo centralizado (5) | `try/catch` en cada método del controlador | Parcialmente centralizado | `@RestControllerAdvice` con excepciones propias |

## 4. Código y arquitectura (20 puntos)

| Criterio | 0 | Parcial | Completo |
|---|---|---|---|
| Capas (6) | Todo en el controlador | Servicio, pero con lógica de negocio en el controlador | Controlador fino (HTTP), servicio (casos de uso), dominio (reglas) |
| DTOs (5) | Devuelve y recibe el objeto de dominio | Solo en entrada o solo en salida | DTO de entrada y de salida separados del dominio |
| Inyección y Spring (4) | `new` de servicios o `@Autowired` en campos | — | Inyección por constructor, campos `final` |
| Calidad (5) | Nombres confusos, código muerto, magic numbers | Mejorable | Nombres claros, Java moderno (records, enums, streams), sin duplicación |

## 5. Comunicación y proceso (10 puntos)

| Criterio | 0 | Parcial | Completo |
|---|---|---|---|
| Explicación (5) | No sabe justificar sus decisiones | Justifica algunas | Explica en 2 minutos las decisiones y sus alternativas |
| Proceso (5) | Se atasca perfeccionando una parte y no termina | Termina, pero sin probar | Camino feliz primero, prueba cada endpoint, commits frecuentes |

---

## Lo que un entrevistador valora

- **Que funcione** y lo hayas probado delante de él.
- **Decisiones justificadas**, aunque no sean las mismas que las suyas: "he elegido 422 porque la petición es válida en forma, pero no se puede procesar".
- **Preguntar** cuando un requisito es ambiguo ("¿un pedido pagado se puede cancelar?").
- **Priorizar**: el núcleo funcionando antes que los detalles.
- **Conocer las alternativas**: "PATCH también sería válido, pero…".

## Lo que un entrevistador penaliza

| Señal | Por qué preocupa |
|---|---|
| Devolver la entidad de dominio en la API | Expone datos internos y acopla la API al modelo |
| 200 para todo, con `{"error": "..."}` en el cuerpo | No conoce HTTP |
| `try/catch` en cada endpoint | Duplicación; no conoce `@RestControllerAdvice` |
| La traza de la excepción en la respuesta | Fuga de información interna: problema de seguridad |
| `@Autowired` en campos | Código de tutorial antiguo |
| Lógica de negocio en el controlador | No separa responsabilidades |
| `DELETE` para cancelar | Cancelar no es borrar: el pedido sigue existiendo |
| No haber ejecutado el código | "En mi máquina compila" no vale |
| Quedarse callado 10 minutos | En live coding se evalúa cómo piensas, no solo el resultado |
