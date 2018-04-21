package com.dkkcorp.recipe.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public  void setUp(){
        category=new Category();
    }
    @Test
    public void getId() {
        Long idl=4L;
        category.setId(idl);
        assertEquals(idl,category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipeList() {
    }
}