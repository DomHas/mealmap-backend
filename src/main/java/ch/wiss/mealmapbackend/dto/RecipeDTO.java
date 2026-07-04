package ch.wiss.mealmapbackend.dto;

import java.util.List;

/**
 * Ausgabe-DTO für ein Rezept, inklusive seiner Zutaten.
 */
public record RecipeDTO(
        Long id,
        String title,
        String description,
        String category,
        String instructions,
        List<IngredientDTO> ingredients
) {
}