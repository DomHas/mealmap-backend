package ch.wiss.mealmapbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Eingabe-DTO für ein Rezept beim Erstellen/Bearbeiten.
 */
public record RecipeFormDTO(
        @NotBlank(message = "Titel darf nicht leer sein")
        String title,

        @NotBlank(message = "Beschreibung darf nicht leer sein")
        String description,

        @NotBlank(message = "Kategorie darf nicht leer sein")
        String category,

        @NotBlank(message = "Zubereitungsschritte dürfen nicht leer sein")
        String instructions,

        @NotEmpty(message = "Mindestens eine Zutat ist erforderlich")
        @Valid
        List<IngredientFormDTO> ingredients
) {
}