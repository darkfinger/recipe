package com.dkkcorp.recipe.service;

import com.dkkcorp.recipe.command.IngredientCommand;
import com.dkkcorp.recipe.command.UnitOfMeasureCommand;
import com.dkkcorp.recipe.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.dkkcorp.recipe.model.UnitOfMeasure;
import com.dkkcorp.recipe.repository.UnitOfMesureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    UnitOfMesureRepository unitOfMesureRepository;
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMesureRepository unitOfMesureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMesureRepository = unitOfMesureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public List<UnitOfMeasureCommand> fetchAll() {
        List<UnitOfMeasure> list1=new ArrayList<>();
        List<UnitOfMeasureCommand> list= new ArrayList<>();
        unitOfMesureRepository.findAll().iterator().forEachRemaining(list1::add);
        list1.forEach(unitOfMeasure -> list.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure)));

        return list;
    }
}
