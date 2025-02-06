package com.talktales.Service;

import java.util.List;

import com.talktales.DTO.PostDTO;
import com.talktales.Entities.Post;

public interface  PostService {
PostDTO createPost(PostDTO postDto,int userid, int categoryid); 
PostDTO updatePost(PostDTO postDto, int postid);
void deletePost(int postid);
List<PostDTO>getAllPost();

PostDTO getPostById(int postid);
//get all post by category
List<PostDTO> getPostsByCategory(int categoryid);
//get all post by user
List<PostDTO>getPostsByUser(int userid);

List<PostDTO>searchPosts(String keyword);
}
