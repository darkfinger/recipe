package com.dkkcorp.recipe.model;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String noteOfRecipe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getNoteOfRecipe() {
        return noteOfRecipe;
    }

    public void setNoteOfRecipe(String noteOfRecipe) {
        this.noteOfRecipe = noteOfRecipe;
    }
}
