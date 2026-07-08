package ch.wiss.mealmapbackend.mapper;

import ch.wiss.mealmapbackend.dto.IngredientDTO;
import ch.wiss.mealmapbackend.dto.IngredientFormDTO;
import ch.wiss.mealmapbackend.dto.RecipeDTO;
import ch.wiss.mealmapbackend.dto.RecipeFormDTO;
import ch.wiss.mealmapbackend.model.Ingredient;
import ch.wiss.mealmapbackend.model.Recipe;

import java.util.List;

/**
 * Wandelt Recipe-Entities in RecipeDTOs um
 * (inkl. der verschachtelten Zutaten).
 */
public class RecipeMapper {

    private RecipeMapper() {
        // statische Hilfsklasse, keine Instanzen
    }

    public static RecipeDTO toDTO(Recipe recipe) {
        List<IngredientDTO> ingredientDTOs = recipe.getIngredients().stream().map(RecipeMapper::toIngredientDTO).toList();

        return new RecipeDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getCategory(),
                recipe.getInstructions(),
                ingredientDTOs
        );
    }

    public static IngredientDTO toIngredientDTO(Ingredient ingredient) {
        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getAmount(),
                ingredient.getUnit()
        );
    }

    public static Recipe fromDTO(RecipeFormDTO dto) {
        Recipe recipe = new Recipe(
                dto.title(),
                dto.description(),
                dto.category(),
                dto.instructions()
        );

        for (IngredientFormDTO ingredientDto : dto.ingredients())
        {
            Ingredient ingredient = new Ingredient(
                        ingredientDto.name(),
                        ingredientDto.amount(),
                        ingredientDto.unit()
            );

            recipe.addIngredient(ingredient);
        }

        return recipe;
    }
}