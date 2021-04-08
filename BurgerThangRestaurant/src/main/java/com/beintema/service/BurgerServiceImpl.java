package com.beintema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beintema.dao.BurgerDao;
import com.beintema.messaging.JmsMessageSender;
import com.beintema.pojo.Burger;
import com.beintema.pojo.Restaurant;

@Service
public class BurgerServiceImpl implements BurgerService{
	
	private BurgerDao burgerDao;
	
	private JmsMessageSender messageSender;

	@Override
	@Transactional
	public Burger newBurgerAnnouncement(Burger burger) {
		burgerDao.createBurger(burger);
		messageSender.newBurger("Introducing the new "+burger.getBurgername()+" from "
				+ burger.getRestaurant().getRestaurantname()+"! Order one today, yah burger dorks!");
		return burger;
	}

	@Override
	@Transactional
	public String burgerRemovalAnnouncement(Burger burger) {
		String end=burgerDao.removeBurger(burger);
		messageSender.newBurger("Well, it sucks, but say goodbye to the "
				+ burger.getBurgername()+" from "
				+ burger.getRestaurant().getRestaurantname()+".");
		// TODO Auto-generated method stub
		return end;
	}

	@Override
	public Burger getBurgerByBurgerId(int burger_id) {
		return burgerDao.getById(burger_id);
	}

	@Override
	@Transactional
	public List<Burger> burgerByRestaurant(Restaurant restaurant) {
		List<Burger> restaurantsBurgers=burgerDao.getBurgerByRestaurant(restaurant.getRestaurant_id());
		return restaurantsBurgers;
	}
	
	@Autowired
	public void setBurgerDao(BurgerDao burgerDao) {
		this.burgerDao = burgerDao;
	}
	
	@Autowired
	public void setMessageSender(JmsMessageSender messageSender) {
		this.messageSender = messageSender;
	}

}
