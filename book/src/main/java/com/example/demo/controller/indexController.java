package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;

@Controller
@Setter
public class indexController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
