package com.anonymous.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import com.anonymous.product.model.Product;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, String>{

	 @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
	  List<Product> findByCategoryId(String categoryId);
	 
	 @Modifying
	 @Query(value = "UPDATE product SET product_stock_quantity = :quantity WHERE product_id = :productId", nativeQuery = true)
	 int updateProductQuantity(@Param("productId") String productId, @Param("quantity") long quantity);
	 
}
