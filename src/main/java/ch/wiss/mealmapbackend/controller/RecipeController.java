package ch.wiss.mealmapbackend.controller;

import ch.wiss.mealmapbackend.dto.RecipeDTO;
import ch.wiss.mealmapbackend.dto.RecipeFormDTO;
import ch.wiss.mealmapbackend.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller für die CRUD-Operationen auf Rezepten.
 * Nimmt HTTP-Anfragen entgegen und delegiert an den {@link RecipeService}.
 */
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Liefert alle Rezepte inklusive ihrer Zutaten.
     * @return Liste aller {@link RecipeDTO}
     */
    @GetMapping("/api/recipes")
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    /**
     * Liefert ein einzelnes Rezept anhand seiner ID.
     * @param id die ID des gesuchten Rezepts
     * @return das gefundene {@link RecipeDTO}
     */
    @GetMapping("/api/recipes/{id}")
    public RecipeDTO getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    /**
     * Erstellt ein neues Rezept aus den übergebenen Formulardaten.
     * @param form die validierten Eingabedaten des neuen Rezepts
     * @return das gespeicherte {@link RecipeDTO} inklusive generierter ID
     */
    @PostMapping("/api/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDTO createRecipe(@Valid @RequestBody RecipeFormDTO form) {
        return recipeService.createRecipe(form);
    }

    /**
     * Aktualisiert ein bestehendes Rezept.
     * @param id die ID des zu aktualisierenden Rezepts
     * @param form die neuen Formulardaten
     * @return das aktualisierte {@link RecipeDTO}
     */
    @PutMapping("/api/recipes/{id}")
    public RecipeDTO updateRecipe(@PathVariable Long id,
                                  @Valid @RequestBody RecipeFormDTO form) {
        return recipeService.updateRecipe(id, form);
    }

    /**
     * Löscht ein Rezept anhand seiner ID.
     * @param id die ID des zu löschenden Rezepts
     */

    @DeleteMapping("/api/recipes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}