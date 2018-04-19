package com.dkkcorp.recipe.repository;

import com.dkkcorp.recipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
