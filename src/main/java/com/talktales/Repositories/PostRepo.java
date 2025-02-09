package com.talktales.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talktales.Entities.Category;
import com.talktales.Entities.Post;
import com.talktales.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post>findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post>findByTitleContaining(String title);

}
