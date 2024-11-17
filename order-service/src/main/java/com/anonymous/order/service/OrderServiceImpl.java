package com.anonymous.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.anonymous.order.dto.OrderItemDto;
import com.anonymous.order.dto.OrderRequestDto;
import com.anonymous.order.dto.OrderResponseAddressDto;
import com.anonymous.order.dto.OrderResponseDto;
import com.anonymous.order.dto.UpdateProductDto;
import com.anonymous.order.model.Address;
import com.anonymous.order.model.Order;
import com.anonymous.order.model.OrderItem;
import com.anonymous.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebClient webClient;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public boolean createOrder(String userId, OrderRequestDto orderRequestDto) {
		if (!productQuantityUpdate(orderRequestDto)) {
			return false;
		}

		try {
			Order order = new Order();
			order.setOrderId(UUID.randomUUID().toString());
			order.setUserId(userId);
			order.setPaymentMode(orderRequestDto.getPaymentMode());
			Address address = this.modelMapper.map(orderRequestDto.getAddressDto(), Address.class);
			order.setAddress(address);

			List<OrderItem> orderItems = new ArrayList<>();
			double totalPrice = 0.0;
			for (OrderItemDto itemDto : orderRequestDto.getOrderItemDtos()) {
				OrderItem orderItem = this.modelMapper.map(itemDto, OrderItem.class);
				orderItem.setOrderItemId(UUID.randomUUID().toString());
				orderItem.setProductId(itemDto.getOrderItemId());
				orderItem.setOrder(order);
				orderItems.add(orderItem);
				totalPrice = totalPrice + (orderItem.getProductPrice() * orderItem.getProductQuantity());
			}
			order.setTotalAmount(totalPrice);
			order.setOrderStatus("CONFIRMED");
			order.setOrderItems(orderItems);

			this.orderRepository.save(order);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cancelOrder(String orderId) {
		Optional<Order> order = this.orderRepository.findById(orderId);
		if (order.isEmpty()) {
			return false;
		}
		this.orderRepository.updateOrderStatus(orderId, "Cancelled");
		return true;
	}

	@Override
	public List<OrderResponseDto> getOrderByUserId(String userId) {
		List<Order> orders = this.orderRepository.findByUserId(userId);
		List<OrderResponseDto> orderResponseDtos = new ArrayList<OrderResponseDto>();
		if (orders.isEmpty()) {
			return orderResponseDtos;
		}
		orderResponseDtos = orders.stream().map(item -> convertToOrderResponseDto(item)).toList();
		return orderResponseDtos;
	}

	@Override
	public OrderResponseAddressDto getOrderItemByOrderIdAndItemId(String orderId, String orderItemId) {

		Optional<Order> order = this.orderRepository.findById(orderId);
		if (order.isEmpty()) {
			return new OrderResponseAddressDto();
		}
		OrderItem orderItem = order.get().getOrderItems().stream()
				.filter(item -> item.getOrderItemId().equals(orderItemId)).findFirst().orElse(new OrderItem());
		return this.modelMapper.map(orderItem, OrderResponseAddressDto.class);
	}

	private OrderResponseDto convertToOrderResponseDto(Order order) {
		OrderResponseDto orderResponseDto = this.modelMapper.map(order, OrderResponseDto.class);

		List<OrderItemDto> orderItemDtos = order.getOrderItems().stream().map(item -> {
			OrderItemDto orderItemDto = this.modelMapper.map(item, OrderItemDto.class);
			orderItemDto.setOrderItemId(item.getProductId());
			return orderItemDto;
		}).toList();
		orderResponseDto.setOrderItemDtos(orderItemDtos);
		return orderResponseDto;
	}

	public boolean productQuantityUpdate(OrderRequestDto orderRequestDto) {
		String url = "http://localhost:8080/api/products/orderProducts";

		List<UpdateProductDto> updateProductDtos = orderRequestDto.getOrderItemDtos().stream()
				.map(item -> convertToUpdateProductDto(item)).toList();

		try {
//			ResponseEntity<Boolean> status = this.restTemplate.exchange(url, HttpMethod.PATCH, requestEntity,
//					Boolean.class);
//			return status.getBody();
			Boolean response = webClient.patch().uri(url).bodyValue(updateProductDtos).retrieve()
					.bodyToMono(Boolean.class).block();

			return response != null && response;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public UpdateProductDto convertToUpdateProductDto(OrderItemDto orderItemDto) {
		UpdateProductDto updateProductDto = new UpdateProductDto();
		updateProductDto.setProductId(orderItemDto.getOrderItemId());
		updateProductDto.setProductStockQuantity(orderItemDto.getProductQuantity());
		return updateProductDto;
	}

}
