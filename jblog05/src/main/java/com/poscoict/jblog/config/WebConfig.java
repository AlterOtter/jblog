package com.poscoict.jblog.config;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.config.web.FileUploadConfig;
import com.poscoict.config.web.MessageConfig;
import com.poscoict.config.web.MvcConfig;
import com.poscoict.config.web.SecurityConfig;
import com.poscoict.jblog.interceptor.BlogInterceptor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.jblog.controller"})
@Import({MvcConfig.class,MessageConfig.class,SecurityConfig.class,FileUploadConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter{
	@Bean
	public HandlerInterceptor bLogInterceptor() {
		return new BlogInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(bLogInterceptor())
				.addPathPatterns("/blog/**");
	}
	
	
	@ControllerAdvice
	public class GlobalExceptionHandler {

		@ExceptionHandler(Exception.class)
		public String ExceptionHandler(Model model,Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			model.addAttribute("exception", errors.toString());
			
			
			//2. 사과 페이지(HTML 응답, 정상 종료)
			return "error/exception";
		}
	}

}
