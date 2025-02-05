package com.talktales.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talktales.DTO.CategoryDTO;
import com.talktales.DTO.UserDTO;
import com.talktales.Entities.Category;
import com.talktales.Entities.User;
import com.talktales.Exception.ResourceNotFound;
import com.talktales.Repositories.CategoryRepo;
import com.talktales.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = this.dtoToCategory(categoryDTO);
		Category savedCategory = this.categoryRepo.save(category);
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryid) {
		Category category = this.categoryRepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFound("Category", "ID", categoryid));
		category.setCategoryTitile(categoryDTO.getCategoryTitile());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updateCategory = this.categoryRepo.save(category);
		CategoryDTO catDTO = this.categoryToDto(updateCategory);
		return catDTO;
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryid) {
		Category category = this.categoryRepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFound("Category", "ID", categoryid));
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categorys = this.categoryRepo.findAll();
		List<CategoryDTO> categoryDTOList = categorys.stream().map(category -> categoryToDto(category)).collect(Collectors.toList());
		return categoryDTOList;
	}

	@Override
	public void deleteCategory(Integer categoryid) {
		Category category = this.categoryRepo.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFound("Category", "ID", categoryid));
		this.categoryRepo.delete(category);

	}

	public Category dtoToCategory(CategoryDTO categoryDTO) {
		Category category = this.modelMapper.map(categoryDTO, Category.class);
		return category;
	}

	public CategoryDTO categoryToDto(Category category) {
		CategoryDTO categorydto = this.modelMapper.map(category, CategoryDTO.class);
		return categorydto;

	}

}
