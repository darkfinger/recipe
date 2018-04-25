package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.RecipeCommand;
import com.dkkcorp.recipe.converter.RecipeCommandToRecipe;
import com.dkkcorp.recipe.converter.RecipeToRecipeCommand;
import com.dkkcorp.recipe.model.Recipe;
import com.dkkcorp.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{
    RecipeRepository recipeRepository;
    RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public List<RecipeCommand> fetchAllRecipe() {
        List<Recipe> list=new ArrayList<Recipe>();
        List<RecipeCommand> listcmd=new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(list::add);
        list.forEach(recipecmd -> listcmd.add(recipeToRecipeCommand.convert(recipecmd)) );


        return listcmd;
    }

    @Override
    public RecipeCommand fetchRecipe(int idToFind) {
        Recipe recipe;
        Optional<Recipe> recipeOptional=recipeRepository.findById(idToFind);
        if(!recipeOptional.isPresent())
            throw new RuntimeException("Not found");
        recipe=recipeOptional.get();
        RecipeCommand recipeCommand=recipeToRecipeCommand.convert(recipe);
        return recipeCommand;
    }

    @Override
    public RecipeCommand saveRecipe(RecipeCommand recipecmd) {
        Recipe recipe=recipeCommandToRecipe.convert(recipecmd);
        recipe=recipeRepository.save(recipe);
        RecipeCommand command=recipeToRecipeCommand.convert(recipe);
        return command;
    }

    @Override
    public void deleteRecipe(Integer id) {
        recipeRepository.delete(recipeRepository.findById(id).get());
    }

}
