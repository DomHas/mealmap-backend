package ch.wiss.mealmapbackend.controller;

import ch.wiss.mealmapbackend.dto.RecipeDTO;
import ch.wiss.mealmapbackend.dto.RecipeFormDTO;
import ch.wiss.mealmapbackend.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/recipes")
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/api/recipes/{id}")
    public RecipeDTO getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping("/api/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDTO createRecipe(@Valid @RequestBody RecipeFormDTO form) {
        return recipeService.createRecipe(form);
    }

    @PutMapping("/api/recipes/{id}")
    public RecipeDTO updateRecipe(@PathVariable Long id,
                                  @Valid @RequestBody RecipeFormDTO form) {
        return recipeService.updateRecipe(id, form);
    }

    @DeleteMapping("/api/recipes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}