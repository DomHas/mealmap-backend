package ch.wiss.mealmapbackend.repository;

import ch.wiss.mealmapbackend.model.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RecipeSeederTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void seederHasDbFilled() {
        long anzahl = recipeRepository.count();
        assertTrue(anzahl > 0);
    }

    @Test
    void recipeCanBeLoaded() {
        Optional<Recipe> recipe = recipeRepository.findById(1L);
        assertTrue(recipe.isPresent());
    }
}
