package com.anonymous.product.model;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String productId;
	
	//Use the @valid annotation
	private String productName;
	
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category productCategory;

	private double productPrice;
	private String productBrand;
	private String productImageURL;
	private long productStockQuantity;
	private LocalDateTime productCreatedAt = LocalDateTime.now();
	private LocalDateTime producUpdatedAt = LocalDateTime.now();
	
}
