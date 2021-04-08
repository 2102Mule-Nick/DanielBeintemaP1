package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.beintema.pojo.Burger;

@Component
public class BurgerRowMapper implements RowMapper<Burger>{
	
	private BurgerExtractor burgerExtractor;
	
	@Autowired
	public void setBurgerExtractor (BurgerExtractor burgerExtractor) {
		this.burgerExtractor=burgerExtractor;
	}

	@Override
	public Burger mapRow(ResultSet rs, int rowNum) throws SQLException {
		return burgerExtractor.extractData(rs);
	}
	
	
	
}
