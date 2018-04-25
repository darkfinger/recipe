package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.converter.IngredientCommandToIngredient;
import com.dkkcorp.recipe.converter.IngredientToIngredientCommand;
import com.dkkcorp.recipe.model.Ingredient;
import com.dkkcorp.recipe.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<IngredientCommand> fetchAllIngradient() {
        List<IngredientCommand> list=new ArrayList<>();
        ingredientRepository.findAll().iterator()
                .forEachRemaining(ingredient ->list.add(ingredientToIngredientCommand.convert(ingredient)) );
        return list;
    }

    @Override
    public IngredientCommand fetchIngradient(int idToFind) {
        Optional<Ingredient> optionalIngredient=ingredientRepository.findById(Long.valueOf(idToFind));
        if(!optionalIngredient.isPresent()){
            throw new RuntimeException("not ingredient found with this id");
        }
        IngredientCommand command=ingredientToIngredientCommand.convert(optionalIngredient.get());
        return command;
    }

    @Override
    public IngredientCommand saveIngradient(IngredientCommand ingredientCommand) {
        Ingredient ingredient=ingredientCommandToIngredient.convert(ingredientCommand);
        ingredient=ingredientRepository.save(ingredient);
        ingredientCommand=ingredientToIngredientCommand.convert(ingredient);
        return ingredientCommand;
    }

    @Override
    public void deleteIngradient(Integer id) {
        ingredientRepository.deleteById(Long.valueOf(id));
    }
}
