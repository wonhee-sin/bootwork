package com.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.MemberDTO;
import com.shop.entity.Member;
import com.shop.service.MemberService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {
	
	private final MemberService service;
	
	private final PasswordEncoder passenc;
	
	//로그인 폼 요청
	@GetMapping("/login")
	public String lginForm() {
		return "member/loginForm";
	}
	
	//로그인 실패
	@GetMapping("/login/error")
	public String lginError(Model model) {
		model.addAttribute("msg","아이디나 비밀번호를 확인해 주세요");
		return "member/loginForm";
	}
	
	//회원 가입 폼 요청
	@GetMapping("/new")
	public String memberForm(Model model) {
		model.addAttribute("dto", new MemberDTO());
		return "member/memberForm";
	}
	
	//회원 가입 처리
	//@Valid - 유효성 검증 어노테이션
	@PostMapping("/new")
	public String memberForm(@Valid @ModelAttribute("dto") MemberDTO dto, 
			 BindingResult bindingResult , Model model) {
		//유효성 검증
		if(bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		//이메일 중복 처리
		try {
			Member member = Member.createMember(dto, passenc);
			service.saveMember(member);
		}catch(IllegalStateException e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "member/memberForm";
		}
		return "redirect:/";
	}
	
	
}
