package com.beintema.dao.mapper;
//JMS

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.beintema.pojo.Restaurant;

@Component
public class RestaurantExtractor implements ResultSetExtractor<Restaurant>{

	@Override
	public Restaurant extractData(ResultSet rs) throws SQLException, DataAccessException {
		// Searches up and extracts a restaurant from result set.
		
		Restaurant newRestaurant=new Restaurant();
		
		newRestaurant.setRestaurant_id(rs.getInt("restaurant_id"));
		newRestaurant.setRestaurantname(rs.getString("restaurant_name"));
		newRestaurant.setNumitems(rs.getInt("num_items"));
		newRestaurant.setOpening(rs.getTime("opening"));
		newRestaurant.setClosing(rs.getTime("closing"));
		newRestaurant.setBankaccount(rs.getString("bank_account"));
		newRestaurant.setRestaurant_x(rs.getInt("restaurant_x"));
		newRestaurant.setRestaurant_y(rs.getInt("restaurant_y"));
		
		return newRestaurant;
		
//		create table restaurant(
//				restaurant_id serial primary key,
//				restaurant_name varchar(100),
//				num_items int,
//				opening time,
//				closing time,
//				bank_account varchar(16),
//				restaurant_x int,
//				restaurant_y int
//			);
	}
}
