package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	//private final GuestbookService service;
	
	@GetMapping("/")
	public String main() {
		return "Main";
	}
	
}
