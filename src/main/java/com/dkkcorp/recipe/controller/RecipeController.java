package com.dkkcorp.recipe.controller;

import com.dkkcorp.recipe.command.RecipeCommand;
import com.dkkcorp.recipe.converter.RecipeCommandToRecipe;
import com.dkkcorp.recipe.model.Recipe;
import com.dkkcorp.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/show")
    public String showRecipe(Model model, @PathVariable String id){
        int idToFind=Integer.parseInt(id);
        model.addAttribute("recipe",recipeService.fetchRecipe(idToFind));
        return "recipe/show";
    }
    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe/")
    public String saveRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand command=recipeService.saveRecipe(recipeCommand);
        return "redirect:/recipe/"+command.getId()+"/show/";
    }
    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        int idToFind=Integer.parseInt(id);
        RecipeCommand command=recipeService.fetchRecipe(idToFind);
        model.addAttribute("recipe",command);
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        int idToFind=Integer.parseInt(id);
        recipeService.deleteRecipe(idToFind);
        return "redirect:/";
    }
}
