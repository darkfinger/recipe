package com.dkkcorp.recipe.converter;

import com.dkkcorp.recipe.command.RecipeCommand;
import com.dkkcorp.recipe.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {

    private final CategoryToCategoryCommand categoryConveter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConveter, IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source){
        if(source==null)
        {return null;}
        final RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setServing(source.getServing());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setNote(notesConverter.convert(source.getNote()));
        if(source.getIngredientList()!=null && source.getIngredientList().size()>0){
            source.getIngredientList()
                    .forEach(i->recipeCommand.getIngredientList().add(ingredientConverter.convert(i)));
        }
        if(source.getCategoryList()!=null && source.getCategoryList().size()>0){
            source.getCategoryList().forEach(category -> recipeCommand.getCategoryList().add(categoryConveter.convert(category)));
        }
        return recipeCommand;
    }
}
