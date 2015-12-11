package com.alindus.iss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping
	public String home() {
		return "index";
	}
	@RequestMapping(value="/login")
	public String loginPage() {
		return "login";
	}
	
	/*@RequestMapping(value="/dashboard")
	public String dashboard() {
		return "dashboard";
	}*/
}
