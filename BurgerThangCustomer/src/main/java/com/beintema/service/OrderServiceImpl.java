package com.beintema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beintema.dao.OrderDao;
import com.beintema.pojo.Burger;
import com.beintema.pojo.Order;

@Component
public class OrderServiceImpl implements OrderService{

	private OrderDao orderDao;
	
	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public Order createOrder(Order order) {
		Order newOrder=orderDao.createOrder(order);
		return newOrder;
	}

	@Override
	public Order addBurger(Order order, Burger burger) {
		orderDao.addBurger(order, burger, "");
		return order;
	}

	@Override
	public Order removeBurger(Order order, Burger burger) {
		// TODO Auto-generated method stub
		orderDao.removeBurger(order, burger);
		return order;
	}

	@Override
	public void deleteOrder(Order order) {
		orderDao.removeOrder(order);
	}


	
	

}
