package com.talktales.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {


	private int categoryId;
@NotNull
@Size(min= 50,message="TItle cannot be greater than 50 Caracters")
	private String categoryTitle;
@NotNull
	private String categoryDescription;
	
	
}
