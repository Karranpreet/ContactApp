package com.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
	return new Class[] {
		SpringRootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		// TODO Auto-generated method stub
		return new Class[] {
				SpringWebConfig.class};
			}

	@Override
	protected String[] getServletMappings()  //front controller
	{
		// TODO Auto-generated method stub
		return new String[] {
				"/"
		};
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext); // imp must there
	}
	
	

}
