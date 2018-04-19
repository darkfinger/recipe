package com.dkkcorp.recipe.controller;

import com.dkkcorp.recipe.model.Category;
import com.dkkcorp.recipe.model.UnitOfMesure;
import com.dkkcorp.recipe.repository.CategoryRepository;
import com.dkkcorp.recipe.repository.UnitOfMesureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    CategoryRepository categoryRepository;
    UnitOfMesureRepository unitOfMesureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMesureRepository unitOfMesureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMesureRepository = unitOfMesureRepository;
    }

    @RequestMapping({"/","","index"})
    public String index(Model model){
        Optional<Category> category=categoryRepository.findByDescription("American");
        Optional<UnitOfMesure> unitOfMesure=unitOfMesureRepository.findByUom("Cup");

        return "index";
    }
}
