package com.anonymous.order.model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	private String orderId;
	private String userId;
	private double totalAmount;
	private String paymentMode;
	private String addressId;
	private LocalDateTime orderCreatedTime = LocalDateTime.now();
	private LocalDateTime orderUpdatedTime;

	private String orderStatus;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;

}
