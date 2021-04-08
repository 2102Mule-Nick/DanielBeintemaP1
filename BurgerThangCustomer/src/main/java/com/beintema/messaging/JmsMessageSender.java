package com.beintema.messaging;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageSender {
	
	private JmsTemplate jmsTemplate;
	
	private Queue orders;

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Autowired
	public void setOrders(Queue orders) {
		this.orders = orders;
	}
	
	public void sendOrder(String msg) {
		jmsTemplate.send(orders,(s)->s.createTextMessage(msg));
	}

}
