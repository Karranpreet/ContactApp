package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.dao.UserDao;
import com.domain.User;
import com.exception.UserBlockedException;
import com.rm.UserRowMapper;

@Service // to make it a service layer
public class UserServiceImpl extends BaseDao implements UserService{
	
	@Autowired
	private UserDao userDao;

	public void register(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	public User login(String loginName, String password) throws UserBlockedException {
		// TODO Auto-generated method stub
		String query = "SELECT userId,name,phone,email,address,role,loginName,loginStatus from user where loginName=:ln AND password=:pw";
	    Map m = new HashMap();
		m.put("ln",loginName);
		m.put("pw",password);
		try {
			User u = super.getNamedParameterJdbcTemplate().queryForObject(query, m, new UserRowMapper());
			if(u.getLoginStatus().equals(UserService.LOGIN_STATUS_BLOCKED)) {
				throw new UserBlockedException("Your account has been blocked by admin");
			} else {
				return u;
			}
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
		
	}

	public List<User> getUsersList() {
		 return userDao.findByProperty("role", UserService.ROLE_USER);
		
	}

	public void changeLoginStatus(Integer userId, Integer loginStatus) {
		  String sql = "UPDATE user SET loginStatus=:lst WHERE userId=:uid";
	        Map m = new HashMap();
	        m.put("uid", userId);
	        m.put("lst", loginStatus);
	        super.getNamedParameterJdbcTemplate().update(sql, m);
		
	}

}
