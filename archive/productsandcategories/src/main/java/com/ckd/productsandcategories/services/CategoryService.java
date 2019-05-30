package com.ckd.productsandcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckd.productsandcategories.models.Category;
import com.ckd.productsandcategories.repositories.CategoryRepo;
import com.ckd.productsandcategories.repositories.ProductRepo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductRepo productRepo;
	public CategoryService(CategoryRepo categoryRepo, ProductRepo productRepo) {
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
	}
	public void addCategory(Category category) {
		categoryRepo.save(category);
	}
	public List<Category> getAll() {
		return (List<Category>) categoryRepo.findAll();
	}
	public Category getOne(Long id) {
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		if(optionalCategory.isPresent()) {
		    return optionalCategory.get();
		} else {
		    return null;
		}
	}
	public void update(Category category) {
		categoryRepo.save(category);
	}
	public List<Category> getAllExcept(Long productId) {
		List<Category> categories = this.getAll();
		List<Category> productCategories = productRepo.findById(productId).getCategories();
		categories.removeAll(productCategories);
		return categories;
	}
}
