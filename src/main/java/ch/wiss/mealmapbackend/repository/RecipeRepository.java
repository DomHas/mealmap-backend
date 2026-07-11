package ch.wiss.mealmapbackend.repository;

import ch.wiss.mealmapbackend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository für Datenbankzugriff auf Recipe.
 * Ein separates IngredientRepository ist nicht nötig
 * da Ingredients immer über ihr Recipe verwaltet werden.
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
