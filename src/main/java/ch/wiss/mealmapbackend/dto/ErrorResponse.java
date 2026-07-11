package ch.wiss.mealmapbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Einheitliches Format für Fehlerantworten der API,
 * z. B. bei nicht gefundenen Ressourcen oder fehlgeschlagener Validierung.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        Map<String, String> fieldErrors
){}