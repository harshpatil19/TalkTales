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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.talktales.Config.AppConstants;
import com.talktales.DTO.ApiResponse;
import com.talktales.DTO.PostDTO;
import com.talktales.DTO.PostResponse;
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
	public ResponseEntity<PostResponse>getAllPost(@RequestParam (value="PageNumber", defaultValue=AppConstants.PAGE_NUMBER,required=false) int PageNumber,
			@RequestParam(value="PageSize", defaultValue=AppConstants.PAGE_SIZE,required=false)int PageSize,
			@RequestParam(value="sortby", defaultValue=AppConstants.SORT_BY,required=false)String sortBy)
			{
	PostResponse postResponse = this.postService.getAllPost(PageNumber,PageSize,sortBy);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable int postId){
		PostDTO post=this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable int postId){
		PostDTO updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable int postId)
	{
		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted Successfully",true);
	}
	
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable ("keywords") String keyword){
		List<PostDTO> result= this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDTO>>(result,HttpStatus.OK);
	}

}
