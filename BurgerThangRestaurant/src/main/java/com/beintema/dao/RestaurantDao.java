package com.beintema.dao;
//JMS

import java.sql.SQLException;
import java.util.List;

import com.beintema.pojo.Restaurant;

public interface RestaurantDao {
	
	public Restaurant createRestaurant(Restaurant restaraurant);
	
	public Restaurant getRestaurantById(int id);
	
	public List<Restaurant> getRestaurantByName(String name);
	
	public String deleteRestaurant(int id);
	
	public String deleteRestaurant(String name);
	
	public List<String> getSpecialInstructions(int order_id);
	
	public Restaurant updateRestaurant(Restaurant restaurant);

}
