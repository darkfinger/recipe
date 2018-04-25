package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.RecipeCommand;
import com.dkkcorp.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<RecipeCommand> fetchAllRecipe();
    RecipeCommand fetchRecipe(int idToFind);
    RecipeCommand saveRecipe(RecipeCommand recipe);
    void deleteRecipe(Integer id);
}
