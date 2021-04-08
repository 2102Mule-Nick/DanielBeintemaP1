package com.beintema.dao;
//SOAP dao

import java.util.List;

import com.beintema.pojo.Delivery;

public interface DeliveryDao {
	
	public Delivery createDelivery(Delivery delivery);
	
	public Delivery getDeliveryById(int delivery_id);
	
	public List<Delivery> getAllDelivery();
	
	public List<Delivery> getDeliveryByRestaurant(int restaurant_id);
	
	public Delivery getDeliveryByCustomer(int customer_id);
	
	public String removeDelivery(Delivery delivery);
	
	public Delivery updateDelivery(Delivery delivery);
	
	public int getDeliverer_x(Delivery delivery);
	
	public int getDeliverer_y(Delivery delivery);

	public void resetDelivererLocation(Delivery delivery, int customer_x, int customer_y);

}
