package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.annotation.Auth;
import com.poscoict.annotation.Principal;
import com.poscoict.annotation.Referer;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService bservice;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable(value = "id") String user_id,Model model) {
		bservice.getblogAllinfo(user_id,model);
		return "blog/blog-main";
	}
	
	
	//블로그 대문 수정
	@Auth
	@RequestMapping(value="/{id}/admin/basic",method=RequestMethod.GET)
	public String get_basic() {
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value="/{id}/admin/basic",method=RequestMethod.POST)
	public String post_basic(@Principal UserVo uservo,
			@RequestParam(value="upload-image",required = false) MultipartFile multipartFile,
			@Referer String referer,
			BlogVo vo,Model model) {
		vo.setUser_id(uservo.getUser_id());
		bservice.updateBlog(vo,multipartFile);
		return "redirect:"+referer;
	}
	
	
	@Auth
	@RequestMapping(value="/{id}/admin/category",method=RequestMethod.GET)
	public String category(@Principal UserVo vo,Model model) {
		bservice.getcategory(vo,model);
		return "blog/blog-admin-category";
	}
	@Auth
	@RequestMapping(value="/{id}/admin/write",method=RequestMethod.GET)
	public String write() {
		return "blog/blog-admin-write";
	}
}
