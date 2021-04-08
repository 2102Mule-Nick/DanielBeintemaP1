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

import com.beintema.dao.mapper.DeliveryRowMapper;
import com.beintema.pojo.Delivery;

@Repository
public class DeliveryDaoImpl implements DeliveryDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private DeliveryRowMapper deliveryRowMapper;
	
	private RowMapper<Integer> rowMapper;
	
	@Autowired
	public void setRowMapper(RowMapper<Integer> rowMapper) {
		this.rowMapper=rowMapper;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Autowired
	public void setDeliveryRowMapper(DeliveryRowMapper deliveryRowMapper) {
		this.deliveryRowMapper=deliveryRowMapper;
	}

	@Override
	public Delivery createDelivery(Delivery delivery) {
		// TODO Auto-generated method stub
		
		String sql="INSERT INTO delivery(order_id, restaurant_id, customer_id,"
				+ "driver_id, restaurant_x, restaurant_y, endpoint_x, endpoint_y,"
				+ "distance, prep_deliver, price, tip)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, delivery.getOrder().getOrder_id());
			ps.setInt(2, delivery.getRestaurant().getRestaurant_id());
			ps.setInt(3, delivery.getCustomer().getUser_id());
			ps.setInt(4, delivery.getDriver().getDrivernumber());
			ps.setInt(5, delivery.getRestaurant().getRestaurant_x());
			ps.setInt(6, delivery.getRestaurant().getRestaurant_y());
			ps.setInt(7, delivery.getCustomer().getCustomer_x());
			ps.setInt(8, delivery.getCustomer().getCustomer_y());
			ps.setDouble(9, delivery.getDistance());
			ps.setInt(10, delivery.getDeliverytime());
			ps.setDouble(11, delivery.getCost());
			ps.setDouble(12, delivery.getTip());
			return ps;
		}, keyHolder);
		
		return delivery;
		
//		create table delivery(
//				1 order_id references orders(order_id),
//				2 restaurant_id int references restaurant(restaurant_id),
//				3 customer_id int references user_acc(user_id),
//				4 driver_id int references driver_acc(driver_id),
//				5 restaurant_x int references restaurant(restaurant_x),
//				6 restaurant_y int references restaurant(restaurant_y),
//				7 endpoint_x int references user_acc(user_x),
//				8 endpoint_y int references user_acc(user_y),
//				distance numeric(2),
//				prep_deliver int,
//				price money,
//				tip money
//			);
	}

	@Override
	public Delivery getDeliveryById(int delivery_id) {
		String sql="SELECT * FROM delivery WHERE deliver_id=?";
		
		List<Delivery> delivers=jdbcTemplate.query(sql, deliveryRowMapper,delivery_id);
		
		return delivers.get(0);
	}

	@Override
	public List<Delivery> getAllDelivery() {
		String sql="SELECT * FROM delivery";
		
		List<Delivery> delivers=jdbcTemplate.query(sql, deliveryRowMapper);
		
		return delivers;
	}

	@Override
	public List<Delivery> getDeliveryByRestaurant(int restaurant_id) {

		String sql="SELECT * FROM delivery WHERE restaurant_id=?";
		
		List<Delivery> delivers=jdbcTemplate.query(sql, deliveryRowMapper, restaurant_id);
		
		return delivers;
	}

	@Override
	public Delivery getDeliveryByCustomer(int customer_id) {
		
		String sql="SELECT * FROM delivery WHERE customer_id=?";
		
		List<Delivery> delivers=jdbcTemplate.query(sql, deliveryRowMapper,customer_id);
		
		return delivers.get(0);
	}

	@Override
	public String removeDelivery(Delivery delivery) {

		String sql="DELETE FROM delivery WHERE order_id="+delivery.getOrder().getOrder_id();
		
		jdbcTemplate.execute(sql);
		
		return "Delivery order with id "+delivery.getOrder().getOrder_id()+" removed.";
	}

	@Override
	public Delivery updateDelivery(Delivery delivery) {
		String sql="UPDATE delivery "
				+ "SET restaurant_id=?, "
				+ "customer_id=?, "
				+ "driver_id=?, "
				+ "restaurant_x=?, "
				+ "restaurant_y=?, "
				+ "endpoint_x=?, "
				+ "endpoint_y=?, "
				+ "distance=?, "
				+ "prep_deliver=?, "
				+ "price=?, "
				+ "tip=? "
				+ "WHERE order_id=?";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(12, delivery.getOrder().getOrder_id());
			ps.setInt(1, delivery.getRestaurant().getRestaurant_id());
			ps.setInt(2, delivery.getCustomer().getUser_id());
			ps.setInt(3, delivery.getDriver().getDrivernumber());
			ps.setInt(4, delivery.getRestaurant().getRestaurant_x());
			ps.setInt(5, delivery.getRestaurant().getRestaurant_y());
			ps.setInt(6, delivery.getCustomer().getCustomer_x());
			ps.setInt(7, delivery.getCustomer().getCustomer_y());
			ps.setDouble(8, delivery.getDistance());
			ps.setInt(9, delivery.getDeliverytime());
			ps.setDouble(10, delivery.getCost());
			ps.setDouble(11, delivery.getTip());
			return ps;
		}, keyHolder);
		
		return delivery;
		
	}
	
	@Override
	public int getDeliverer_x(Delivery delivery) {
		String sql="SELECT x_axis FROM driver_locations WHERE driver_id="+delivery.getDriver().getDrivernumber();
		List<Integer> x_axis=jdbcTemplate.query(sql, rowMapper);
		int driver_x=x_axis.get(0);
		
		return driver_x;
		
	}

	@Override
	public int getDeliverer_y(Delivery delivery) {
		String sql="SELECT y_axis FROM driver_locations WHERE driver_id="+delivery.getDriver().getDrivernumber();
		List<Integer> y_axis=jdbcTemplate.query(sql, rowMapper);
		int driver_y=y_axis.get(0);
		
		return driver_y;
	}

	@Override
	public void resetDelivererLocation(Delivery delivery, int customer_x, int customer_y) {
		String sql="UPDATE driver_locations "+ "SET x_axis="+customer_x+ " y_axis="+customer_y+ " WHERE driver_id="+delivery.getDriver().getDrivernumber();
		
		jdbcTemplate.execute(sql);
		
	}

}
