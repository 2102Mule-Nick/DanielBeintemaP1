package com.beintema.service;

import com.beintema.pojo.User;
import com.beintema.exception.InvalidPassword;
import com.beintema.exception.UserNameTaken;
import com.beintema.exception.UserNotFound;

public interface AuthService {
	
	public boolean existingUser(User user) throws UserNotFound;
	
	public User authenticateUser(User user) throws InvalidPassword, UserNotFound;
	
	public User registerUser(User user) throws UserNameTaken;
	
	public User updateUser(User user, String password);

	public boolean removeUser(User user);
	
	public User getCurrentUser();

}
