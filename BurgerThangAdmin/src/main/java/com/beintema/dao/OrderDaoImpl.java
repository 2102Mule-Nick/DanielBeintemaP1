package com.beintema.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.beintema.dao.mapper.OrderRowMapper;
import com.beintema.pojo.Burger;
import com.beintema.pojo.Order;

@Repository
public class OrderDaoImpl implements OrderDao{
	
	private JdbcTemplate jdbcTemplate;

	private OrderRowMapper orderRowMapper;
	
	private BurgerDaoImpl burgerDao;
	
	private RestaurantDaoImpl restaurantDao;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Autowired
	public void setOrderRowMapper(OrderRowMapper orderRowMapper) {
		this.orderRowMapper = orderRowMapper;
	}
	
	@Autowired
	public void setBurgerDao(BurgerDaoImpl burgerDao) {
		this.burgerDao = burgerDao;
	}
	@Autowired
	public void setRestaurantDao(RestaurantDaoImpl restaurantDao) {
		this.restaurantDao = restaurantDao;
	}
	@Override
	public Order createOrder(Order order) {
		
		String sql="INSERT INTO orders(customer_id, restaurant_id, total)"
				+ "VALUES(?,?,?)";
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,order.getCustomer().getUser_id());
			ps.setInt(2,order.getRestaurant_id());
			ps.setDouble(3, order.getTotal());
			return ps;
		}, keyHolder);
		
		order.setOrder_id((int) keyHolder.getKeys().get("order_id"));
		
		return order;
	}

	@Override
	public Order getOrderById(int order_id) {
		String sql = "SELECT * FROM orders WHERE order_id=?";
		
		List<Order> orders=jdbcTemplate.query(sql, orderRowMapper, order_id);
		
		return orders.get(0);
	}

	@Override
	public List<Order> getAllOrders() {
		String sql="SELECT * FROM orders WHERE order_id=?";
		
		List<Order> orders=jdbcTemplate.query(sql, orderRowMapper);
		
		return orders;
	}

	@Override
	public String removeOrder(Order order) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM orders WHERE order_id="+order.getOrder_id();
		
		jdbcTemplate.execute(sql);
		
		String sql2="DELETE FROM burgerorders WHERE order_id="+order.getOrder_id();
		
		jdbcTemplate.execute(sql2);
		
		return "Order with order id "+order.getOrder_id()+" removed.";
	}

	@Override
	public Order addBurger(Order order, Burger burger, String instructions) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO burgerorders(order_id, burger_id, special_instructions)"
				+ "VALUES(?,?,'?')";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getOrder_id());
			ps.setInt(2, burger.getBurger_id());
			ps.setString(3, instructions);
			return ps;
		}, keyHolder);
		
		List<Burger> burgernew=burgerDao.getBurgerByOrder(order.getOrder_id());
		List<String> instructionsnew=restaurantDao.getSpecialInstructions(order.getOrder_id());
		order.getBurgers().clear();
		order.getBurgers().put(burgernew,instructionsnew);
		
		return order;
	}

	@Override
	public Order updateOrder(Order order) {
		String sql="UPDATE orders "
				+ "SET customer_id=?, "
				+ "restaurant_id=?, "
				+ "total=? "
				+ "WHERE order_id=?";
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,order.getCustomer().getUser_id());
			ps.setInt(2,order.getRestaurant_id());
			ps.setDouble(3, order.getTotal());
			ps.setInt(4, order.getOrder_id());
			return ps;
		}, keyHolder);
		
		order.setOrder_id((int) keyHolder.getKeys().get("order_id"));
		
		return order;
	}

	@Override
	public Order removeBurger(Order order, Burger burger) {
		String sql="DELETE FROM burgerorders WHERE order_id=? AND burger_id=?";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getOrder_id());
			ps.setInt(2, burger.getBurger_id());
			return ps;
		}, keyHolder);
		
		List<Burger> burgernew=burgerDao.getBurgerByOrder(order.getOrder_id());
		List<String> instructionsnew=restaurantDao.getSpecialInstructions(order.getOrder_id());
		order.getBurgers().clear();
		order.getBurgers().put(burgernew,instructionsnew);
		
		return order;
	}

}
