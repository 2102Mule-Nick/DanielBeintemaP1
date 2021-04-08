package com.beintema.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beintema.dao.RestaurantDao;
import com.beintema.messaging.JmsMessageSender;
import com.beintema.pojo.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	private RestaurantDao restaurantDao;
	
	private JmsMessageSender messageSender;

	@Autowired
	public void setMessageSender(JmsMessageSender messageSender) {
		this.messageSender = messageSender;
	}

	@Autowired
	public void setRestaurantDao(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	@Override
	public Restaurant newRestaurantAnnouncement(Restaurant restaurant) {
		restaurantDao.createRestaurant(restaurant);
		messageSender.newRestaurant("Hey y'all, looking for somewhere good to eat? Why not try the new "+restaurant.getRestaurantname()+" down at "+restaurant.getAddress()+"!");
		return restaurant;
	}

	@Override
	public Restaurant getRestaurantByRestaurantId(int restaurant_id) {
		Restaurant restaurant=restaurantDao.getRestaurantById(restaurant_id);
		return restaurant;
	}

	@Override
	@Transactional
	public List<Restaurant> getRestaurantChain(String restaurant_name) {
		List<Restaurant> chains=restaurantDao.getRestaurantByName(restaurant_name);
		return chains;
	}

	@Override
	@Transactional
	public String restaurantShuttingDown(Restaurant restaurant) {
		String end=restaurantDao.deleteRestaurant(restaurant.getRestaurant_id());
		messageSender.newRestaurant("It is with a heavy heart that we must announce the loss of "+restaurant.getRestaurantname()+" down at "+restaurant.getAddress()+". RIP");
		return end;
	}

}
