package ch.wiss.mealmapbackend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Eingabe-DTO für eine Zutat beim Erstellen/Bearbeiten eines Rezepts.
 */
public record IngredientFormDTO(
        @NotBlank(message = "Name darf nicht leer sein")
        String name,

        @NotNull(message = "Menge muss angegeben werden")
        @DecimalMin(value = "0.0", inclusive = false, message = "Menge muss grösser als 0 sein")
        BigDecimal amount,

        @NotBlank(message = "Einheit darf nicht leer sein")
        String unit
) {
}