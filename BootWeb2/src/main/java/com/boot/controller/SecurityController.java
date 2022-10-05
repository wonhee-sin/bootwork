package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes("member")
public class SecurityController {
	
	@GetMapping("/login")
	public void login() {
		log.info("login 요청입니다.");
	}
}
