package com.talktales.Service;

import com.talktales.DTO.CommentDTO;
import com.talktales.Entities.Comment;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO comment, Integer postId);
	void deleteComment(int commentId);
}
