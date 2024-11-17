package com.anonymous.order.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

	private String orderId;
	private String userId;
	private double totalAmount;
	private String orderFStatus;
	
	private List<OrderItemDto> orderItemDtos;
}
