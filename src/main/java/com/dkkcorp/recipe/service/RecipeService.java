package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> fetchAllRecipe();
    Recipe fetchRecipe(int idToFind);
}
