package com.talktales.DTO;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.talktales.Entities.Category;
import com.talktales.Entities.Comment;
import com.talktales.Entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date AddedDate;
	private CategoryDTO category;
	private UserDTO user;
	private Set<CommentDTO> comments= new HashSet<>();
}
