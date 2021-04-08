package com.beintema.dao;
//Rest Dao

import java.util.List;

import com.beintema.pojo.User;

public interface UserDao {
	
	public User createUser(User user);
	
	public User getUserByUserId(int id);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	public String removeUser(User user);
	
	public User updateUser(User user);

}
