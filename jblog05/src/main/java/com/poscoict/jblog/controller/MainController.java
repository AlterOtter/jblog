package com.poscoict.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value= {"/main"})
public class MainController {

	@RequestMapping(value= {""})
	public String main() {
		return "main/index";
	}
}
