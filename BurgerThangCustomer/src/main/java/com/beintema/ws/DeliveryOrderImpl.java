package com.beintema.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.beintema.dao.BurgerDao;
import com.beintema.messaging.JmsMessageSender;
import com.beintema.pojo.Burger;
import com.beintema.pojo.Order;
import com.beintema.service.AuthService;
import com.beintema.service.OrderService;

@WebService(endpointInterface="com.beintema.ws.DeliveryOrder",
serviceName="orderService")
public class DeliveryOrderImpl implements DeliveryOrder{
	
	private OrderService orderService;
	
	private AuthService authService;
	
	private Scanner sc;
	
	private BurgerDao burgerDao;
	
	private JmsMessageSender messageSender;

	@Autowired
	public void setBurgerDao(BurgerDao burgerDao) {
		this.burgerDao=burgerDao;
	}
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService=authService;
	}
	
	@Autowired
	public void setScanner(Scanner scanner) {
		this.sc=scanner;
	}
	
	@Override
	public String finalizeOrder(Order order) {
		return Integer.toString(order.getOrder_id());
	}

	@Override
	public Order createOrder() {
		Order order=new Order();
		order.setCustomer(authService.getCurrentUser());
		int restaurant=sc.nextInt();
		order.setRestaurant_id(restaurant);
		Map<List<Burger>, List<String>> orders=new HashMap<List<Burger>, List<String>>();
		order.setBurgers(orders);
		order.setTotal(0.00);
		orderService.createOrder(order);
		messageSender.sendOrder(orders.entrySet().toString());
		return order;
	}

	@Override
	public Order addBurgers(Order order) {
		System.out.print(burgerDao.getBurgerByRestaurant(order.getRestaurant_id()));
		System.out.println("Choose a burger");
		int burger_id=sc.nextInt();
		orderService.addBurger(order, burgerDao.getById(burger_id));
		return order;
	}

	@Override
	public Order removeBurgers(Order order) {
		System.out.print(burgerDao.getBurgerByOrder(order.getOrder_id()));
		System.out.println("Choose a burger");
		int burger_id=sc.nextInt();
		orderService.removeBurger(order, burgerDao.getById(burger_id));
		return order;
	}

	@Override
	public Order deleteOrders(Order order) {
		orderService.deleteOrder(order);
		return order;
	}

}
