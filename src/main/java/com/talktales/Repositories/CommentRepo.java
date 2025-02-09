package com.talktales.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.talktales.Entities.Comment;


public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
