package com.dkkcorp.recipe.converter;

import com.dkkcorp.recipe.command.RecipeCommand;
import com.dkkcorp.recipe.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConveter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter, IngredientCommandToIngredient ingredientConverter,
                                 NotesCommandToNotes notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setServing(source.getServing());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNote(notesConverter.convert(source.getNote()));

        if (source.getCategoryList() != null && source.getCategoryList().size() > 0){
            source.getCategoryList()
                    .forEach( category -> recipe.getCategoryList().add(categoryConveter.convert(category)));
        }

        if (source.getIngredientList() != null && source.getIngredientList().size() > 0){
            source.getIngredientList()
                    .forEach(ingredient -> recipe.addIngredient(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}