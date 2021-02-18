package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.SpringRootConfig;
import com.dao.UserDao;
import com.domain.User;
import com.service.UserService;

public class TestUserDaoSave {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		//UserDao userDao = ctx.getBean(UserDao.class);
		UserService userService = ctx.getBean(UserService.class);
		
		User u = new User();
		u.setUserId(15);
		u.setPhone("9000011001");
		u.setName("maanpreet");
		u.setAddress("B-44 Ghantaghar");
		u.setEmail("kreet@gmail.com");
		u.setLoginName("preetjudge");
		u.setLoginStatus(1);
		u.setPassword("Karan@123");
		u.setRole(0);
//		userDao.delete(4);
		userService.register(u);
		
//	   User u = userDao.findById(3);
//	   System.out.println(u.name +"  " + u.email);
//		List<User> u = userDao.findAll();
//		for(User us :u) {
//			System.out.println(us.loginName);
//		}
		
		System.out.println("-----------------kk--------");
	}
}
