package com.talktales.DTO;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private boolean lastpage;

}
