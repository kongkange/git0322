package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Service
@Setter
public class MemberService implements UserDetailsService{
	@Autowired
	private MemberDAO dao;
	
	public Member findById(String id) {
		return dao.findById(id).get();
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;
		try {
			Member m = dao.findById(username).get();
			if(m==null) {
				throw new UsernameNotFoundException(username);
			}else {
				user = User.builder().username(username)
						.password(m.getPwd())
						.roles(m.getRole())
						.build();
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return user;
	}
}
