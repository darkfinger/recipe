package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;

import java.util.List;

public interface IngredientService {
    List<IngredientCommand> fetchAllRecipe();
    IngredientCommand fetchRecipe(int idToFind);
    IngredientCommand saveRecipe(IngredientCommand recipe);
    void deleteRecipe(Integer id);
}
