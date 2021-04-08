package com.beintema.dao;
//SOAP dao

import java.util.List;

import com.beintema.pojo.Deliverer;

public interface DelivererDao {
	
	public Deliverer createDeliverer(Deliverer deliverer);
	
	public Deliverer getDelivererById(int driver_id);
	
	public List<Deliverer> getAllDeliverer();
	
	public Deliverer getDelivererByName(String driver_name);
	
	public String removeDeliverer(Deliverer deliverer);
	
	public Deliverer updateDeliverer(Deliverer deliverer);

}
