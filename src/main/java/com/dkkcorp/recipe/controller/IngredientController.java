package com.dkkcorp.recipe.controller;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.model.Ingredient;
import com.dkkcorp.recipe.repository.IngredientRepository;
import com.dkkcorp.recipe.service.IngredientService;
import com.dkkcorp.recipe.service.RecipeService;
import com.dkkcorp.recipe.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {
    RecipeService recipeService;
    UnitOfMeasureService unitOfMeasureService;
    IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, UnitOfMeasureService u) {
        this.recipeService = recipeService;
        this.unitOfMeasureService=u;
    }

    @GetMapping("/recipe/{idRecipe}/list")
    public String showListIngredient(@PathVariable String idRecipe, Model model){
        model.addAttribute("recipe",recipeService.fetchRecipe(Integer.valueOf(idRecipe)));
        return "/recipe/ingredient/list";
    }
    @GetMapping("recipe/{idRecipe}/ingredient/new")
    public String newIngredient(@PathVariable String idRecipe, Model model){
        IngredientCommand command=new IngredientCommand();
        command.setRecipeId(Long.valueOf(idRecipe));
        command.setId(Long.valueOf("100"));
        command.setUom(unitOfMeasureService.fetchAll().get(1));
        model.addAttribute("ingredient",command);
        model.addAttribute("uomList",unitOfMeasureService.fetchAll());
        return "/recipe/ingredient/ingredientform";
    }
    @PostMapping("recipe/{idRecipe}/ingredient")
    public String saveIngredient(@ModelAttribute String idRecipe){
        
        return "redirect:/recipe/{idRecipe}/list";
    }
}
