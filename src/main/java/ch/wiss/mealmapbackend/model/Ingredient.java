package ch.wiss.mealmapbackend.model;

import jakarta.persistence.*;

import javax.print.DocFlavor;
import java.math.BigDecimal;

/**
 * Eine einzelne Zutat innerhalb eines Rezepts.
 * Hält den Fremdschlüssel zum Rezept (Owning Side).
 */
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    private String unit;

    // Die Many-Seite hält den Fremdschlüssel: Spalte recipe_id.
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // Leerer Konstruktor für JPA
    public Ingredient() {

    }

    public Ingredient(String name, BigDecimal amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    // --- Getter / Setter ---

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public BigDecimal getAmount() {return amount;}
    public void setAmount(BigDecimal amount) {this.amount = amount;}

    public String getUnit() {return unit;}
    public void setUnit(String unit) {this.unit = unit;}

    public Recipe getRecipe() {return recipe;}
    public void setRecipe(Recipe recipe) {this.recipe = recipe;}
}

