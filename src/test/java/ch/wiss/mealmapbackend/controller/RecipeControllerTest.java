package ch.wiss.mealmapbackend.controller;

import ch.wiss.mealmapbackend.dto.IngredientDTO;
import ch.wiss.mealmapbackend.dto.RecipeDTO;
import ch.wiss.mealmapbackend.exception.RecipeNotFoundException;
import ch.wiss.mealmapbackend.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private RecipeService service;

    @Test
    void getByIdReturns200WithBody() {
        RecipeDTO dto = new RecipeDTO(
                1L,
                "Testrezept",
                "Beschreibung",
                "Kategorie",
                "Anleitung",
                List.of(new IngredientDTO(1L, "Mehl", new BigDecimal("200"), "g"))
        );
        given(service.getRecipeById(1L)).willReturn(dto);

        assertThat(mvc.get().uri("/api/recipes/1"))
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.title")
                .isEqualTo("Testrezept");
    }

    @Test
    void getByIdReturns404WhenServiceThrows() {
        given(service.getRecipeById(999L))
                .willThrow(new RecipeNotFoundException(999L));

        assertThat(mvc.get().uri("/api/recipes/999"))
                .hasStatus(HttpStatus.NOT_FOUND);
    }
}