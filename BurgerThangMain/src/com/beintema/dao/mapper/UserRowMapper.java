package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.beintema.pojo.User;

public class UserRowMapper implements RowMapper<User>{
	
	private UserExtractor userExtractor;
	
	@Autowired
	public void setUserExtractor(UserExtractor userExtractor) {
		this.userExtractor=userExtractor;
	}

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return userExtractor.extractData(rs);
	}

}
