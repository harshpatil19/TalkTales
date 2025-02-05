package com.talktales.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talktales.DTO.CategoryDTO;
import com.talktales.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
@Autowired
private CategoryService categoryService;

@PostMapping
public ResponseEntity<CategoryDTO>createCategory(@Valid @RequestBody CategoryDTO categoryDto){
	CategoryDTO createCategoryDto=this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
}


@PutMapping("/{categoryid}")
public ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDto, @PathVariable("categoryid") int catid){
	CategoryDTO updateCategory=this.categoryService.updateCategory(categoryDto, catid);
	return  ResponseEntity.ok(updateCategory);
}


@DeleteMapping("/{categoryid}")
public ResponseEntity<?>deleteCategory(@Valid @PathVariable int categoryid){
categoryService.getCategoryById(categoryid);
return new ResponseEntity<>(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
}


@GetMapping("/{categoryid}")
public ResponseEntity<CategoryDTO>getSingleCategory(@Valid @PathVariable int categoryid){
	return ResponseEntity.ok(this.categoryService.getCategoryById(categoryid));
}

@GetMapping
public ResponseEntity<List<CategoryDTO>>getAllCategory(){
	return ResponseEntity.ok(this.categoryService.getAllCategory());
}
	
	

}
