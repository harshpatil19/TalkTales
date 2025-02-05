package com.talktales.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talktales.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
