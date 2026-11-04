package com.tienda.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Un único sitio que traduce excepciones a respuestas HTTP con formato ProblemDetail (RFC 9457).
 * Al extender ResponseEntityExceptionHandler, los errores propios de Spring MVC
 * (JSON mal formado, tipo de parámetro incorrecto, ruta inexistente...) también salen como ProblemDetail.
 */
@RestControllerAdvice
public class ManejadorGlobalDeErrores extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ProblemDetail manejarNoEncontrado(RecursoNoEncontradoException ex) {
        var problema = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problema.setTitle("Recurso no encontrado");
        return problema;
    }

    @ExceptionHandler(ConflictoException.class)
    public ProblemDetail manejarConflicto(ConflictoException ex) {
        var problema = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problema.setTitle("Conflicto con el estado actual");
        return problema;
    }

    // 422: la petición es correcta en forma, pero no se puede procesar (por ejemplo, un SKU que no existe).
    @ExceptionHandler(DatosNoProcesablesException.class)
    public ProblemDetail manejarNoProcesable(DatosNoProcesablesException ex) {
        var problema = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());
        problema.setTitle("La petición no se puede procesar");
        return problema;
    }

    // Spring ya convierte los errores de @Valid en un 400 con ProblemDetail, pero sin decir QUÉ campos fallan.
    // Se sobrescribe para añadir la lista de errores.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var errores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDeCampo(error.getField(), error.getDefaultMessage()))
                .toList();

        var problema = ex.getBody();
        problema.setTitle("Datos de entrada no válidos");
        problema.setDetail("La petición tiene " + errores.size() + " error(es) de validación");
        problema.setProperty("errores", errores);

        return handleExceptionInternal(ex, problema, headers, status, request);
    }
}
