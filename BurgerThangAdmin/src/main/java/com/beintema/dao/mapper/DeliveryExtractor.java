package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.beintema.pojo.Deliverer;
import com.beintema.pojo.Delivery;
import com.beintema.pojo.Order;
import com.beintema.pojo.Restaurant;
import com.beintema.pojo.User;
import com.beintema.dao.DelivererDaoImpl;
import com.beintema.dao.OrderDaoImpl;
import com.beintema.dao.RestaurantDaoImpl;
import com.beintema.dao.UserDaoImpl;

@Component
public class DeliveryExtractor implements ResultSetExtractor<Delivery>{
	
	private OrderDaoImpl orderDao;
	
	private UserDaoImpl userDao;
	
	private DelivererDaoImpl delivererDao;
	
	private RestaurantDaoImpl restarauntDao;
	
	@Autowired
	public void setOrderDao(OrderDaoImpl orderDao) {
		this.orderDao = orderDao;
	}

	@Autowired
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setDelivererdao(DelivererDaoImpl delivererDao) {
		this.delivererDao = delivererDao;
	}

	@Autowired
	public void setRestarauntDao(RestaurantDaoImpl restarauntDao) {
		this.restarauntDao = restarauntDao;
	}

	@Override
	public Delivery extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Order order=orderDao.getOrderById(rs.getInt("order_id"));
		User customer=userDao.getUserByUserId(rs.getInt("customer_id"));
		Restaurant restaurant=restarauntDao.getRestaurantById(rs.getInt("restaurant_id"));
		Deliverer driver=delivererDao.getDelivererById(rs.getInt("driver_id"));
		
		Delivery newDelivery=new Delivery();
		
		newDelivery.setOrder(order);
		newDelivery.setCustomer(customer);
		newDelivery.setRestaurant(restaurant);
		newDelivery.setDistance(rs.getDouble("distance"));
		newDelivery.setDeliverytime(rs.getInt("prep_deliver"));
		newDelivery.setDriver(driver);
		newDelivery.setCost(rs.getDouble("price"));
		newDelivery.setTip(rs.getDouble("tip"));
		
		return newDelivery;
	}
	
	
	
//	create table delivery(
//			order_id references orders(order_id),
//			restaurant_id int references restaurant(restaurant_id),
//			customer_id int references user_acc(user_id),
//			driver_id int references driver_acc(driver_id),
//			restaurant_x int references restaurant(restaurant_x),
//			restaurant_y int references restaurant(restaurant_y),
//			endpoint_x int references user_acc(user_x),
//			endpoint_y int references user_acc(user_y),
//			distance numeric(2),
//			prep_deliver int,
//			price money,
//			tip money
//		);

//	private Order order;
//	private User customer;
//	private Restaurant restaurant;
//	private double distance;
//	private int deliverytime;
//	private Deliverer driver;
//	private double cost;
//	private double tip;

}
