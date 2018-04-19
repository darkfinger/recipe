package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.model.Recipe;
import com.dkkcorp.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
