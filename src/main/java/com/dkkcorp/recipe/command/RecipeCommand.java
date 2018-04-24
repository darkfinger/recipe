package com.dkkcorp.recipe.command;

import com.dkkcorp.recipe.model.Difficulty;
import com.dkkcorp.recipe.model.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Integer id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    private Difficulty difficulty;
    private Byte[] image;
    private NoteCommand note;
    private Set<IngredientCommand> ingredientList=new HashSet<>();
    private Set<CategoryCommand> categoryList=new HashSet<>();
}
