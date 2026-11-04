# Módulo 1 — Preparación previa (tarea para casa)

Haz esta preparación **antes de la primera sesión**. Son unos 30 minutos. Si algo falla, avisa antes de clase para no perder tiempo en directo.

---

## 1. Instalar IntelliJ IDEA

1. Descarga **IntelliJ IDEA** (versión gratuita) desde <https://www.jetbrains.com/idea/download/>.
2. Instálalo con las opciones por defecto.

> **No necesitas instalar Java por tu cuenta.** IntelliJ descarga el JDK por ti (paso 3).

---

## 2. Instalar y configurar Git

1. Descarga Git desde <https://git-scm.com/downloads> e instálalo.
2. Abre una terminal y configura tu identidad (usa el mismo email que en GitHub):
   ```bash
   git config --global user.name "Tu Nombre"
   git config --global user.email "tu-email@ejemplo.com"
   ```
3. Comprueba que funciona:
   ```bash
   git --version
   ```

---

## 3. Descargar Java 21 desde IntelliJ

1. Abre IntelliJ y crea un proyecto nuevo: **New Project** → **Java**.
2. Elige **Build system: Maven**.
3. En el desplegable **JDK**, elige **Download JDK…**:
   - **Version:** `21`
   - **Vendor:** `Eclipse Temurin`
4. Pulsa **Download** y espera a que termine.
5. Marca **Add sample code** y pulsa **Create**.

> Si ya tienes un proyecto abierto: **File → Project Structure → SDKs → + → Download JDK…**

---

## 4. Comprobar que todo funciona

En el proyecto que acabas de crear, sustituye el contenido de la clase `Main` por:

```java
public class Main {
    public static void main(String[] args) {
        record Saludo(String texto) {}
        var saludo = new Saludo("Hola desde Java " + Runtime.version().feature());
        System.out.println(saludo.texto());
    }
}
```

Ejecútalo con el botón ▶. Debe aparecer:

```
Hola desde Java 21
```

Si compila y aparece ese mensaje, tu entorno está listo. (Sí, eso de ahí es un `record`: lo veremos en la primera sesión).

---

## 5. Crear la cuenta y el repositorio en GitHub

1. Crea una cuenta en <https://github.com> si no tienes una.
   Elige un nombre de usuario profesional: lo vas a enseñar en entrevistas.
2. Crea un repositorio nuevo:
   - **Nombre:** `tienda-online`
   - **Visibilidad:** público
   - **Vacío:** sin README, sin `.gitignore` y sin licencia (los añadiremos desde el proyecto).

---

## 6. (Opcional) Usar Java desde la terminal

El JDK que descarga IntelliJ **solo lo usa IntelliJ**: la terminal no lo encuentra. Para el módulo 1 no hace falta, pero si quieres usar `java`, `jshell` o `mvnw` desde la terminal:

1. Localiza la carpeta del JDK: IntelliJ lo guarda en `~/.jdks/` (en Windows, `C:\Users\<tu-usuario>\.jdks\`), en una carpeta tipo `temurin-21.x.x`.
2. Crea la variable de entorno `JAVA_HOME` apuntando a esa carpeta y añade `JAVA_HOME/bin` al `PATH`.
   - **Windows:** Configuración → Sistema → Información → Configuración avanzada del sistema → Variables de entorno.
   - **macOS / Linux:** añade a tu `~/.zshrc` o `~/.bashrc`:
     ```bash
     export JAVA_HOME="$HOME/.jdks/temurin-21.x.x"
     export PATH="$JAVA_HOME/bin:$PATH"
     ```
3. Abre una terminal **nueva** y comprueba:
   ```bash
   java -version
   ```

---

## Checklist antes de la primera sesión

- [ ] IntelliJ IDEA instalado.
- [ ] Git instalado y configurado (`user.name` y `user.email`).
- [ ] JDK 21 (Eclipse Temurin) descargado desde IntelliJ.
- [ ] El programa de prueba imprime `Hola desde Java 21`.
- [ ] Cuenta de GitHub creada.
- [ ] Repositorio público y vacío `tienda-online` creado.
