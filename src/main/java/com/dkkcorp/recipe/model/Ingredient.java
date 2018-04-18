package com.dkkcorp.recipe.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMesure uom;
    @ManyToOne()
    private Recipe recipeIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipeIn() {
        return recipeIn;
    }

    public void setRecipeIn(Recipe recipeIn) {
        this.recipeIn = recipeIn;
    }

    public UnitOfMesure getUom() {
        return uom;
    }

    public void setUom(UnitOfMesure uom) {
        this.uom = uom;
    }
}
