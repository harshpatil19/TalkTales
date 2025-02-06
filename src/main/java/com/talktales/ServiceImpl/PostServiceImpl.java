package com.talktales.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.talktales.DTO.PostDTO;
import com.talktales.Entities.Category;
import com.talktales.Entities.Post;
import com.talktales.Entities.User;
import com.talktales.Exception.ResourceNotFound;
import com.talktales.Repositories.CategoryRepo;
import com.talktales.Repositories.PostRepo;
import com.talktales.Repositories.UserRepo;
import com.talktales.Service.PostService;
@Service
public class PostServiceImpl implements PostService {
@Autowired
	private PostRepo postRepo;
@Autowired
private ModelMapper modelMapper;
@Autowired
private UserRepo userRepo;
@Autowired
private CategoryRepo categoryRepo;
	
	@Override
	public PostDTO createPost(PostDTO postDto, int userid, int categoryid) {
		User user=this.userRepo.findById(userid).orElseThrow(() -> new ResourceNotFound("User", "Id", userid));
		Category category=this.categoryRepo.findById(categoryid).orElseThrow(() -> new ResourceNotFound("Category", "Id", categoryid));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddeddate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, int postid) {
	Post post=this.postRepo.findById(postid).orElseThrow(()->new ResourceNotFound("Post", "ID", postid));
	post.setTitle(postDto.getTitle());
	post.setContent(postDto.getContent());
	post.setImageName(postDto.getImageName());
Post updatePost= this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDTO.class) ;
	}

	@Override
	public void deletePost(int postid) {
	Post post=this.postRepo.findById(postid).orElseThrow(() -> new ResourceNotFound("Post", "ID",postid));
	this.postRepo.delete(post);

	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post> allPosts = this.postRepo.findAll();
		List<PostDTO> postDtos = allPosts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public PostDTO getPostById(int postid) {
		Post post= this.postRepo.findById(postid).orElseThrow(()-> new ResourceNotFound("Post","ID",postid));
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostsByCategory(int categoryid) {
		Category category= this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFound("Category","ID",categoryid));
		List<Post> posts=this.postRepo.findByCategory(category);
		List<PostDTO> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		/*List<PostDTO> postDtos=posts.stream().map(post->this.modelMapper.map(posts, PostDTO.class)).collect(Collectors.toList());*/
		return postDtos;

	}

	@Override
	public List<PostDTO> getPostsByUser(int userid) {
		User user= this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFound("User","ID",userid));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDTO> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		/*List<PostDTO> postDto=posts.stream().map(post->this.modelMapper.map(posts, PostDTO.class)).collect(Collectors.toList());*/
		return postDtos;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {

		return null;
	}

}
