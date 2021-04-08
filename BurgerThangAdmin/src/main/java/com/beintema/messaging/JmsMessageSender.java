package com.beintema.messaging;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageSender {
	
	private JmsTemplate jmsTemplate;
	
	private Queue orderQueue;

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Autowired
	public void setorderQueue(Queue orderQueue) {
		this.orderQueue = orderQueue;
	}
	
	public void sendMessage(String msg) {
		jmsTemplate.send(orderQueue, session-> session.createTextMessage(msg));
	}
}
