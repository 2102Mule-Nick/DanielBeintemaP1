package com.beintema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beintema.dao.UserDao;
import com.beintema.pojo.User;
import com.beintema.exception.InvalidPassword;
import com.beintema.exception.UserNameTaken;
import com.beintema.exception.UserNotFound;

@Component
public class AuthServiceImpl implements AuthService{
	
	private User currentUser;

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean existingUser(User user) {
		if (userDao.getUserByUsername(user.getUser_name()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public User authenticateUser(User user) throws InvalidPassword, UserNotFound {

		User existingUser = userDao.getUserByUsername(user.getUser_name());

		if (existingUser.getPassword().equals(user.getPassword())) {
			this.currentUser = existingUser;
			return existingUser;
		}

		throw new InvalidPassword();
	}

	@Override
	public User registerUser(User user) throws UserNameTaken {
		userDao.createUser(user);
		return user;
	}

	public AuthServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired //constructor injection
	public AuthServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public User updateUser(User user, String password) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
		return user;
	}
	
	public boolean removeUser(User user) {

		if (existingUser(user)) {
			userDao.removeUser(user);
			return true;
		}
		return false;
	}

	@Override
	public User getCurrentUser() {
		return currentUser;
	}
}
