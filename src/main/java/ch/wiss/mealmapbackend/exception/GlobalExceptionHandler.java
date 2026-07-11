package ch.wiss.mealmapbackend.exception;

import ch.wiss.mealmapbackend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Fängt Exceptions aus allen Controllern zentral ab und wandelt
 * sie in einheitliche {@link ErrorResponse}-Antworten um.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Behandelt den Fall, dass ein Rezept mit der angegebenen ID nicht existiert.
     * @param ex die ausgelöste Exception
     * @return eine {@link ErrorResponse} mit Status 404
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(RecipeNotFoundException ex) {
        return new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage(), Map.of());
    }

    /**
     * Behandelt fehlgeschlagene Bean-Validierung (z. B. bei @Valid-Requests).
     * Sammelt alle Feldfehler in einer Map.
     * @param ex die ausgelöste Validierungs-Exception
     * @return eine {@link ErrorResponse} mit Status 400 und allen Feldfehlern
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        return new ErrorResponse(LocalDateTime.now(), 400, "Validierung fehlgeschlagen", fieldErrors);
    }
}
