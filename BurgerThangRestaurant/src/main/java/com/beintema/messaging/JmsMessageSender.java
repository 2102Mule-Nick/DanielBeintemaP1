package com.beintema.messaging;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageSender {
	
	private JmsTemplate jmsTemplate;
	
	private Queue orderQueue;
	
	private Topic restaurantTopic;
	
	private Topic burgerTopic;

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	@Autowired
	public void setOrderQueue(Queue orderQueue) {
		this.orderQueue = orderQueue;
	}
	
	@Autowired
	public void setRestaurantTopic(Topic restaurantTopic) {
		this.restaurantTopic = restaurantTopic;
	}
	
	@Autowired
	public void setBurgerTopic(Topic burgerTopic) {
		this.burgerTopic=burgerTopic;
	}
	
	public void newRestaurant(String msg) {
		jmsTemplate.send(restaurantTopic, session-> session.createTextMessage(msg));
	}
	
	public void newBurger(String msg) {
		jmsTemplate.send(burgerTopic, session-> session.createTextMessage(msg));
	}
	
	public void orderResponse(String msg) {
		jmsTemplate.send(orderQueue, session-> session.createTextMessage(msg));
	}
}
