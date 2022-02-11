package com.poscoict.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poscoict.jblog.config.AppConfig;
import com.poscoict.jblog.config.WebConfig;


public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Service Repository Aspect
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {AppConfig.class};
	}
	

	//기본 url 설정
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {WebConfig.class};
	}
	
	@Override
	protected Filter[] getServletFilters() {
	        return new Filter[]{new CharacterEncodingFilter("UTF-8",false)};
	}
	
	


}
