package com.anonymous.product.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	private String productId;
	
	//Use the @valid annotation
	private String productName;
	
	@ManyToOne
	@JoinColumn(name = "category_Id", referencedColumnName = "categoryId")
	private Category category;

	private double productPrice;
	private String productBrand;
	private String productImageURL;
	private long productStockQuantity;
	private LocalDateTime productCreatedAt;
	private LocalDateTime producUpdatedAt = LocalDateTime.now();
	
}
