package com.beintema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beintema.pojo.Order;
import com.beintema.service.OrderService;

@RestController
public class OrderController {
	
	OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService=orderService;
	}
	@GetMapping("/restaurants/{order_id}")
	public Order createOrder(Order order) {
		System.out.println(orderService.orderReadout(order));
		return order;
	}
}
