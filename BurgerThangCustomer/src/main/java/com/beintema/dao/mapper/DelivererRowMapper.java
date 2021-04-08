package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.beintema.pojo.Deliverer;

@Component
public class DelivererRowMapper implements RowMapper<Deliverer>{
	
	private DelivererExtractor delivererExtractor;
	
	@Autowired
	public void setDelivererExtractor(DelivererExtractor delivererExtractor) {
		this.delivererExtractor=delivererExtractor;
	}

	@Override
	public Deliverer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return delivererExtractor.extractData(rs);
	}
	
}
