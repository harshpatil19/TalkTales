package com.talktales.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	@Column(name="Post_title", length=100, nullable=false)
	private String title;
	@Column(length=10000)
	private String content;
	private String imageName;
	private Date Addeddate;
	@ManyToOne
	private Category category;
	@ManyToOne
	private User user;

}
