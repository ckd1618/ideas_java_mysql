package com.ckd.productsandcategories.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ckd.productsandcategories.models.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {

}
