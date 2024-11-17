package com.anonymous.order.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import com.anonymous.order.model.Order;

@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order, String> {

	List<Order> findByUserId(String userId);

	@Modifying
	@Query("UPDATE Order o SET o.orderStatus = :status WHERE o.orderId = :orderId")
	int updateOrderStatus(@Param("orderId") String orderId, @Param("status") String status);

}
