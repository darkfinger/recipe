package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.converter.IngredientCommandToIngredient;
import com.dkkcorp.recipe.converter.IngredientToIngredientCommand;
import com.dkkcorp.recipe.model.Ingredient;
import com.dkkcorp.recipe.model.Recipe;
import com.dkkcorp.recipe.repository.IngredientRepository;
import com.dkkcorp.recipe.repository.RecipeRepository;
import com.dkkcorp.recipe.repository.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    IngredientRepository ingredientRepository;
    RecipeRepository recipeRepository;
    UnitOfMesureRepository unitOfMesureRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 RecipeRepository recipeRepository,
                                 UnitOfMesureRepository unitOfMesureRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMesureRepository = unitOfMesureRepository;
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
    public IngredientCommand fetchIngredient(Long idRecipe, Long idIngredient) {
        Optional<Recipe> optionalRecipe=recipeRepository.findById(Integer.valueOf(Math.toIntExact(idRecipe)));
        if(!optionalRecipe.isPresent()){
            throw new RuntimeException("no recipe with this id");
        }
        Optional<IngredientCommand> optionalIngredient=optionalRecipe.get().getIngredientList().stream()
                .filter(ingredient -> ingredient.getId().equals(idIngredient))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();
        if(!optionalIngredient.isPresent()){
            throw new RuntimeException("no ingredient with this id");
        }
        return optionalIngredient.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngradient(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById((int)(long) command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredientList()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMesureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipeIn(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredientList().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredientList().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }

    @Override
    public void deleteIngradient(Integer id) {
        ingredientRepository.deleteById(Long.valueOf(id));
    }
}
