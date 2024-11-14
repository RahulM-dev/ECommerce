package com.anonymous.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anonymous.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
