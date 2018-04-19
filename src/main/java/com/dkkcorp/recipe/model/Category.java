package com.dkkcorp.recipe.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categoryList")
    private Set<Recipe> recipeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(Set<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
