package com.anonymous.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.anonymous.order.model.OrderItem;

@EnableJpaRepositories
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}
