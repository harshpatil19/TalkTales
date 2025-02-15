package com.talktales.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talktales.DTO.CommentDTO;
import com.talktales.Entities.Comment;
import com.talktales.Entities.Post;
import com.talktales.Exception.ResourceNotFound;
import com.talktales.Repositories.CommentRepo;
import com.talktales.Repositories.PostRepo;
import com.talktales.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "ID", postId));
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	
	@Override
	public void deleteComment(int commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(( )->new ResourceNotFound("Comment", "ID", commentId));
		this.commentRepo.delete(com);
	}

}
