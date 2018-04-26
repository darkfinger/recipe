package com.dkkcorp.recipe.repository;

import com.dkkcorp.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    //Optional<Ingredient> findByRecipeIn(Long idRecipe, Long idIngredient);
}
