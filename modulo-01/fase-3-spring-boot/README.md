# Fase 3 — Ejercicio: el primer endpoint de la tienda

**Duración:** 30 minutos · **Formato:** por parejas

## Contexto

Hoy nace el proyecto que vas a hacer crecer durante todo el curso: la **tienda online**. Al terminar la sesión tendrás una aplicación Spring Boot que responde a `GET /productos` con el catálogo en JSON.

## Paso 1 — Crear el proyecto con Spring Initializr

Entra en <https://start.spring.io> y rellena:

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | La última versión 4.x estable (sin `SNAPSHOT` ni `M`) |
| Group | `com.tienda` |
| Artifact | `tienda-online` |
| Package name | `com.tienda` (corrígelo a mano: Initializr propone `com.tienda.tiendaonline`) |
| Packaging | Jar |
| Java | 21 |
| Dependencies | **Spring Web** |

Pulsa **Generate**, descomprime el `.zip` dentro de tu repositorio `tienda-online` y ábrelo en IntelliJ (**File → Open** → `pom.xml` → *Open as Project*).

Antes de escribir código, ejecuta `TiendaOnlineApplication` y comprueba que en la consola aparece algo como `Tomcat started on port 8080`.

## Paso 2 — El ejercicio

Crea el paquete `com.tienda.producto` y dentro:

1. **`Producto`**: un record con `sku`, `nombre`, `categoria` y `precio` (`BigDecimal`).
2. **`ProductoService`**: anotado con `@Service`. Guarda en memoria estos 5 productos y tiene un método `listar()` que los devuelve:

   | SKU | Nombre | Categoría | Precio |
   |---|---|---|---|
   | TEC-01 | Teclado mecánico | PERIFERICOS | 89.99 |
   | RAT-01 | Ratón inalámbrico | PERIFERICOS | 25.50 |
   | MON-01 | Monitor 27 pulgadas | PANTALLAS | 299.00 |
   | AUR-01 | Auriculares | AUDIO | 59.90 |
   | CAB-01 | Cable USB-C | ACCESORIOS | 9.99 |

3. **`ProductoController`**: anotado con `@RestController`. Recibe el `ProductoService` **por el constructor** y expone `GET /productos`.

Arranca la aplicación y abre <http://localhost:8080/productos> en el navegador. Debes ver los 5 productos en JSON.

## Reglas

- **Nada de `new ProductoService()`** dentro del controlador: que lo inyecte Spring.
- **Nada de `@Autowired` en campos.** Inyección por constructor.

## Extras, para quien termine antes

1. `GET /productos?categoria=PERIFERICOS` devuelve solo los productos de esa categoría.
2. `GET /productos/TEC-01` devuelve un producto. Si el SKU no existe, la respuesta debe ser **404**. Pista: `Optional` (fase 2) y `ResponseEntity`.

## Al terminar

Haz commit con el mensaje `feat: crear proyecto tienda-online con GET /productos`.

## Para pensar (lo preguntaremos al final)

- ¿Quién crea el objeto `ProductoService`? ¿Cuántas instancias hay?
- ¿Por qué inyección por constructor y no `@Autowired` en un campo?
- ¿Cómo se convierte tu record `Producto` en JSON si no has escrito nada para ello?
