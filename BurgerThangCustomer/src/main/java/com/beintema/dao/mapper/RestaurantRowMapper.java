package com.beintema.dao.mapper;
//JMS

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.beintema.pojo.Restaurant;

@Component
public class RestaurantRowMapper implements RowMapper<Restaurant>{

	private RestaurantExtractor restaurantExtractor;
	
	@Autowired
	public void setRestaurantExtractor(RestaurantExtractor restaurantExtractor) {
		this.restaurantExtractor=restaurantExtractor;
	}
	
	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return restaurantExtractor.extractData(rs);
	}
	

}
