package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.model.Recipe;
import com.dkkcorp.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{
    RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> fetchAllRecipe() {
        List<Recipe> list=new ArrayList<Recipe>();
        recipeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Recipe fetchRecipe(int idToFind) {
        Recipe recipe;
        Optional<Recipe> recipeOptional=recipeRepository.findById(idToFind);
        if(!recipeOptional.isPresent())
            throw new RuntimeException("Not found");
        recipe=recipeOptional.get();
        return recipe;
    }
}
