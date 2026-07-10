package ch.wiss.mealmapbackend.repository;

import ch.wiss.mealmapbackend.model.Ingredient;
import ch.wiss.mealmapbackend.model.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void saveRecipeAlsoSavesIngredients() {
        Recipe recipe = new Recipe("Testrezept", "Beschreibung", "Kategorie", "Anleitung");
        Ingredient ingredient = new Ingredient("Mehl", new BigDecimal("200"), "g");
        recipe.addIngredient(ingredient);

        Recipe saved = recipeRepository.save(recipe);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getIngredients()).hasSize(1);
        assertThat(saved.getIngredients().get(0).getRecipe()).isEqualTo(saved);
    }

    @Test
    void findByIdReturnsEmptyForUnknownId() {
        Optional<Recipe> result = recipeRepository.findById(999999L);

        assertThat(result).isEmpty();
    }
}