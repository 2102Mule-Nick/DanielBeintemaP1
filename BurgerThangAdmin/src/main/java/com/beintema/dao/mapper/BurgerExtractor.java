package com.beintema.dao.mapper;
//JMS

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.beintema.dao.RestaurantDaoImpl;
import com.beintema.pojo.Burger;
import com.beintema.pojo.Restaurant;

@Component
public class BurgerExtractor implements ResultSetExtractor<Burger>{
	
	private RestaurantDaoImpl restaurantDao;
	
	@Autowired
	public void setRestaurantDao(RestaurantDaoImpl restaurantDao) {
		this.restaurantDao=restaurantDao;
	}

	@Override
	public Burger extractData(ResultSet rs) throws SQLException, DataAccessException {
		// Used to input information about a burger from the burger information set.
		
		Restaurant restaurant=restaurantDao.getRestaurantById(rs.getInt("restaurant_id"));
		
		Burger newBurger=new Burger();
		
		newBurger.setBurger_id(rs.getInt("burger_id"));
		newBurger.setBurgername(rs.getString("burger_name"));
		newBurger.setIngredients(rs.getString("ingredients"));
		newBurger.setPreptime(rs.getInt("preptime"));
		newBurger.setPrice(rs.getDouble("price"));
		newBurger.setRestaurant(restaurant);
		
		return newBurger;
		
	}
	
	

}
