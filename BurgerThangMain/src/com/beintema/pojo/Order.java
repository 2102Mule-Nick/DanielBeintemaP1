package com.beintema.pojo;

import java.util.List;
import java.util.Map;

public class Order {
	
	private int order_id;
	private User customer;
	private int restaurant_id;
	private Map<List<Burger>, List<String>> burgers;
	private double total;
	
	public Order() {
		super();
	}
	
	public Order(int order_id, User customer, int restaurant_id, Map<List<Burger>, List<String>> burgers, double total) {
		this.setOrder_id(order_id);
		this.setCustomer(customer);
		this.setRestaurant_id(restaurant_id);
		this.setBurgers(burgers);
		this.setTotal(total);
	}
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public Map<List<Burger>, List<String>> getBurgers() {
		return burgers;
	}
	public void setBurgers(Map<List<Burger>, List<String>> burgers) {
		this.burgers = burgers;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}
	

}
