package com.beintema.dao;
//JMS dao

import java.util.List;

import com.beintema.pojo.Burger;

public interface BurgerDao {
	
	public Burger createBurger(Burger burger);
	
	public Burger getById(int burger_id);
	
	public List<Burger> getAllBurgers();
	
	public List<Burger> getBurgerByName(String burger_name);
	
	public List<Burger> getBurgerByRestaurant(int Restaurant_id);
	
	public List<Burger> getBurgerByOrder(int order_id);
	
	public String removeBurger(Burger burger);
	
	public Burger updateBurger(Burger burger);
}
