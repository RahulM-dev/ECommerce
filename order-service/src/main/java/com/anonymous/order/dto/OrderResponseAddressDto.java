package com.anonymous.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseAddressDto {

	private String orderId;
	private String userId;
	private double totalAmount;
	private String orderStatus;
	
	private AddressDto addressDto;
	private List<OrderItemDto> orderItemDtos;
}
