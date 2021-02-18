package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.SpringRootConfig;
import com.dao.UserDao;
import com.domain.Contact;
import com.domain.User;
import com.service.ContactService;
import com.service.UserService;

public class TestConatctDaoService {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
		//UserDao userDao = ctx.getBean(UserDao.class);
		ContactService userService = ctx.getBean(ContactService.class);
		
		Contact u = new Contact();
		u.setUserId(3);
		u.setPhone("9000011001");
		u.setAddress("B-44 Ghantaghar");
		u.setEmail("kreet@gmail.com");
		u.setName("ppp");
		u.setRemark("Good");
//		u.setContactId(4);
//		userDao.delete(4);
		userService.save(u);
		
//	   User u = userDao.findById(3);
//	   System.out.println(u.name +"  " + u.email);
//		List<User> u = userDao.findAll();
//		for(User us :u) {
//			System.out.println(us.loginName);
//		}
		
		System.out.println("-----------------kk--------");
	}
}
