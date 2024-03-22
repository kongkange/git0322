package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.Setter;

@Configuration
@EnableWebSecurity
@Setter
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
		//전체권한
		.requestMatchers("/","/login","/join").permitAll()
		//admin권한
		.requestMatchers("/admin/**","/auth/**").hasRole("admin")
		.requestMatchers("/auth/**","/user/**").hasRole("user")
		//user권한
//		.requestMatchers("/auth/**","/user/**").hasRole("user")
		.anyRequest().authenticated();
		
		//login권한
		http.formLogin()
		.loginPage("/login")
		.permitAll()
		.defaultSuccessUrl("/listBook");
		
		//logout권한
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/index");
		//바로 인증, 보안절차 생략코드
		http.httpBasic();
		
		return http.build();
	}
}
