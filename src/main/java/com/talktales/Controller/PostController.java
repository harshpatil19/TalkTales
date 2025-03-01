package com.talktales.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talktales.Config.AppConstants;
import com.talktales.DTO.ApiResponse;
import com.talktales.DTO.PostDTO;
import com.talktales.DTO.PostResponse;
import com.talktales.Service.FileService;
import com.talktales.Service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userid}/category/{categoryid}/post")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto, @PathVariable int userid,
			@PathVariable int categoryid) {
		PostDTO createPost = this.postService.createPost(postDto, userid, categoryid);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userid}/post")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable int userid) {
		List<PostDTO> posts = this.postService.getPostsByUser(userid);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryid}/post")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable int categoryid) {
		List<PostDTO> posts = this.postService.getPostsByCategory(categoryid);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "PageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int PageNumber,
			@RequestParam(value = "PageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int PageSize,
			@RequestParam(value = "sortby", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
		PostResponse postResponse = this.postService.getAllPost(PageNumber, PageSize, sortBy);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable int postId) {
		PostDTO post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable int postId) {
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	@DeleteMapping("/{postId}")
	public ApiResponse deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted Successfully", true);
	}

	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keywords") String keyword) {
		List<PostDTO> result = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
	}

	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDTO> fileupload(@RequestParam("image") MultipartFile image, @PathVariable int postId)
			throws IOException {
		PostDTO postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadPostImage(path, image);
		postDto.setImageName(fileName);
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}
}
