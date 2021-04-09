package com.beintema.service;

import com.beintema.dao.OrderDao;
import com.beintema.pojo.Order;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao orderDao;


@Override
public String orderReadout(Order order) {
	return order.getBurgers().toString();
	
}
@Override
public Order orderCreation(Order order) {
	Order ordernew=orderDao.createOrder(order);
	return ordernew;
}
}
