package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.command.UnitOfMeasureCommand;
import com.dkkcorp.recipe.model.UnitOfMeasure;

import java.util.List;
import java.util.Optional;

public interface UnitOfMeasureService {

    List<UnitOfMeasureCommand> fetchAll();
}
