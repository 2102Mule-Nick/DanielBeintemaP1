package com.beintema.service;

import com.beintema.pojo.Burger;
import com.beintema.pojo.Order;

public interface OrderService {
	
	public Order createOrder(Order order);
	
	public Order addBurger(Order order, Burger burger);
	
	public Order removeBurger(Order order, Burger burger);
	
	public void deleteOrder(Order order);

}
