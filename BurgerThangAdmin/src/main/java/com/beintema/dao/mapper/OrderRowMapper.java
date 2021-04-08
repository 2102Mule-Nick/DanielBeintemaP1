package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.beintema.pojo.Order;

public class OrderRowMapper implements RowMapper<Order>{
	
	private OrderExtractor orderExtractor;
	
	@Autowired
	public void setOrderExtractor(OrderExtractor orderExtractor) {
		this.orderExtractor=orderExtractor;
	}

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		return orderExtractor.extractData(rs);
	}

}
