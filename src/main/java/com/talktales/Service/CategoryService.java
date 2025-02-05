package com.talktales.Service;

import java.util.List;

import com.talktales.DTO.CategoryDTO;
import com.talktales.DTO.UserDTO;

public interface CategoryService {

	CategoryDTO createCategory(CategoryDTO category);
	CategoryDTO updateCategory(CategoryDTO category, Integer categoryid);
	CategoryDTO getCategoryById( Integer categoryid);
	List<CategoryDTO>getAllCategory();
	void deleteCategory(Integer categoryid);
}
