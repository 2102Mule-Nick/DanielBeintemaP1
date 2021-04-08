package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.beintema.pojo.Delivery;

@Component
public class DeliveryRowMapper implements RowMapper<Delivery>{
	
	private DeliveryExtractor deliveryExtractor;

	@Autowired
	public void setDeliveryExtractor(DeliveryExtractor deliveryExtractor) {
		this.deliveryExtractor = deliveryExtractor;
	}

	@Override
	public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return deliveryExtractor.extractData(rs);
	}
	
	

}
