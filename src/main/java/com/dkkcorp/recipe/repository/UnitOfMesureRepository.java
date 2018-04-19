package com.dkkcorp.recipe.repository;

import com.dkkcorp.recipe.model.UnitOfMesure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMesureRepository extends CrudRepository<UnitOfMesure,Long> {

    Optional<UnitOfMesure> findByUom(String uom);
}
