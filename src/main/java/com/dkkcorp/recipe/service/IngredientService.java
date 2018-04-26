package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    List<IngredientCommand> fetchAllIngradient();
    IngredientCommand fetchIngradient(int idToFind);
    IngredientCommand findByRecipeId(Long idRecipe, Long idIngredient);
    IngredientCommand saveIngradient(IngredientCommand recipe);
    void deleteIngradient(Integer id);
}
