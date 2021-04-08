package com.beintema.dao;
//Rest dao

import java.util.List;

import com.beintema.pojo.Burger;
import com.beintema.pojo.Order;

public interface OrderDao {
	
	public Order createOrder(Order order);
	
	public Order getOrderById(int order_id);
	
	public List<Order> getAllOrders();
	
	public String removeOrder(Order order);
	
	public Order addBurger(Order order, Burger burger, String instructions);
	
	public Order removeBurger(Order order, Burger burger);
	
	public Order updateOrder(Order order);

}
