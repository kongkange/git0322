package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.BookDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Book;
import com.example.demo.entity.Member;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class BookController {

	@Autowired
	private BookDAO dao;
	
	@Autowired
	private MemberDAO mdao;
	
	@GetMapping("/listBook")
	public void list(HttpSession session, Model model, String category, String keyword) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String loginId = user.getUsername();
		Member m = mdao.findById(loginId).get();
		session.setAttribute("m", m);
		
		String key = null;
		key = keyword;
		if(key == null || key.equals("")) {
			model.addAttribute("list", dao.findAll());
		}else {
			model.addAttribute("list", dao.selectBook(category, keyword));			
		}
	}
	
	@GetMapping("/insertBook")
	public void insertForm() {
	}
	
	@PostMapping("/insertBook")
	public String insertSub(Book b) {
		b.setBookid(dao.getNextBookid());
		dao.save(b);
		return "redirect:/listBook";
	}
	
	@GetMapping("/updateBook/{bookid}")
	public String updateForm(@PathVariable("bookid") int bookid, Model model) {
		model.addAttribute("b", dao.findById(bookid).get());
		return "/updateBook";
	}
	
	@PostMapping("/updateBook")
	public String updateSub(Book b) {
		dao.save(b);
		return "redirect:/listBook";
	}
	
	@GetMapping("/deleteBook/{bookid}")
	public String delete(@PathVariable("bookid") int bookid) {
		dao.deleteById(bookid);
		return "redirect:/listBook";
	}
	
}
