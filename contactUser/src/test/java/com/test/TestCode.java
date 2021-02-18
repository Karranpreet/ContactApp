package com.test;



import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.config.SpringRootConfig;

public class TestCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
   DataSource ds = ctx.getBean(DataSource.class); //BasicDataSource(Apache class) imp DataSource
   JdbcTemplate jt = new JdbcTemplate(ds);
   
   String sql = "Insert into user(name,phone, email, address, loginName, password) VALUES(?,?,?,?,?,?)";
	Object[] param = new Object[] {"Karan","9810081594","karan@gmail.com","B-44 Hari Nagar","Singh","Karan@123"};
	jt.update(sql,param);
	System.out.println("executed");
	((AnnotationConfigApplicationContext) ctx).close();
	}

}
