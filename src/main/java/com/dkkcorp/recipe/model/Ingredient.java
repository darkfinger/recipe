package com.dkkcorp.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@EqualsAndHashCode(exclude = {"recipeIn"})
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

    public Ingredient(String description, BigDecimal amount, UnitOfMesure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

}
