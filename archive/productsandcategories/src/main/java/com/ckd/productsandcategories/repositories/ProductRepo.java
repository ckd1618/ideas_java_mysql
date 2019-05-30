package com.ckd.productsandcategories.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ckd.productsandcategories.models.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

}
