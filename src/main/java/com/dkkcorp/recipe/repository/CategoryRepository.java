package com.dkkcorp.recipe.repository;

import com.dkkcorp.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByDescription(String desc);
}
