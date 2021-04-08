package com.beintema.pojo;

public class Delivery {
	
	private Order order;
	private User customer;
	private Restaurant restaurant;
	private double distance;
	private int deliverytime;
	private Deliverer driver;
	private double cost;
	private double tip;
	
	public Delivery(){
		super();
	}
	
	public Delivery(Order order, User customer, Restaurant restaurant, double distance,
			int deliverytime, Deliverer driver, double cost, double tip){
		this.order=order;
		this.customer=customer;
		this.restaurant=restaurant;
		this.distance=distance;
		this.deliverytime=deliverytime;
		this.driver=driver;
		this.cost=cost;
		this.tip=tip;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(int deliverytime) {
		this.deliverytime = deliverytime;
	}

	public Deliverer getDriver() {
		return driver;
	}

	public void setDriver(Deliverer driver) {
		this.driver = driver;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTip() {
		return tip;
	}

	public void setTip(double tip) {
		this.tip = tip;
	}

}
