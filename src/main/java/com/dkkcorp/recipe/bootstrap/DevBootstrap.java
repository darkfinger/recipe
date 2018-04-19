package com.dkkcorp.recipe.bootstrap;

import com.dkkcorp.recipe.model.*;
import com.dkkcorp.recipe.repository.CategoryRepository;
import com.dkkcorp.recipe.repository.RecipeRepository;
import com.dkkcorp.recipe.repository.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    CategoryRepository categoryRepository;
    RecipeRepository recipeRepository;
    UnitOfMesureRepository unitOfMesureRepository;

    public DevBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMesureRepository unitOfMesureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMesureRepository = unitOfMesureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.save(ini());
        log.debug("new recipe saved");
    }

    private Recipe ini() {
        Recipe r1=new Recipe();
        Note note=new Note();
        note.setNoteOfRecipe("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado " +
                "with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."+
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"+
        "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some " +
                "balance to the richness of the avocado and will help delay the avocados from turning brown.");
        r1.setDescription("The BEST guacamole! So easy to make with ripe avocado");
        r1.setPrepTime(10);
        r1.setCookTime(0);
        r1.setServing(4);
        r1.setSource("unknow");
        r1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        r1.setDifficulty(Difficulty.EASY);
        r1.setNote(note);
        r1.getCategoryList().add(categoryRepository.findByDescription("Avocado").get());
        r1.getCategoryList().add(categoryRepository.findByDescription("Mexican").get());

        r1.addIngredient(new Ingredient("avocados",new BigDecimal(2),unitOfMesureRepository.findByUom("Ripe").get()));
        r1.addIngredient(new Ingredient("Kosher salt",new BigDecimal(1/2),unitOfMesureRepository.findByUom("Teaspoon").get()));
        r1.addIngredient(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(1),unitOfMesureRepository.findByUom("Tablespoon").get()));
        return r1;

    }
}
