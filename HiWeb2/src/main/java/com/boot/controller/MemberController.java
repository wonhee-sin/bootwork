package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
import com.boot.domain.Member;
import com.boot.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//로그인
	@GetMapping("/login")
	public void login() {}		//tamplates에 /member/login.html
	
	//로그아웃
	@GetMapping("/logout")
	public void logout() {}
	
	//회원가입 페이지 요청
	@GetMapping("/signUp")
	public void signUp() {}
	
	//회원가입 처리
	@PostMapping("/signUp")
	public String signUp(Member member) {
		service.signUp(member);
		return "redirect:/member/login";
	}
	
	//정보 보기 페이지 처리
	@GetMapping("/userInfo")
	public void userInfo(Model model, @AuthenticationPrincipal SecurityUser principal) {
		Member member = principal.getMember();
		model.addAttribute("member", member);
	}
	
	//정보 수정 처리
	@PostMapping("/userInfo")
	public String userInfo(Member member) {
		return "redirect:/";
	}
}
