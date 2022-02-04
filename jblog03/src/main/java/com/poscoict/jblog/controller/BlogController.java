package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscoict.jblog.service.BlogService;

@RequestMapping("/blog")
@Controller
public class BlogController {
	
	@Autowired
	private BlogService bservice;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable(value = "id") String user_id,Model model) {
		bservice.getbloginfo(user_id,model);
		return "blog/blog-main";
	}
	
	
	//블로그 대문 수정
	@RequestMapping("/basic")
	public String basic() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/category")
	public String category() {
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("write")
	public String write() {
		return "blog/blog-admin-write";
	}
}
