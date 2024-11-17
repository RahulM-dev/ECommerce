package com.anonymous.order.dto;

import java.util.List;

import com.anonymous.order.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

	private String userId;
	private String userName;
	private String paymentMode;

	private AddressDto addressDto;

	private List<OrderItemDto> orderItemDtos;

}
