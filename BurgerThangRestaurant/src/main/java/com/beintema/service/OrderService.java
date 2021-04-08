package com.beintema.service;

import com.beintema.pojo.Order;

public interface OrderService {
	
	public String orderReadout(Order order);
	
	public Order orderCreation(Order order);

}
