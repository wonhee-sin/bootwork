package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
		return "/result";
	}
	
	//정보 보기 로그인 페이지 이동
	@GetMapping("/userInfoLogin")
	public void userInfoLogin() {}
	
	//정보 보기 로그인 처리
	@PostMapping("/userInfoLogin")
	public String userInfoLoginProc(@AuthenticationPrincipal SecurityUser principal, Member checkMember) {
		Member member = principal.getMember();
		String checkPassword = checkMember.getPassword();
		boolean checkPw = service.checkPassword(member, checkPassword);
		if(checkPw) {
			return "redirect:/member/userInfoPage";
		}else {
			return "redirect:/member/userInfoLogin";
		}
	}
	
	
	//정보 보기 페이지 처리
	@RequestMapping("/userInfoPage")
	public void userInfo(Model model, @AuthenticationPrincipal SecurityUser principal) {
		Member member = principal.getMember();
		model.addAttribute("member", member);
	}
	
	//정보 수정 처리
	@PostMapping("/userInfo")
	public String userInfo(Member member) {
		service.userInfo(member);
		return "redirect:/member/logout";
	}
	
	//회원 삭제
	@RequestMapping("/deleteAccount")
	public String deleteAccount(@AuthenticationPrincipal SecurityUser principal) {
		Member member = principal.getMember();
		service.deleteAccount(member);
		return "redirect:/member/logout";
	}
}
