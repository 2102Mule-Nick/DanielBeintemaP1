package com.beintema.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beintema.pojo.Burger;
import com.beintema.pojo.Deliverer;
import com.beintema.pojo.Delivery;
import com.beintema.pojo.Order;
import com.beintema.service.OrderService;

@RestController
public class OrderController {
	
	OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@PostMapping("/orders/{restaurant_id}")
	public Delivery restaurantOrder(Order order, Deliverer deliverer){
		Delivery delivery=orderService.setDelivery(order, deliverer);
		Map<List<Burger>, List<String>> burgers=order.getBurgers();
		System.out.println(burgers);
		return delivery;
	}
	
	@PostMapping("/delivery/{drivernumber}")
	public Delivery directions(Order order, Deliverer deliverer) {
		Delivery delivery=orderService.setDelivery(order, deliverer);
		System.out.println(orderService.directions(delivery));
		return delivery;
	}
}
