package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = {"com"})
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {

	public void addResourceHandlers(ResourceHandlerRegistry registry) // add static files js css
	{
		
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/static/**").addResourceLocations("/static/"); //you can access theses files on web
	}
	
	@Bean
	public ViewResolver viewResolver() //to access private files web-inf
	{
		InternalResourceViewResolver vs = new InternalResourceViewResolver();
		vs.setViewClass(JstlView.class);
		vs.setPrefix("/WEB-INF/view/");
		vs.setSuffix(".jsp");
		return vs;
	}

}
