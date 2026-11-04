# Curso de Backend Java moderno — Preparación para entrevistas técnicas

Curso práctico para desarrolladores con experiencia en stacks legacy que quieren actualizarse al backend Java actual y **superar entrevistas técnicas** para acceder a mejores puestos.

---

## Índice

1. [Objetivos del curso](#1-objetivos-del-curso)
2. [Metodología](#2-metodología)
3. [Stack tecnológico y herramientas](#3-stack-tecnológico-y-herramientas)
4. [El proyecto del curso: tienda online](#4-el-proyecto-del-curso-tienda-online)
5. [Planificación general](#5-planificación-general)
6. [Módulos](#6-módulos)
7. [Preparación de entrevistas (transversal)](#7-preparación-de-entrevistas-transversal)
8. [Consejos para impartir las clases](#8-consejos-para-impartir-las-clases)
9. [Estado actual y próximos pasos](#9-estado-actual-y-próximos-pasos)

---

## 1. Objetivos del curso

### Objetivo principal
Preparar a los alumnos para **entrevistas técnicas de backend Java** de nivel mid o senior, con ejercicios lo más parecidos posible a los que se encontrarán en procesos reales:

- **Live coding:** construir una API o resolver un problema en tiempo limitado.
- **Pruebas para llevar a casa:** entregar un proyecto bien estructurado y con tests.
- **Entrevista de system design:** diseñar un sistema escalable en la pizarra.
- **Preguntas teóricas:** explicar conceptos con claridad (JPA, Spring, Kafka, patrones…).
- **Code review:** detectar problemas en código ajeno.
- **Entrevista conductual:** contar su experiencia, incluida la legacy, en positivo.

### Objetivos concretos al terminar el curso
Cada alumno será capaz de:

1. Escribir **Java moderno** (records, streams, pattern matching) en lugar de Java 8 o anterior.
2. Construir **APIs REST con Spring Boot** con validación, manejo de errores y buenas prácticas.
3. Trabajar con **PostgreSQL y MongoDB**, y saber cuándo elegir cada uno.
4. Implementar **autenticación con Spring Security y JWT**.
5. Aplicar **SOLID, patrones de diseño y arquitectura hexagonal** con criterio.
6. Escribir **tests unitarios, de integración y end-to-end**.
7. Empaquetar con **Docker**, automatizar con **GitHub Actions** y monitorizar con **Prometheus y Grafana**.
8. Razonar en **system design**: balanceadores, gateways, réplicas, sharding y caché.
9. Diseñar comunicación por **eventos con Kafka**.
10. Construir **microservicios hexagonales** que se comunican de forma síncrona y asíncrona.
11. Tener un **repositorio en GitHub** con un proyecto completo para enseñar en las entrevistas.

---

## 2. Metodología

- **Antes → ahora.** Los alumnos ya saben programar: no se enseña desde cero, se actualiza lo que ya saben. Cada concepto se presenta comparando "así lo hacías en legacy" con "así se hace ahora".
- **Un único proyecto durante todo el curso.** Cada módulo añade una pieza a la tienda online. Al final hay un proyecto real que enseñar.
- **Programación en directo** en vez de diapositivas con código.
- **Ejercicios por parejas:** uno escribe y el otro piensa en voz alta, igual que en un live coding.
- **Analogías sencillas** para cada concepto abstracto.
- **Cada sesión termina con:**
  - 10 minutos de preguntas de entrevista, con el profesor haciendo de entrevistador.
  - Un commit en el repositorio del alumno.
- **Simulacros con reloj** en momentos clave del curso.

---

## 3. Stack tecnológico y herramientas

| Área | Tecnología |
|---|---|
| Lenguaje | Java 21 LTS (Eclipse Temurin) |
| Framework | Spring Boot 4, Spring Security, Spring Data, Spring Kafka, Spring Cloud Gateway |
| Bases de datos | PostgreSQL, MongoDB, Redis (caché) |
| Mensajería | Apache Kafka (modo KRaft) |
| Testing | JUnit 5, Mockito, AssertJ, Testcontainers, WireMock, RestAssured |
| Migraciones | Flyway |
| Contenedores | Docker, Docker Compose |
| CI/CD | GitHub Actions |
| Observabilidad | Spring Boot Actuator, Micrometer, Prometheus, Grafana, OpenTelemetry |
| Resiliencia | Resilience4j |
| Documentación API | OpenAPI / Swagger |

### Coste: todo gratis
- Todo el stack es open source o gratuito para uso personal y formativo.
- **Docker Desktop** es gratis para uso personal, educación y pequeñas empresas. Si alguien lo usa en un portátil de empresa grande, puede usar Podman Desktop o Rancher Desktop.
- **GitHub Actions** es gratis en repositorios públicos. Los repositorios del curso serán públicos para enseñarlos en entrevistas.
- **IDE:** IntelliJ IDEA en su versión gratuita o VS Code con las extensiones de Java y Spring.
- **Despliegue:** el pipeline termina en una imagen Docker publicada y se despliega con Docker Compose en local. Un despliegue en la nube es opcional y tendría coste.
- **Hardware recomendado:** 16 GB de RAM para levantar el ecosistema completo (microservicios, Kafka y bases de datos).

---

## 4. El proyecto del curso: tienda online

Una tienda online sencilla con **productos, usuarios, pedidos, pagos y notificaciones**. Empieza como una aplicación única y termina dividida en microservicios.

### Evolución del proyecto por módulo

| Módulo | Qué se añade al proyecto |
|---|---|
| 1 | API REST de productos y pedidos con datos en memoria |
| 2 | Persistencia en PostgreSQL (pedidos) y MongoDB (catálogo) |
| 3 | Registro, login y endpoints protegidos con JWT |
| 4 | Refactorización a arquitectura hexagonal |
| 5 | Batería de tests completa |
| 6 | Dockerfile, Docker Compose, pipeline CI/CD y métricas en Grafana |
| 7 | Documento de diseño para escalar la tienda |
| 8 | Eventos `PedidoCreado` con Kafka y consumidores de stock y notificaciones |
| 9 | División en microservicios con gateway |

### Microservicios finales (módulo 9)

| Servicio | Responsabilidad | Base de datos |
|---|---|---|
| `gateway` | Punto de entrada, validación de JWT y enrutamiento | — |
| `usuarios` | Registro, login y emisión de tokens | PostgreSQL |
| `catalogo` | Productos y stock | MongoDB |
| `pedidos` | Creación y gestión de pedidos | PostgreSQL |
| `pagos` | Procesamiento (simulado) de pagos | PostgreSQL |
| `notificaciones` | Reacciona a eventos y "envía" avisos | — |

---

## 5. Planificación general

Duración total: **unos 3 meses**, con 2 sesiones de 2 horas por semana.

| Bloque | Módulos | Duración |
|---|---|---|
| Base | 1. Java moderno y Spring Boot · 2. Persistencia · 3. Seguridad | 3–4 semanas |
| Diseño y calidad | 4. Patrones y hexagonal · 5. Testing | 2–3 semanas |
| DevOps | 6. Docker, CI/CD y observabilidad | 1–2 semanas |
| Arquitectura | 7. System design · 8. Eventos y Kafka · 9. Microservicios | 4–5 semanas |
| Entrevistas | 10. Preparación de entrevistas | Transversal, con simulacros desde el módulo 3 |
| Opcional | 11. Concurrencia | 1 semana |

**Prioridad si hay poco tiempo:** módulos 1, 2, 3, 5, 4 y 7, en ese orden.

---

## 6. Módulos

Cada módulo incluye: objetivos, fases (sesiones), ejercicios, preguntas de entrevista y el entregable para el proyecto.

---

### Módulo 1 — Java moderno y Spring Boot

**Duración:** 4 sesiones (2 semanas)

#### Objetivos
- Pasar de Java 8 o anterior a Java moderno.
- Dominar streams, `Optional` y el funcionamiento interno de las colecciones.
- Entender qué hace Spring por debajo, no solo usar anotaciones.
- Construir una API REST como la que se pide en una prueba técnica.

#### Preparación previa (tarea para casa)
- Instalar IntelliJ IDEA y Git, y descargar el JDK 21 (Eclipse Temurin) desde el propio IntelliJ. Guía paso a paso: [`modulo-01/preparacion-alumnos.md`](modulo-01/preparacion-alumnos.md).
- Crear una cuenta de GitHub y un repositorio vacío `tienda-online`.

#### Fase 1 — Java moderno: escribir menos para decir lo mismo
**Contenido**
1. Experimentar sin ceremonia con **JShell** (incluido en el JDK): probar código Java sin crear clases ni `main`.
   ```
   jshell> record Producto(String nombre, double precio) {}
   jshell> new Producto("Teclado", 49.99)
   $2 ==> Producto[nombre=Teclado, precio=49.99]
   ```
   *Nota:* las clases implícitas con `void main()` existen en Java 21 solo como *preview* y son estándar desde Java 25; se menciona como novedad, pero no se usa.
2. **Records.** Mostrar una clase `Producto` de 50 líneas (getters, constructor, `equals`, `hashCode`, `toString`) y su equivalente:
   ```java
   record Producto(String nombre, double precio) {}
   ```
   *Analogía:* una ficha con datos que no cambian; si necesitas otro dato, haces una ficha nueva.
3. `var`: el compilador deduce el tipo. Útil, sin abusar.
4. Text blocks para JSON y SQL.
5. Switch expressions y pattern matching (`instanceof` sin cast manual, record patterns) con jerarquías `sealed`.

**Ejercicio (40 min, por parejas):** modernizar una clase legacy típica: un DTO enorme, un `switch` con `break` y casts manuales. Material: [`modulo-01/fase-1-java-moderno/`](modulo-01/fase-1-java-moderno/).

**Preguntas de entrevista**
- ¿Qué diferencia hay entre un record y una clase normal?
- ¿Un record puede tener métodos? ¿Y validación en el constructor?

#### Fase 2 — Streams, Optional y colecciones
**Contenido**
1. Lambdas y method references.
2. **Streams.** *Analogía:* una cadena de montaje. Los datos pasan por estaciones (`filter`, `map`) y al final se empaquetan (`collect`).
   ```java
   var totalCaros = productos.stream()
       .filter(p -> p.precio() > 100)
       .mapToDouble(Producto::precio)
       .sum();
   ```
   Operaciones: `filter`, `map`, `flatMap`, `sorted`, `collect`, `groupingBy`.
3. **Optional.** *Analogía:* una caja que puede estar vacía y que obliga a mirar dentro. Usos correctos (`orElse`, `orElseThrow`, `map`) y errores típicos (`get()` sin comprobar).
4. Colecciones: cuándo usar `List`, `Set` o `Map`, colecciones inmutables (`List.of`) y Sequenced Collections de Java 21 (`getFirst`, `getLast`, `reversed`).
5. **`HashMap` por dentro y el contrato `equals`/`hashCode`.** *Analogía:* un armario con cajones numerados; `hashCode` dice en qué cajón buscar y `equals` qué objeto es dentro del cajón.

**Ejercicio (45 min):** con una lista de pedidos de la tienda, calcular (material: [`modulo-01/fase-2-streams/`](modulo-01/fase-2-streams/)):
- El total facturado.
- Los pedidos agrupados por cliente.
- El producto más vendido.
- Los clientes con más de 3 pedidos.

**Preguntas de entrevista**
- ¿Cómo funciona un `HashMap` internamente?
- ¿Qué pasa si sobrescribes `equals` pero no `hashCode`?
- ¿Diferencia entre `map` y `flatMap`?

#### Fase 3 — Spring Boot: qué hace la "magia"
**Contenido**
1. El problema que resuelve Spring: código sin Spring donde cada clase hace `new` de sus dependencias. "¿Y si quiero cambiar la base de datos? ¿Y testearlo?"
2. **Inversión de control e inyección de dependencias.** *Analogía:* en vez de que cada clase vaya a la tienda a comprar lo que necesita, Spring es un repartidor que se lo lleva a la puerta.
   ```java
   @Service
   class PedidoService {
       private final ProductoRepository repo;

       PedidoService(ProductoRepository repo) {
           this.repo = repo;
       }
   }
   ```
   Por qué inyección por constructor y no `@Autowired` en un campo: dependencias obligatorias, campos `final` y tests sin Spring.
3. Crear el proyecto con Spring Initializr y recorrer su estructura.
4. Qué hace `@SpringBootApplication`: escaneo de componentes y autoconfiguración.
5. Configuración: `application.yml`, perfiles (`dev`, `prod`) y `@ConfigurationProperties`.
6. Primer endpoint: `GET /productos`.

**Ejercicio (30 min):** crear el proyecto `tienda-online` con un servicio de productos en memoria inyectado en el controlador. Material: [`modulo-01/fase-3-spring-boot/`](modulo-01/fase-3-spring-boot/).

**Preguntas de entrevista**
- ¿Qué es la inyección de dependencias?
- ¿Por qué inyección por constructor?
- ¿Qué es un bean y qué scopes tiene?

#### Fase 4 — API REST bien hecha y primer simulacro
**Contenido**
1. Buenas prácticas REST: recursos en plural, verbos HTTP y códigos de respuesta (201, 400, 404…).
2. DTOs con records: separar la API del modelo interno.
3. Validación con `@NotBlank`, `@Positive` y `@Valid`.
4. Manejo global de errores con `@RestControllerAdvice` y `ProblemDetail`.
5. Probar la API con Postman, Bruno o el cliente HTTP del IDE.

Material: [`modulo-01/fase-4-api-rest/`](modulo-01/fase-4-api-rest/) (demo, enunciado, rúbrica y solución).

**Simulacro (45 min, con reloj):** "Crea una API REST para gestionar pedidos: crear, listar, consultar por id y cancelar. Valida los datos de entrada y devuelve errores claros". Datos en memoria.

**Revisión en grupo (20 min):** qué valoraría un entrevistador y qué penalizaría.

**Preguntas de entrevista**
- ¿Diferencia entre PUT y PATCH?
- ¿Qué significa que una operación sea idempotente?
- ¿Por qué no devolver directamente la entidad en la API?

#### Entregable
API REST de productos y pedidos en memoria, con validación y manejo de errores.

---

### Módulo 2 — Persistencia: PostgreSQL y MongoDB

**Duración:** 3 sesiones

#### Objetivos
- Tener un SQL sólido y saber leer un plan de ejecución.
- Entender transacciones y bloqueos.
- Usar JPA sin caer en sus trampas, sobre todo el problema N+1.
- Modelar en MongoDB y elegir con criterio entre SQL y NoSQL.

#### Fase 1 — SQL y PostgreSQL
- JOINs, agregaciones e índices.
- Leer un `EXPLAIN ANALYZE`.
- Transacciones (ACID) y bloqueo optimista con `@Version`.
- Migraciones con Flyway.

#### Fase 2 — JPA e Hibernate
- Entidades y relaciones.
- LAZY frente a EAGER y el **problema N+1**: cómo detectarlo y resolverlo (fetch join, entity graph).
- `@Transactional`: cómo funciona y por qué no funciona en una llamada interna a la misma clase.

#### Fase 3 — MongoDB y criterio
- Modelado documental: embeber frente a referenciar.
- Spring Data MongoDB y aggregation pipeline básico.
- Cuándo elegir SQL y cuándo NoSQL.

#### Ejercicios
- Detectar y corregir un N+1 en código dado.
- "Esta consulta tarda 5 segundos, ¿qué harías?"
- Persistir pedidos en PostgreSQL y el catálogo en MongoDB.

#### Preguntas de entrevista
- ¿Qué es el problema N+1 y cómo lo resuelves?
- ¿Por qué no funciona mi `@Transactional`?
- ¿Optimista o pesimista?
- ¿Por qué usarías Mongo aquí y no Postgres?

#### Entregable
Tienda con pedidos en PostgreSQL (con migraciones Flyway) y catálogo en MongoDB.

---

### Módulo 3 — Seguridad con Spring Security y JWT

**Duración:** 2 sesiones

#### Objetivos
- Entender autenticación y autorización en Spring Security.
- Implementar un flujo completo con JWT.

#### Fase 1 — Fundamentos de Spring Security
- Autenticación frente a autorización.
- La cadena de filtros y `SecurityFilterChain`.
- Hashing de contraseñas con BCrypt.
- CORS.

#### Fase 2 — JWT
- Estructura de un JWT: header, payload y firma.
- Access token y refresh token.
- El problema de la revocación y sus soluciones (tokens cortos, blacklist).
- Protección de endpoints por rol con `@PreAuthorize`.

#### Ejercicio
Implementar registro, login y refresh token. Solo un `ADMIN` puede crear productos; un `CLIENTE` solo ve sus propios pedidos.

#### Preguntas de entrevista
- ¿Diferencia entre autenticación y autorización?
- ¿Cómo invalidas un JWT antes de que caduque?
- ¿Dónde guardas el token en el cliente?

#### Entregable
Tienda con registro, login y endpoints protegidos por rol.

---

### Módulo 4 — Patrones de diseño y arquitectura hexagonal

**Duración:** 3 sesiones

#### Objetivos
- Aplicar SOLID con ejemplos reales.
- Conocer los patrones más preguntados y dónde los usa Spring.
- Estructurar un proyecto con arquitectura hexagonal.

#### Fase 1 — SOLID y patrones
- SOLID con ejemplos de código malo y bueno.
- Patrones esenciales: Strategy, Builder, Factory, Adapter y Observer.
- Dónde usa Spring estos patrones internamente.

#### Fase 2 — Arquitectura hexagonal
- Problemas de la arquitectura en capas tradicional.
- Dominio, casos de uso, puertos y adaptadores.
- Estructura de paquetes en Spring Boot.
- Mapeo entre el modelo de dominio y la entidad de persistencia.

#### Fase 3 — DDD táctico y criterio
- Entidades, value objects y agregados.
- Cuándo la hexagonal es sobreingeniería.

#### Ejercicios
- Refactorizar un método con muchos `if` según el tipo de pago usando Strategy.
- Migrar la tienda a hexagonal.
- Demostrar el valor de la arquitectura: cambiar un adaptador de persistencia sin tocar el dominio.

#### Preguntas de entrevista
- Explica la O y la D de SOLID con un ejemplo.
- ¿Qué patrones usa Spring internamente?
- ¿Qué ventajas tiene la hexagonal? ¿Cuándo no la usarías?

#### Entregable
Tienda refactorizada a arquitectura hexagonal.

---

### Módulo 5 — Testing

**Duración:** 2 sesiones

#### Objetivos
- Saber qué testear en cada nivel.
- Escribir tests legibles y útiles.
- Entregar pruebas técnicas con tests de calidad: es lo que más se valora en las pruebas para llevar a casa.

#### Fase 1 — Tests unitarios
- Pirámide de tests.
- JUnit 5, Mockito y AssertJ; patrón Arrange-Act-Assert.
- Mocks, stubs y fakes.
- Testear el dominio hexagonal sin Spring.

#### Fase 2 — Integración y end-to-end
- Slices: `@WebMvcTest`, `@DataJpaTest` y `@DataMongoTest`.
- Testcontainers con PostgreSQL y MongoDB reales.
- Tests de seguridad.
- Tests end-to-end con `@SpringBootTest` y RestAssured.
- Cobertura con JaCoCo, y por qué el 100 % no es el objetivo.

#### Ejercicio
Cubrir la tienda con tests en los tres niveles.

#### Preguntas de entrevista
- ¿Qué diferencia hay entre un mock y un stub?
- ¿Qué testearías con un test unitario y qué con uno de integración?
- ¿Por qué Testcontainers en lugar de H2?

#### Entregable
Batería de tests completa con informe de cobertura.

---

### Módulo 6 — DevOps: Docker, CI/CD y observabilidad

**Duración:** 3 sesiones (se puede condensar en una sola sesión práctica si hace falta)

#### Objetivos
- Empaquetar la aplicación en contenedores.
- Automatizar build, tests y despliegue.
- Monitorizar la aplicación con métricas y dashboards.

#### Fase 1 — Docker
- Imagen, contenedor y capas.
- Dockerfile multi-stage para Java.
- Docker Compose con la aplicación, PostgreSQL y MongoDB.

#### Fase 2 — CI/CD con GitHub Actions
- Workflows, jobs y steps.
- Pipeline: build → tests → imagen Docker → publicación.
- Secrets y caché de dependencias.

#### Fase 3 — Observabilidad
- Actuator y Micrometer.
- Prometheus recogiendo métricas y Grafana mostrándolas.
- Métricas de negocio (pedidos creados por minuto).
- Alertas básicas.

#### Ejercicio
Levantar todo con un `docker compose up` y crear un dashboard con métricas técnicas y de negocio.

#### Preguntas de entrevista
- ¿Qué ventajas tiene un build multi-stage?
- Producción va lenta, ¿cómo lo investigas?

#### Entregable
Proyecto dockerizado con pipeline en GitHub Actions y dashboard en Grafana.

---

### Módulo 7 — System design

**Duración:** 2 sesiones

#### Objetivos
- Seguir un método claro en entrevistas de system design.
- Conocer los componentes fundamentales de un sistema escalable.

#### Fase 1 — Método y componentes
- Método: requisitos funcionales y no funcionales → estimaciones → diseño de alto nivel → profundización → cuellos de botella.
- Escalado vertical frente a horizontal; servicios sin estado.
- Balanceadores de carga y sus algoritmos.
- API Gateway.

#### Fase 2 — Datos a escala
- Réplicas: primario y réplicas de lectura, replicación síncrona y asíncrona.
- Sharding: por rango o por hash, elección de la clave y hot spots.
- Teorema CAP.
- Caché con Redis: cache-aside, TTL e invalidación.

#### Ejercicios (en pizarra)
- Diseñar un acortador de URLs.
- Diseñar un sistema de reservas.
- ¿Cómo escalarías la tienda si recibe 100 veces más tráfico en Black Friday?

#### Entregable
Documento de diseño con el plan para escalar la tienda.

---

### Módulo 8 — Eventos y Kafka

**Duración:** 3 sesiones

#### Objetivos
- Entender la arquitectura orientada a eventos.
- Usar Kafka con Spring Boot de forma fiable.
- Llegar al módulo de microservicios sabiendo ya usar Kafka.

#### Fase 1 — Pensar en eventos
- Evento (algo que **ya ha pasado**, `PedidoCreado`) frente a comando (algo que **se pide**, `CrearPedido`).
- Por qué usarlos: quien publica no necesita saber quién escucha.
- Primer paso sin Kafka: eventos dentro de la aplicación con `ApplicationEventPublisher` y `@EventListener`.

#### Fase 2 — Kafka con Spring Boot
- Topics, particiones y offsets.
- Productores, consumidores y consumer groups.
- La clave del mensaje: misma clave, misma partición, orden garantizado.
- Retención y réplicas.
- Kafka en Docker Compose (modo KRaft, sin ZooKeeper).
- `KafkaTemplate`, `@KafkaListener` y serialización JSON.

#### Fase 3 — Fiabilidad y testing
- Garantías de entrega: at-most-once, at-least-once y exactly-once.
- Consumidor idempotente.
- Reintentos y dead letter topic.
- Patrón Outbox.
- Tests de integración con Kafka en Testcontainers.

#### Ejercicio
Al crear un pedido, publicar `PedidoCreado`. Dos consumidores independientes reaccionan: uno descuenta stock y otro "envía" una notificación (un log basta). Después, añadir un tercer consumidor sin tocar el código que publica.

#### Preguntas de entrevista
- ¿Cómo garantizas el orden de los mensajes?
- ¿Qué pasa si el consumidor falla a mitad?
- ¿Cómo escalas los consumidores y qué límite tienen? (Como mucho, un consumidor activo por partición dentro de un grupo.)
- ¿Kafka o RabbitMQ?

#### Entregable
Tienda publicando y consumiendo eventos con Kafka, con Outbox y dead letter topic.

---

### Módulo 9 — Microservicios con arquitectura hexagonal

**Duración:** 3 sesiones

#### Objetivos
- Partir el proyecto en microservicios.
- Ver que la comunicación entre servicios son simplemente **adaptadores** de la arquitectura hexagonal.
- Mantener la consistencia entre servicios.

#### Fase 1 — División y comunicación síncrona
- Partir la tienda en servicios (ver [sección 4](#microservicios-finales-módulo-9)), cada uno un hexágono con su propia base de datos.
- La llamada a otro servicio es un **puerto de salida** del dominio, implementado con `RestClient`.
- Timeouts, reintentos y circuit breaker con Resilience4j dentro del adaptador.

#### Fase 2 — Comunicación asíncrona en la hexagonal
- Publicar eventos es un **puerto de salida**.
- Un `@KafkaListener` es un **adaptador de entrada**, al mismo nivel que un controlador REST.
- Saga pedido → pago → notificación, con compensación si el pago falla.

#### Fase 3 — Ecosistema completo
- Spring Cloud Gateway con validación de JWT.
- Docker Compose con todos los servicios, Kafka y las bases de datos.
- Trazas distribuidas con OpenTelemetry y correlation ID en logs.
- Tests de adaptadores con WireMock y Testcontainers.

#### Ejercicio
Añadir un nuevo consumidor del evento `PedidoCreado` sin modificar el servicio que lo publica.

#### Preguntas de entrevista
- ¿Qué pasa si el servicio de pagos está caído?
- ¿Cómo mantienes la consistencia entre pedido y pago?
- ¿Monolito o microservicios? ¿Cuándo cada uno?

#### Entregable
Tienda dividida en microservicios, con gateway y ecosistema completo en Docker Compose.

---

### Módulo 11 — Concurrencia (opcional)

**Duración:** 1 semana

Para quien quiera profundizar o apunte a puestos donde el rendimiento es crítico.

- Hilos, `ExecutorService` y `CompletableFuture`.
- Condiciones de carrera, `synchronized`, locks y clases atómicas.
- Virtual threads: qué resuelven y cuándo usarlos.
- Activar virtual threads en Spring Boot.

---

## 7. Preparación de entrevistas (transversal)

Es el **módulo 10**, pero se trabaja durante todo el curso, con simulacros desde el módulo 3.

### Contenido
- **Analizar antes de programar:** hacer preguntas, aclarar requisitos y descomponer el problema. Se entrena explícitamente porque es de lo que más se valora.
- **Algoritmia básica:** arrays, hashmaps, strings, dos punteros y complejidad Big-O (nivel LeetCode easy/medium).
- **Pensar en voz alta** y explicar trade-offs mientras se programa.
- **Entrevista conductual:** método STAR y cómo contar la experiencia legacy en positivo (por ejemplo: "migré X", "mantuve Y en producción").
- **CV y GitHub:** presentar el proyecto del curso.

### Simulacros

| Momento | Tipo de simulacro |
|---|---|
| Final del módulo 1 | Live coding: API REST en 45 minutos |
| Final del módulo 3 | Preguntas teóricas de Spring, JPA y seguridad |
| Final del módulo 5 | Prueba para llevar a casa, evaluada sobre todo por los tests |
| Final del módulo 7 | System design en pizarra |
| Final del curso | Entrevista completa: teoría, live coding y system design |

---

## 8. Consejos para impartir las clases

- **Programar en directo.** Equivocarse delante de los alumnos es útil: ven cómo se resuelve un error real.
- **Trabajo por parejas** en los ejercicios.
- **Cerrar cada sesión** con 10 minutos de preguntas de entrevista.
- **Un commit por sesión** para construir un historial que enseñar.
- **Revisar las soluciones en grupo** desde el punto de vista del entrevistador.

---

## 9. Estado actual y próximos pasos

### Decisiones tomadas
- Temario condensado en 9 módulos principales, uno transversal de entrevistas y uno opcional de concurrencia (unos 3 meses).
- Proyecto común: tienda online, que acaba dividida en microservicios.
- Kafka tiene módulo propio antes de microservicios.
- La arquitectura hexagonal se combina con microservicios para mostrar la comunicación entre servicios como adaptadores.
- Todo el stack es gratuito.
- Versión de Java: **21 LTS** (coincide con el `pom.xml` del proyecto).

### Próximos pasos
- [ ] Preparar el material del módulo 1:
  - [x] Guía de preparación de los alumnos.
  - [x] Clase legacy para el ejercicio de refactorización (fase 1).
  - [x] Lista de pedidos de ejemplo y enunciados del ejercicio de streams, con soluciones (fase 2).
  - [x] Proyecto base de Spring Boot (fase 3).
  - [x] Enunciado y solución de referencia del simulacro (fase 4).
- [ ] Preparar el material del resto de módulos.
- [ ] Crear el repositorio de soluciones de referencia del profesor.
