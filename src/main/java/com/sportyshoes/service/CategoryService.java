package com.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.Category;
import com.sportyshoes.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void deleteCategoryById(int category_id) {
		categoryRepository.deleteById(category_id);
	}
	
	public List<Category> viewAllCategory() {
		return categoryRepository.findAll();
	}
	
	public Category viewById(int category_id) {
		return (Category)categoryRepository.findById(category_id).get();
	}
}
