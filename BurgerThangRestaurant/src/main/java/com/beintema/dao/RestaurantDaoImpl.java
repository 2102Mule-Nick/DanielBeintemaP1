package com.beintema.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.beintema.dao.mapper.RestaurantRowMapper;
import com.beintema.pojo.Restaurant;

@Repository
public class RestaurantDaoImpl implements RestaurantDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private RestaurantRowMapper restaurantRowMapper;
	
	private RowMapper<String> rowMapper;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@Autowired
	public void setRestaurantRowMapper(RestaurantRowMapper restaurantRowMapper) {
		this.restaurantRowMapper=restaurantRowMapper;
	}
	
	@Autowired
	public void setRowMapper(RowMapper<String> rowMapper) {
		this.rowMapper=rowMapper;
	}

	@Override
	public Restaurant createRestaurant(Restaurant restaurant){
		
		String sql="insert into restaurant (restaurant_name, num_items, opening, closing, bank_account, restaurant_x, restaurant_y) "
				+ "VALUES('?',?,?,?,'?',?,?)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getRestaurantname());
			ps.setInt(2, restaurant.getNumitems());
			ps.setTime(3, restaurant.getOpening());
			ps.setTime(4, restaurant.getClosing());
			ps.setString(5, restaurant.getBankaccount());
			ps.setInt(6, restaurant.getRestaurant_x());
			ps.setInt(7, restaurant.getRestaurant_y());
//			1 newRestaurant.setRestaurantname(rs.getString("restaurant_name"));
//			2 newRestaurant.setNumitems(rs.getInt("num_items"));
//			3 newRestaurant.setOpening(rs.getTime("opening"));
//			4 newRestaurant.setClosing(rs.getTime("closing"));
//			5 newRestaurant.setBankaccount(rs.getString("bank_account"));
//			6 newRestaurant.setRestaurant_x(rs.getInt("restaurant_x"));
//			7 newRestaurant.setRestaurant_y(rs.getInt("restaurant_y"));
			return ps;
		}, keyHolder);
		
		restaurant.setRestaurant_id((int) keyHolder.getKeys().get("restaurant_id"));
		
		return restaurant;
	}

	@Override
	public Restaurant getRestaurantById(int id) {
		String sql="SELECT * FROM restaurant WHERE restaurant_id=?";
		
		List<Restaurant> restaurantList=jdbcTemplate.query(sql, restaurantRowMapper, id);
		
		return restaurantList.get(0);
	}

	@Override
	public List<Restaurant> getRestaurantByName(String name) {
		String sql ="SELECT * FROM restaurant WHERE restaurant_name='?'";
		
		List<Restaurant> restaurantList=jdbcTemplate.query(sql, restaurantRowMapper,name);
		
		return restaurantList;
	}

	@Override
	public String deleteRestaurant(int id) {
		String sql="DELETE FROM restaurant WHERE restaurant_id="+id;
		
		jdbcTemplate.execute(sql);
		
		return "Restaurant with id " +id+" removed";
	}

	@Override
	public String deleteRestaurant(String name) {
		
		String sql="DELETE FROM restaurant WHERE restaurant_name='"+name+"'";
		
		jdbcTemplate.execute(sql);
		
		return "Restaurants with name "+name+" removed";
	}
	
	@Override
	public List<String> getSpecialInstructions(int order_id){
		
		String sql="SELECT special_instructions FROM burgerorders WHERE order_id=?";
		
		List<String> instructions=jdbcTemplate.query(sql, rowMapper, order_id);
		
		return instructions;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) {

		String sql="UPDATE restaurant "
				+ "SET restaurant_name='?', "
				+ "num_items=?, "
				+ "opening=?, "
				+ "closing=?, "
				+ "bank_account='?', "
				+ "restaurant_x=?, "
				+ "restaurant_y=? "
				+ "WHERE restaurant_id=?";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getRestaurantname());
			ps.setInt(2, restaurant.getNumitems());
			ps.setTime(3, restaurant.getOpening());
			ps.setTime(4, restaurant.getClosing());
			ps.setString(5, restaurant.getBankaccount());
			ps.setInt(6, restaurant.getRestaurant_x());
			ps.setInt(7, restaurant.getRestaurant_y());
			ps.setInt(8, restaurant.getRestaurant_id());
			return ps;
		}, keyHolder);
		
		return restaurant;
	}

}
