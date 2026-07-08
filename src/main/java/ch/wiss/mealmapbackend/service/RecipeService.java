package ch.wiss.mealmapbackend.service;

import ch.wiss.mealmapbackend.dto.RecipeDTO;
import ch.wiss.mealmapbackend.dto.RecipeFormDTO;
import ch.wiss.mealmapbackend.exception.RecipeNotFoundException;
import ch.wiss.mealmapbackend.mapper.RecipeMapper;
import ch.wiss.mealmapbackend.model.Recipe;
import ch.wiss.mealmapbackend.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Liefert alle Rezepte inklusive ihrer Zutaten.
     *
     * @return Liste aller {@link RecipeDTO}
     */
    @Transactional(readOnly = true)
    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(RecipeMapper::toDTO)
                .toList();
    }

    /**
     * Liefert ein einzelnes Rezept anhand seiner ID.
     *
     * @param id die ID des gesuchten Rezepts
     * @return das gefundene {@link RecipeDTO}
     * @throws RecipeNotFoundException wenn kein Rezept mit dieser ID existiert
     */
    @Transactional(readOnly = true)
    public RecipeDTO getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
        return RecipeMapper.toDTO(recipe);
    }

    /**
     * Erstellt ein neues Rezept aus den übergebenen Formulardaten.
     *
     * @param form die validierten Eingabedaten des neuen Rezepts
     * @return das gespeicherte {@link RecipeDTO} inklusive generierter ID
     */
    @Transactional
    public RecipeDTO createRecipe(RecipeFormDTO form) {
        Recipe recipe = RecipeMapper.fromDTO(form);
        Recipe saved = recipeRepository.save(recipe);
        return RecipeMapper.toDTO(saved);
    }

    /**
     * Aktualisiert ein bestehendes Rezept.
     *
     * @param id   die ID des zu aktualisierenden Rezepts
     * @param form die neuen Formulardaten
     * @return das aktualisierte {@link RecipeDTO}
     * @throws RecipeNotFoundException wenn kein Rezept mit dieser ID existiert
     */
    @Transactional
    public RecipeDTO updateRecipe(Long id, RecipeFormDTO form) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException(id);
        }
        Recipe recipe = RecipeMapper.fromDTO(form);
        recipe.setId(id);
        Recipe saved = recipeRepository.save(recipe);
        return RecipeMapper.toDTO(saved);
    }

    /**
     * Löscht ein Rezept anhand seiner ID.
     *
     * @param id die ID des zu löschenden Rezepts
     * @throws RecipeNotFoundException wenn kein Rezept mit dieser ID existiert
     */
    @Transactional
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException(id);
        }
        recipeRepository.deleteById(id);
    }
}