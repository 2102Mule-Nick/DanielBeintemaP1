package com.beintema.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.beintema.pojo.Order;

@WebService
public interface DeliveryOrder {
	
	@WebMethod
	public Order createOrder();
	
	@WebMethod
	public Order addBurgers(Order order);
	
	@WebMethod
	public Order removeBurgers(Order order);
	
	@WebMethod
	public Order deleteOrders(Order order);
	
	@WebMethod
	public String finalizeOrder(Order order);

}
