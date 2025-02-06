package com.talktales.Controller;

import java.lang.module.ResolutionException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.talktales.DTO.ApiResponse;
import com.talktales.DTO.PostDTO;
import com.talktales.Service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	@PostMapping("/user/{userid}/category/{categoryid}/post")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto,@PathVariable int userid, @PathVariable int categoryid){
		PostDTO createPost=this.postService.createPost(postDto, userid, categoryid);
		return new ResponseEntity<PostDTO>(createPost,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user/{userid}/post")
	public ResponseEntity<List<PostDTO>>getPostsByUser(@PathVariable int userid){
		List <PostDTO> posts= this.postService.getPostsByUser(userid);
		return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryid}/post")
	public ResponseEntity<List<PostDTO>>getPostsByCategory(@PathVariable int categoryid){
		List <PostDTO> posts= this.postService.getPostsByCategory(categoryid);
		return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/post")
	public ResponseEntity<List<PostDTO>>getAllPost(){
		List<PostDTO>allPost=this.postService.getAllPost();
		return new ResponseEntity<List<PostDTO>>(allPost,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postid}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable int postid){
		PostDTO post=this.postService.getPostById(postid);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}
	
	@PutMapping("/post/{postid}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable int postid){
		PostDTO updatePost=this.postService.updatePost(postDto, postid);
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postid}")
	public ApiResponse deletePost(@PathVariable int postid)
	{
		this.postService.deletePost(postid);
		return new ApiResponse("Post deleted Successfully",true);
	}

}
