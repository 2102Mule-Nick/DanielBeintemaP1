package com.beintema.service;

import com.beintema.pojo.Deliverer;
import com.beintema.pojo.Delivery;
import com.beintema.pojo.Order;

public interface OrderService {
	
	public Delivery setDelivery(Order order, Deliverer deliverer);
	
	public double calculateTotal(Order order);

	public double deliveryDistance(Order order);

	int deliveryTime(Order order);

	String directions(Delivery delivery);

}
