package com.dkkcorp.recipe.controller;

import com.dkkcorp.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowController {

    RecipeService recipeService;

    public ShowController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("recipe/show/{id}")
    public String showRecipe(Model model, @PathVariable String id){
        int idToFind=Integer.parseInt(id);
        model.addAttribute("recipe",recipeService.fetchRecipe(idToFind));
        return "recipe/show";
    }
}
