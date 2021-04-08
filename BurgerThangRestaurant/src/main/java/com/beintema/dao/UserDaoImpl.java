package com.beintema.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.beintema.dao.mapper.UserRowMapper;
import com.beintema.pojo.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private UserRowMapper userRowMapper;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	public void setUserRowMapper(UserRowMapper userRowMapper) {
		this.userRowMapper = userRowMapper;
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		
		String sql="INSERT INTO user_acc(user_name, pass_word, phone_number, bank_account, user_x, user_y)"
				+ "VALUES('?','?','?','?',?,?)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,user.getUser_name());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getPhonenumber());
			ps.setString(4, user.getBank_account());
			ps.setInt(5, user.getCustomer_x());
			ps.setInt(6, user.getCustomer_y());
			return ps;
		}, keyHolder);
		
		user.setUser_id((int) keyHolder.getKeys().get("user_id"));
		
		return user;
				
//				create table user_acc(
//						user_id serial primary key,
//						user_name varchar(100),
//						pass_word varchar(100),
//						phone_number varchar(100),
//						bank_account varchar(16),
//						user_x int,
//						user_y int
//					);
	}
	
	@Override
	public User getUserByUserId(int id) {
		
		String sql="SELECT * FROM user_acc WHERE user_id=?";
		
		List<User> userList=jdbcTemplate.query(sql, userRowMapper, id);
		
		return userList.get(0);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		
		String sql="SELECT * FROM user_acc WHERE user_name='?'";
		
		List<User> userList=jdbcTemplate.query(sql, userRowMapper, username);
		
		return userList.get(0);
	}

	@Override
	public List<User> getAllUsers() {
		
		String sql="SELECT * FROM user_acc WHERE user_name='?'";
		
		List<User> userList=jdbcTemplate.query(sql, userRowMapper);
		
		return userList;
	}

	@Override
	public String removeUser(User user) {
		String sql="DELETE FROM user_acc WHERE user_id="+user.getUser_id();
		
		jdbcTemplate.execute(sql);
		
		return "Restaurants with name "+user.getUser_name()+" removed";
	}

	@Override
	public User updateUser(User user) {
		
		String sql="UPDATE user_acc "
				+ "SET user_name='?', "
				+ "pass_word='?', "
				+ "phone_number='?', "
				+ "bank_account='?', "
				+ "user_x=?, "
				+ "user_y=? "
				+ "WHERE user_id=?";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,user.getUser_name());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getPhonenumber());
			ps.setString(4, user.getBank_account());
			ps.setInt(5, user.getCustomer_x());
			ps.setInt(6, user.getCustomer_y());
			ps.setInt(7, user.getUser_id());
			return ps;
		}, keyHolder);
		
		return user;
		
	}


}
