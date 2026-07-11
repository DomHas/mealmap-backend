package ch.wiss.mealmapbackend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Rezept mit mehreren Zutaten.
 * Inverse Seite der Beziehung (hält den Fremdschlüssel nicht).
 */
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    // mappedBy zeigt auf das Feld "recipe" in Ingredient.
    // cascade: Zutaten werden mit dem Rezept gespeichert/gelöscht.
    // orphanRemoval: aus der Liste entfernte Zutaten werden gelöscht.
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String title, String description, String category, String instructions) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.instructions = instructions;
    }

    /**
     * Fügt eine Zutat hinzu und hält beide Seiten
     * der Beziehung synchron.
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

    /**
     * Entfernt eine Zutat und hält beide Seiten
     * der Beziehung synchron.
     */
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.setRecipe(null);
    }

    // --- Getter / Setter ---

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getInstructions() {return instructions;}
    public void setInstructions(String instructions) {this.instructions = instructions;}

    public List<Ingredient> getIngredients() {return ingredients;}
    public void setIngredients(List<Ingredient> ingredients) {this.ingredients = ingredients;}


}
