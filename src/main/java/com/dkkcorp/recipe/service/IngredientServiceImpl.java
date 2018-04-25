package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.converter.IngredientCommandToIngredient;
import com.dkkcorp.recipe.converter.IngredientToIngredientCommand;
import com.dkkcorp.recipe.repository.IngredientRepository;

import java.util.List;

public class IngredientServiceImpl implements IngredientService {
    IngredientRepository ingredientRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public List<IngredientCommand> fetchAllRecipe() {
        return null;
    }

    @Override
    public IngredientCommand fetchRecipe(int idToFind) {
        return null;
    }

    @Override
    public IngredientCommand saveRecipe(IngredientCommand recipe) {
        return null;
    }

    @Override
    public void deleteRecipe(Integer id) {

    }
}
