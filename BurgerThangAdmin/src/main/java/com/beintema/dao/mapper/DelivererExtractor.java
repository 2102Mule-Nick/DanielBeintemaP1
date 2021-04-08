package com.beintema.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.beintema.pojo.Deliverer;

@Component
public class DelivererExtractor implements ResultSetExtractor<Deliverer>{

	@Override
	public Deliverer extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		
		Deliverer newDeliverer=new Deliverer();
		
		newDeliverer.setDrivernumber(rs.getInt("driver_id"));
		newDeliverer.setDrivername(rs.getString("driver_name"));
		newDeliverer.setPhone(rs.getString("phone_number"));
		newDeliverer.setCarmodel(rs.getString("carmodel"));
		newDeliverer.setCarcolor(rs.getString("carcolor"));
		newDeliverer.setRating(rs.getDouble("rating"));
		newDeliverer.setBankaccount(rs.getString("bankaccount"));
		newDeliverer.setPassword(rs.getString("user_name"));
		newDeliverer.setPassword(rs.getString("pass_word"));
		
		return newDeliverer;
//		create table driver_acc(
//				driver_id serial primary key,
//				driver_name varchar(100),
//				phone_number varchar(100),
//				licenseplate varchar(100),
//				carmodel varchar(100),
//				carcolor varchar(100),
//				rating numeric(2),
//				bankaccount varchar(16),
//				user_name varchar(100),
//				pass_word varchar(100)
//			);
	}
	

}
