package com.anonymous.order.service;

import java.util.List;
import com.anonymous.order.dto.OrderRequestDto;
import com.anonymous.order.dto.OrderResponseAddressDto;
import com.anonymous.order.dto.OrderResponseDto;

public interface OrderService {

	boolean createOrder(String userId, OrderRequestDto orderRequestDto);

	boolean cancelOrder(String orderId);

	List<OrderResponseDto> getOrderByUserId(String userId);

	OrderResponseAddressDto getOrderItemByOrderIdAndItemId(String orderId, String orderItemId);

}
