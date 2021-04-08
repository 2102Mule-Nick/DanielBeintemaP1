package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.beintema.dao.BurgerDaoImpl;
import com.beintema.dao.RestaurantDaoImpl;
import com.beintema.dao.UserDaoImpl;
import com.beintema.pojo.Burger;
import com.beintema.pojo.Order;
import com.beintema.pojo.User;

@Component
public class OrderExtractor implements ResultSetExtractor<Order>{
	
	private UserDaoImpl userDao;
	
	private BurgerDaoImpl burgerDao;
	
	private RestaurantDaoImpl restaurantDao;
	
	@Autowired
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	@Autowired
	public void setBurgerDao(BurgerDaoImpl burgerDao) {
		this.burgerDao=burgerDao;
	}
	@Autowired
	public void setRestaurantDao(RestaurantDaoImpl restaurantDao) {
		this.restaurantDao=restaurantDao;
	}

	@Override
	public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		
		User customer=userDao.getUserByUserId(rs.getInt("customer_id"));
		
		List<Burger> burgers=burgerDao.getBurgerByOrder(rs.getInt("order_id"));
		
		List<String> instructions=restaurantDao.getSpecialInstructions(rs.getInt("order_id"));
		
		Map<List<Burger>, List<String>> neww=new HashMap<List<Burger>, List<String>>();
		neww.put(burgers, instructions);
		
		Order newOrder=new Order();
		
		newOrder.setOrder_id(rs.getInt("order_id"));
		newOrder.setRestaurant_id(rs.getInt("restaurant_id"));
		newOrder.setBurgers(neww);
		newOrder.setCustomer(customer);
		newOrder.setTotal(rs.getDouble("total"));
		
		return newOrder;
	}
	
//	private int order_id;
//	private int restaurant_id;
//	private Map<List<Burger>, List<String>> burgers;
//	private double total;
	
//	create table orders(
//			order_id serial primary key,
//			customer_id int references user_acc(user_id),
//			restaurant_id references restaurant(restaurant_id)
//			total numeric(2)
//			);
//
//			create table burgerorders(
//			order_id references orders(order_id),
//			burger_id references burgers(burger_id),
//			special_instructions varchar(100)
//			);

}
