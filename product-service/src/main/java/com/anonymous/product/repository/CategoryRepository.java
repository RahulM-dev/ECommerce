package com.anonymous.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anonymous.product.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
