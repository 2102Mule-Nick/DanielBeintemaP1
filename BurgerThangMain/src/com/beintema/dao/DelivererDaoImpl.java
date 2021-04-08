package com.beintema.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.beintema.dao.mapper.DelivererRowMapper;
import com.beintema.pojo.Deliverer;

@Repository
public class DelivererDaoImpl implements DelivererDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private DelivererRowMapper delivererRowMapper;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	public void setDelivererRowMapper(DelivererRowMapper delivererRowMapper) {
		this.delivererRowMapper = delivererRowMapper;
	}
	
	@Override
	public Deliverer createDeliverer(Deliverer deliverer) {
		
		String sql="INSERT INTO driver_acc VALUES(driver_name, phone_number, licenseplate,"
				+ "carmodel, carcolor, rating, bankaccount, user_name, pass_word)"
				+ "VALUES('?','?','?','?',?,'?','?','?')";
				
				KeyHolder keyHolder=new GeneratedKeyHolder();
				
				jdbcTemplate.update(connection->{
					PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, deliverer.getDrivername());
					ps.setString(2, deliverer.getPhone());
					ps.setString(3, deliverer.getLicenseplate());
					ps.setString(4, deliverer.getCarmodel());
					ps.setString(5, deliverer.getCarcolor());
					ps.setDouble(6, deliverer.getRating());
					ps.setString(7, deliverer.getBankaccount());
					ps.setString(8, deliverer.getUsername());
					ps.setString(9, deliverer.getPassword());
					return ps;
				}, keyHolder);
				
				deliverer.setDrivernumber((int) keyHolder.getKeys().get("driver_id"));
				
				return deliverer;
				
//				create table driver_acc(
//						driver_id serial primary key,
//						driver_name varchar(100),
//						phone_number varchar(100),
//						licenseplate varchar(100),
//						carmodel varchar(100),
//						carcolor varchar(100),
//						rating numeric(2),
//						bankaccount varchar(16),
//						user_name varchar(100),
//						pass_word varchar(100)
//					);
	}

	@Override
	public Deliverer getDelivererById(int driver_id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM driver_acc WHERE driver_id=?";
		
		List<Deliverer> delivererList=jdbcTemplate.query(sql, delivererRowMapper, driver_id);
		
		return delivererList.get(0);
	}

	@Override
	public List<Deliverer> getAllDeliverer() {
		String sql="SELECT * FROM driver_acc";
		
		List<Deliverer> delivererList=jdbcTemplate.query(sql, delivererRowMapper);
		
		return delivererList;
	}

	@Override
	public Deliverer getDelivererByName(String driver_name) {
		String sql="SELECT * FROM driver_acc WHERE driver_name='?'";
		
		List<Deliverer> delivererList=jdbcTemplate.query(sql, delivererRowMapper, driver_name);
		
		return delivererList.get(0);
	}

	@Override
	public String removeDeliverer(Deliverer deliverer) {
		// TODO Auto-generated method stub
		
		String sql="DELETE FROM driver_acc WHERE driver_id="+deliverer.getDrivernumber();
		
		jdbcTemplate.execute(sql);
		
		return "Driver with name "+deliverer.getDrivername()+" removed from System.";
		
	}

	@Override
	public Deliverer updateDeliverer(Deliverer deliverer) {
		String sql="UPDATE driver_acc "
				+ "SET driver_name='?', "
				+ "phone_number='?', "
				+ "licenseplate='?', "
				+ "carmodel='?', "
				+ "carcolor='?', "
				+ "rating=?, "
				+ "bankaccount='?', "
				+ "user_name='?', "
				+ "pass_word='?'"
				+ "WHERE deliverer_id=?";
				
				KeyHolder keyHolder=new GeneratedKeyHolder();
				
				jdbcTemplate.update(connection->{
					PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, deliverer.getDrivername());
					ps.setString(2, deliverer.getPhone());
					ps.setString(3, deliverer.getLicenseplate());
					ps.setString(4, deliverer.getCarmodel());
					ps.setString(5, deliverer.getCarcolor());
					ps.setDouble(6, deliverer.getRating());
					ps.setString(7, deliverer.getBankaccount());
					ps.setString(8, deliverer.getUsername());
					ps.setString(9, deliverer.getPassword());
					ps.setInt(10, deliverer.getDrivernumber());
					return ps;
				}, keyHolder);
				
				deliverer.setDrivernumber((int) keyHolder.getKeys().get("driver_id"));
				
				return deliverer;
	}

}
