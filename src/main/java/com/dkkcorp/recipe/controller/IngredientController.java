package com.dkkcorp.recipe.controller;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.command.UnitOfMeasureCommand;
import com.dkkcorp.recipe.model.Ingredient;
import com.dkkcorp.recipe.repository.IngredientRepository;
import com.dkkcorp.recipe.service.IngredientService;
import com.dkkcorp.recipe.service.RecipeService;
import com.dkkcorp.recipe.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
@Controller
public class IngredientController {
    RecipeService recipeService;
    UnitOfMeasureService unitOfMeasureService;
    IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, UnitOfMeasureService unitOfMeasureService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.ingredientService = ingredientService;
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
        command.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient",command);
        model.addAttribute("uomList",unitOfMeasureService.fetchAll());
        return "/recipe/ingredient/ingredientform";
    }
    @PostMapping("recipe/{idRecipe}/ingredient")
    public String saveIngredient(@ModelAttribute IngredientCommand command, @PathVariable String idRecipe){
        log.debug(command.getDescription()+ " dans le controller");
        ingredientService.saveIngradient(command);

        return "redirect:/recipe/"+command.getRecipeId()+"/list";
    }
    @GetMapping("/recipe/{idRecipe}/ingredient/{idIngredient}/update")
    public String updateIngredient(@PathVariable String idRecipe, @PathVariable String idIngredient, Model model){

        IngredientCommand command=ingredientService.fetchIngredient(Long.valueOf(idRecipe), Long.valueOf(idIngredient));
        command.setRecipeId(Long.valueOf(idRecipe));
        model.addAttribute("ingredient",command);
        model.addAttribute("uomList",unitOfMeasureService.fetchAll());
        return "/recipe/ingredient/ingredientform";
    }
    @GetMapping("/recipe/{idRecipe}/ingredient/{idIngredient}/delete")
    public  String deleteIngredient(@PathVariable String idIngredient){
        ingredientService.deleteIngradient(Integer.valueOf(idIngredient));
        return "redirect:/recipe/{idRecipe}/list";
    }
}
