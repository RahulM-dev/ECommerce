package com.anonymous.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.anonymous.order.dto.OrderRequestDto;
import com.anonymous.order.dto.OrderResponseAddressDto;
import com.anonymous.order.dto.OrderResponseDto;
import com.anonymous.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		boolean status = this.orderService.createOrder(orderRequestDto.getUserId(), orderRequestDto);
		if (status) {
			return new ResponseEntity<>(status, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	@PatchMapping
	public ResponseEntity<Boolean> cancelOrder(@PathVariable String orderId) {
		boolean status = this.orderService.cancelOrder(orderId);
		if (status) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(@PathVariable String userId) {
		List<OrderResponseDto> orderResponseDto = this.orderService.getOrderByUserId(userId);
		return (orderResponseDto.isEmpty()) ? new ResponseEntity<>(orderResponseDto, HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
	}

	@GetMapping("/{orderId}/{orderItemId}")
	public ResponseEntity<OrderResponseAddressDto> getOrderByOrderItemId(@PathVariable String orderId,
			@PathVariable String orderItemId) {
		OrderResponseAddressDto orderResponseAddressDto = this.orderService.getOrderItemByOrderIdAndItemId(orderId,
				orderItemId);
		return (orderResponseAddressDto.getOrderId().isEmpty())
				? new ResponseEntity<>(orderResponseAddressDto, HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(orderResponseAddressDto, HttpStatus.OK);

	}
}
