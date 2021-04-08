package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.beintema.pojo.User;

@Component
public class UserExtractor implements ResultSetExtractor<User>{

	@Override
	public User extractData(ResultSet rs) throws SQLException, DataAccessException {

	User newUser=new User();
	
	newUser.setUser_id(rs.getInt("user_id"));
	newUser.setUser_name(rs.getString("user_name"));
	newUser.setPassword(rs.getString("pass_word"));
	newUser.setPhonenumber(rs.getString("phone_number"));
	newUser.setBank_account(rs.getString("bank_account"));
	newUser.setCustomer_x(rs.getInt("user_x"));
	newUser.setCustomer_y(rs.getInt("user_y"));
	
	return newUser;
	
//	create table user_acc(
//			user_id serial primary key,
//			user_name varchar(100),
//			pass_word varchar(100),
//			phone_number varchar(100),
//			bank_account varchar(16),
//			user_x int,
//			user_y int
//		);
	}
	
}
