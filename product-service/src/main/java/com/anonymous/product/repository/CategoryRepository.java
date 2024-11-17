package com.anonymous.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.anonymous.product.model.Category;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, String>{
	
	void deleteByCategoryId(String categoryId);

}
