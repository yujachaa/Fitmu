package com.ssafy.fitmu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.Order;
import com.ssafy.fitmu.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/order-api")
@Tag(name = "주문 컨트롤러")
public class OrderController {
	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/order")
	@Operation(summary = "주문 전체 조회")
	public ResponseEntity<?> getOrderList() {
		List<Order> orderList = orderService.getOrderList();

		if (orderList == null || orderList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
	}

	@PostMapping("/order")
	@Operation(summary = "주문 등록")
	public ResponseEntity<?> registOrder(@RequestBody Order order){
		int result = orderService.registOrder(order);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/order/user/{userId}")
	@Operation(summary = "주문 조회(사용자)")
	public ResponseEntity<?> getOrderListByUserId(@PathVariable("userId") int userId) {
		List<Order> orderList = orderService.getOrderListByUserId(userId);

		if (orderList == null || orderList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
	}

	@GetMapping("/order/seller/{sellerId}")
	@Operation(summary = "주문 조회(판매자)")
	public ResponseEntity<?> getOrderListBySellerId(@PathVariable("sellerId") int sellerId) {
		List<Order> orderList = orderService.getOrderListBySellerId(sellerId);

		if (orderList == null || orderList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
	}
	
	@GetMapping("/order/{orderId}")
	@Operation(summary = "주문 상세 조회")
	public ResponseEntity<?> getOrderDetail(@PathVariable("orderId") int orderId) {
		Order order = orderService.getOrderDetail(orderId);

		if(order == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/order/{orderId}")
	@Operation(summary = "주문 상태 수정")
	public ResponseEntity<?> updateStatus(@PathVariable("orderId") int orderId, @RequestBody Order order){
		order.setOrderId(orderId);
		
		int result = orderService.updateStatus(order);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{orderId}")
	@Operation(summary = "주문 삭제")
	public ResponseEntity<?> deleteOrder(@PathVariable("orderId") int orderId){
		int result = orderService.deleteOrder(orderId);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
