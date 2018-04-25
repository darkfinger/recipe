package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;

import java.util.List;

public interface IngredientService {
    List<IngredientCommand> fetchAllIngradient();
    IngredientCommand fetchIngradient(int idToFind);
    IngredientCommand saveIngradient(IngredientCommand recipe);
    void deleteIngradient(Integer id);
}
