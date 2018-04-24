package com.dkkcorp.recipe.command;

import com.dkkcorp.recipe.model.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
public class NoteCommand {
    private Integer id;
    private String noteOfRecipe;

}
