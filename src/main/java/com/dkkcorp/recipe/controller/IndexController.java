package com.dkkcorp.recipe.controller;


import com.dkkcorp.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/","","index"})
    public String index(Model model){
        model.addAttribute("recipes",recipeService.fetchAllRecipe());
        log.debug("data set and sent to index");
        return "index";
    }
}
