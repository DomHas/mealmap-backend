package ch.wiss.mealmapbackend.dto;

import java.math.BigDecimal;

/**
 * Ausgabe-DTO für eine Zutat.
 * Enthält bewusst keinen Rückverweis auf das Recipe,
 * um Endlosschleifen bei der JSON-Serialisierung zu vermeiden.
 */
public record IngredientDTO(
        Long id,
        String name,
        BigDecimal amount,
        String unit
) {
}