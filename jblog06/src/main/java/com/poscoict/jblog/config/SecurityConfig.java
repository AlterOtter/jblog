package com.poscoict.jblog.config;

import java.util.List;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.jblog.arguementresolver.PrincipalArguementResolver;
import com.poscoict.jblog.arguementresolver.RefererResolver;
import com.poscoict.jblog.interceptor.BlogInterceptor;
import com.poscoict.jblog.interceptor.LoginInterceptor;
import com.poscoict.jblog.interceptor.LogoutInterceptor;

@SpringBootConfiguration
public class SecurityConfig implements WebMvcConfigurer{



	//Arguement Resolver
	
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		
		return new PrincipalArguementResolver();
	}
	
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver02() {
		
		return new RefererResolver();
	}
	
	
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argument) {
		argument.add(handlerMethodArgumentResolver());
		argument.add(handlerMethodArgumentResolver02());
	}
	
	
	//Interceptor
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	//Interceptor
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	
	@Bean
	public HandlerInterceptor blogInterceptor() {
		return new BlogInterceptor();
	}

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/auth");
		registry.addInterceptor(logoutInterceptor()).addPathPatterns("/user/logout");
		registry.addInterceptor(blogInterceptor()).addPathPatterns("/blog/**");
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:/upload-images/");
	}

}