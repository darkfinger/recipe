package com.dkkcorp.recipe.command;

import com.dkkcorp.recipe.model.Recipe;
import com.dkkcorp.recipe.model.UnitOfMesure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMesure uom;
}
