package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Controller
@Setter
public class LoginController {
	@Autowired
	private MemberDAO dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping("/login")
	public void login() {
	}
	@GetMapping("/join")
	public void joinForm() {
	}
	@PostMapping("/join")
	public String join(Member m) {
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		dao.save(m);
		return "/login";
	}
	@GetMapping("/loginOk")
	public void ok() {}
}
