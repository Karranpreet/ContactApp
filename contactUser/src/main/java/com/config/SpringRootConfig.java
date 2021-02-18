package com.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.dao","com.service"})
public class SpringRootConfig {

@Bean
public BasicDataSource getDataSource(){
	        BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        ds.setUrl("jdbc:mysql://127.0.0.1:3306/capp_db");
	        ds.setUsername("root");
	        ds.setPassword("Karan@123");
	        ds.setMaxTotal(2);
	        ds.setInitialSize(1);
	        ds.setTestOnBorrow(true);
	        ds.setValidationQuery("SELECT 1");
	        ds.setDefaultAutoCommit(true);
	        return ds;
	    }
}
