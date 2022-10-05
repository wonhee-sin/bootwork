package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.domain.Member;
import com.boot.service.MemberService;

@Controller
public class SecurityController {
	
	@Autowired
	private MemberService service;
	
	//로그인
	@GetMapping("/system/login")
	public void login() {}
	
	//회원가입 폼 요청
	@GetMapping("/system/signup")
	public void signup() {}
	
	//회원가입 처리
	@PostMapping("/system/signup")
	public String signup(Member member) {
		service.signup(member);
		return "redirect:/";
	}
	
	//접근 권한 없음 페이지
	@GetMapping("/system/accessDenied")
	public void accessDenied() {}
	
	//로그아웃
	@GetMapping("/system/logout")
	public void logout() {}
	
	//관리자 페이지
	@GetMapping("/admin/adminPage")
	public void admin() {}
}
