package com.ckd.productsandcategories.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckd.productsandcategories.models.Product;
import com.ckd.productsandcategories.repositories.CategoryRepo;
import com.ckd.productsandcategories.repositories.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}
	public void addProduct(Product product) {
		productRepo.save(product);
	}
	public List<Product> getAll(){
		return (List<Product>) productRepo.findAll();
	}
	public Product getOne(Long id) {
		return (Product) productRepo.findById(id);
	}
	public void update(Product product) {
		productRepo.save(product);
	}
	public List<Product> getAllExcept(Long categoryId) {
		List<Product> products = this.getAll();
		List<Product> categoryProducts = categoryRepo.findById(categoryId).getProducts();
		products.removeAll(categoryProducts);
		return products;
	}
}
