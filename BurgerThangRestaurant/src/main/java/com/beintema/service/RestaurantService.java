package com.beintema.service;

import java.util.List;

import com.beintema.pojo.Restaurant;

public interface RestaurantService {
	
	public Restaurant newRestaurantAnnouncement(Restaurant restaurant);
	
	public Restaurant getRestaurantByRestaurantId(int restaurant_id);
	
	public List<Restaurant> getRestaurantChain(String restaurant_name);
	
	public String restaurantShuttingDown(Restaurant restaurant);

}
