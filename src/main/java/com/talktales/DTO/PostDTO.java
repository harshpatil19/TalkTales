package com.talktales.DTO;

import java.sql.Date;

import com.talktales.Entities.Category;
import com.talktales.Entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

	private int postId;

	private String title;
	private String content;
	private String imageName;
	private Date AddedDate;
	private CategoryDTO category;
	private UserDTO user;
}
