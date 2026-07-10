package ch.wiss.mealmapbackend.service;

import ch.wiss.mealmapbackend.dto.RecipeDTO;
import ch.wiss.mealmapbackend.dto.RecipeFormDTO;
import ch.wiss.mealmapbackend.dto.IngredientFormDTO;
import ch.wiss.mealmapbackend.exception.RecipeNotFoundException;
import ch.wiss.mealmapbackend.model.Recipe;
import ch.wiss.mealmapbackend.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceMockTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void getRecipeByIdThrowsWhenNotFound() {
        given(recipeRepository.findById(999L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.getRecipeById(999L))
                .isInstanceOf(RecipeNotFoundException.class);
    }

    @Test
    void createRecipeSavesAndReturnsDTO() {
        RecipeFormDTO dto = new RecipeFormDTO(
                "Testrezept",
                "Beschreibung",
                "Kategorie",
                "Anleitung",
                List.of(new IngredientFormDTO("Mehl", new BigDecimal("200"), "g"))
        );

        given(recipeRepository.save(any(Recipe.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        RecipeDTO created = recipeService.createRecipe(dto);

        assertThat(created.title()).isEqualTo("Testrezept");

        ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository).save(captor.capture());
        assertThat(captor.getValue().getCategory()).isEqualTo("Kategorie");
    }
}