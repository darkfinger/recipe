package com.dkkcorp.recipe.command;

import com.dkkcorp.recipe.model.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
	@NotNull
    private String description;
}
