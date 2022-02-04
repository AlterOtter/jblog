package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uservice;
	
	@RequestMapping(value= {"/login"} ,method=RequestMethod.GET)
	public String get_login() {
		return "user/login";
	}

	
	
	@RequestMapping(value= {"/join"}, method=RequestMethod.GET)
	public String get_join() {
		return "user/join";
	}
	
	@RequestMapping(value= {"/join"}, method=RequestMethod.POST)
	public String post_join(UserVo vo,Model model) {
		boolean result=uservice.joinService(vo);
		
		if(result) {
			return "redirect:/main";
		}else {
			model.addAttribute("UserVo",vo);
			return "user/join";
		}
	}
	
	@RequestMapping(value= {"/joinsucess"})
	public String join_success() {
		return "user/joinsuccess";
	}
	
}
