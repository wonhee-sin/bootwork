/*package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.boot.domain.Member;
import com.boot.service.MemberService;

//Model 객체 = 'member'을 자동으로 세션에 저장함
@SessionAttributes("member")
@Controller
public class LoginController {
	
	@Autowired
	private MemberService service;
	
	//로그인 페이지 요청
	@GetMapping("/login")
	public void loginView() {
			
	}
	
	//로그인 인증 처리
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = service.getMember(member);
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);	//세션 발급
			return "redirect:getBoardList";
		}else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, SessionStatus sessionStatus) {
		session.invalidate();
		sessionStatus.setComplete();
		return "redirect:";
	}
}*/
