package com.anonymous.product.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt = LocalDateTime.now();

}
