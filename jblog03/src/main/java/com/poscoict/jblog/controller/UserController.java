package com.poscoict.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value= {"","/main"})
	public String main() {
		System.out.println("WelCome!");
		return "main/index";
	}
	
}
