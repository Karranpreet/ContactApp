package com.service;

import java.util.List;

import com.domain.User;
import com.exception.UserBlockedException;

public interface UserService {
 public static final Integer LOGIN_STATUS_ACTIVE = 1;
 public static final Integer LOGIN_STATUS_BLOCKED = 2;
 
 public static final Integer ROLE_ADMIN = 1;
 public static final Integer ROLE_USER = 2;
 
	public void register(User user);
	
	//when user account is blocked userblockedexception will throw
	public User login(String loginName,String password) throws UserBlockedException;
	
	public List<User> getUsersList();
	
	public void changeLoginStatus(Integer userId,Integer loginStatus);
}
