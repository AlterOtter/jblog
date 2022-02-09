package com.poscoict.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.poscoict.config.DBConfig;
import com.poscoict.config.MybatisConfig;
import com.poscoict.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.jblog.service", "com.poscoict.jblog.repository"})
@Import({DBConfig.class,MybatisConfig.class})
public class AppConfig {

}
