package com.anonymous.order.event;

import java.util.List;

import com.anonymous.order.dto.AddressDto;
import com.anonymous.order.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
	
	private String orderId;
	private String userId;
	private String userName;
	private AddressDto address; //Create Address object after adding it in the user microservice
	
	private List<OrderItemDto> orderItems;

}
