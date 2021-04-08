package com.beintema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beintema.dao.BurgerDao;
import com.beintema.dao.DeliveryDao;
import com.beintema.dao.OrderDao;
import com.beintema.dao.RestaurantDao;
import com.beintema.pojo.Burger;
import com.beintema.pojo.Deliverer;
import com.beintema.pojo.Delivery;
import com.beintema.pojo.Order;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderDao orderDao;
	private DeliveryDao deliveryDao;
	private RestaurantDao restaurantDao;
	private BurgerDao burgerDao;
	
	@Autowired
	public void setRestaurantDao(RestaurantDao restaurantDao) {
		this.restaurantDao=restaurantDao;
	}
	
	@Autowired
	public void setBurgerDao(BurgerDao burgerDao) {
		this.burgerDao=burgerDao;
	}
	
	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	@Override
	@Transactional
	public Delivery setDelivery(Order order, Deliverer deliverer) {
		orderDao.createOrder(order);
		Delivery delivery=new Delivery();
		delivery.setOrder(order);
		delivery.setCost(calculateTotal(order));
		delivery.setDistance(deliveryDistance(order));;
		delivery.setDriver(deliverer);
		delivery.setDeliverytime(deliveryTime(order));
		delivery.setRestaurant(restaurantDao.getRestaurantById(order.getRestaurant_id()));
		delivery.setTip(0);
		
		Delivery newDelivery=deliveryDao.createDelivery(delivery);
		return newDelivery;
	}

	@Override
	@Transactional
	public double calculateTotal(Order order) {
		// TODO Auto-generated method stub
		double cost=0;
		List<Burger> burgers=burgerDao.getBurgerByOrder(order.getOrder_id());
		Burger[] burgerArray=(Burger[]) burgers.toArray();
		for(Burger burger : burgerArray) {
			cost+=burger.getPrice();
		}
		cost+=deliveryDistance(order);
		return cost;
	}

	@Override
	@Transactional
	public double deliveryDistance(Order order) {
		int rx=restaurantDao.getRestaurantById(order.getRestaurant_id()).getRestaurant_x();
		int ry=restaurantDao.getRestaurantById(order.getRestaurant_id()).getRestaurant_y();
		int cx=order.getCustomer().getCustomer_x();
		int cy=order.getCustomer().getCustomer_x();
		double x=Math.abs(rx-cx);
		double y=Math.abs(ry-cy);
		return x+y;
	}
	
	@Override
	@Transactional
	public int deliveryTime(Order order) {
		int time=0;
		List<Burger> burgers=burgerDao.getBurgerByOrder(order.getOrder_id());
		Burger[] burgerArray=(Burger[]) burgers.toArray();
		for(Burger burger : burgerArray) {
			if(burger.getPreptime()>time)
				time=burger.getPreptime();
		}
		time+=deliveryDistance(order);
		return time;
	}
	
	@Override
	@Transactional
	public String directions(Delivery delivery) {
		
		double x=delivery.getRestaurant().getRestaurant_x()-deliveryDao.getDeliverer_x(delivery);
		double y=delivery.getRestaurant().getRestaurant_y()-deliveryDao.getDeliverer_y(delivery);

		String eastWest1=null;
		if(x>0) {
			eastWest1="Go East for "+Math.abs(x)+" blocks.";
		}else if (x<0) {
			eastWest1="Go West for "+Math.abs(x)+" blocks.";
		}
		
		String northSouth1=null;
		if(x>0) {
			northSouth1="Then, go North for "+Math.abs(y)+" blocks.";
		}else if (x<0) {
			northSouth1="Then, go South for "+Math.abs(y)+" blocks.";
		}
		
		double x1=delivery.getRestaurant().getRestaurant_x()-delivery.getCustomer().getCustomer_x();
		double y1=delivery.getRestaurant().getRestaurant_y()-delivery.getCustomer().getCustomer_y();
		
		String eastWest2=null;
		if(x1>0) {
			eastWest2="Go East for "+Math.abs(x1)+" blocks.";
		}else if (x1<0) {
			eastWest2="Go West for "+Math.abs(x1)+" blocks.";
		}
		
		String northSouth2=null;
		if(y1>0) {
			northSouth2="Then, go North for "+Math.abs(y1)+" blocks.";
		}else if (y1<0) {
			northSouth2="Then, go South for "+Math.abs(y1)+" blocks.";
		}
		
		deliveryDao.resetDelivererLocation(delivery, delivery.getCustomer().getCustomer_x(), delivery.getCustomer().getCustomer_y());
		String directions=eastWest1+northSouth1+" You have reached the restaurant/ "+ eastWest2+northSouth2+" You have arrived at the customer.";
		return directions;
	}
	
	@Autowired
	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

}
