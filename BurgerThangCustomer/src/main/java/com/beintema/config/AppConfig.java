package com.beintema.config;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.beintema.messaging.JmsMessageListener;

@ComponentScan("com.revature")
@Configuration
public class AppConfig {

	//DataSource info
		public static final String DATASOURCE_URL = 
				"jdbc:postgresql://" + 
				System.getenv("POS_DB_URL") + 
				":5432/" + 
				System.getenv("POS_DB_NAME") + 
				"?";
		public static final String DATASOURCE_DRIVERNAME = "org.postgresql.Driver";
		public static final String DATASOURCE_USERNAME = System.getenv("POS_DB_USERNAME");
		public static final String DATASOURCE_PASSWORD = System.getenv("POS_DB_PASSWORD");
		public static final String DATASOURCE_SCHEMA = "public";
		
		public static final String BROKER_URL = "tcp://localhost:61616";
		
		public static final String ORDER_QUEUE="ORDERS";
		
		@Bean
		public DataSource dataSource() {
			
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setUrl(DATASOURCE_URL);
			dataSource.setDriverClassName(DATASOURCE_DRIVERNAME);
			dataSource.setUsername(DATASOURCE_USERNAME);
			dataSource.setPassword(DATASOURCE_PASSWORD);
			dataSource.setSchema(DATASOURCE_SCHEMA);
			return dataSource;
			
		}
		
		@Bean
		public JdbcTemplate jdbcTemplate(DataSource dataSource) {
			JdbcTemplate template = new JdbcTemplate();
			template.setDataSource(dataSource);
			return template;
		}

		@Bean
		@Scope("singleton")
		public Scanner consoleInScanner() {
			return new Scanner(System.in);
		}

		@Bean
		public Logger log() {
			return Logger.getRootLogger();
		}

		@Bean
		public ActiveMQConnectionFactory amqConnectionFactory() {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
			//connectionFactory.setTrustAllPackages(true);
			return connectionFactory;
		}

		@Bean
		public ConnectionFactory connectionFactory(ActiveMQConnectionFactory amqConnectionFactory) {
			return new SingleConnectionFactory(amqConnectionFactory);
		}

		@Bean
		public Queue destinationQueue() {
			return new ActiveMQQueue(ORDER_QUEUE);
		}

		// this will allow us to consume messages from the queue, using Spring for help

		@Bean
		public DefaultMessageListenerContainer jmsContainer(ConnectionFactory connectionFactory,
				JmsMessageListener messageListener) {
			DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
			container.setConnectionFactory(connectionFactory);
//			container.setDestinationName(EXAMPLE_QUEUE);
			container.setDestinationName(ORDER_QUEUE);
			container.setPubSubDomain(true);
			
			container.setMessageListener(messageListener);
			return container;
		}

		@Bean
		public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
			JmsTemplate template = new JmsTemplate();
			template.setConnectionFactory(connectionFactory);
			
			return template;
		}
}
