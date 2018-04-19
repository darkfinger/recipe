package com.dkkcorp.recipe.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeIn")
    private Set<Ingredient> ingredientList=new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),inverseJoinColumns = @JoinColumn(name =   "category_id"))
    private Set<Category> categoryList=new HashSet<Category>();

    public void setNote(Note note) {
        this.note = note;
        note.setRecipe(this);
    }
    public void addIngredient(Ingredient i){
        i.setRecipeIn(this);
        this.getIngredientList().add(i);
    }
}
