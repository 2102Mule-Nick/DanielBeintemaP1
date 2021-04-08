package com.beintema.service;

import java.util.List;

import com.beintema.pojo.Burger;
import com.beintema.pojo.Restaurant;

public interface BurgerService {
	
	public Burger newBurgerAnnouncement(Burger burger);
	
	public String burgerRemovalAnnouncement(Burger burger);
	
	public Burger getBurgerByBurgerId(int burger_id);
	
	public List<Burger> burgerByRestaurant(Restaurant restaurant);

}
