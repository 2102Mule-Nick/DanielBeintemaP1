package com.beintema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beintema.pojo.Delivery;
import com.beintema.service.OrderService;

@RestController
public class DirectionController {
	
	OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@GetMapping("/delivery/{order_id}")
	public Delivery getDelivery(Delivery delivery){
		System.out.println(orderService.directions(delivery));
		return delivery;
	}
	

}
