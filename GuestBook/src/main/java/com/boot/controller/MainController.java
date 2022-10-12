package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDTO;
import com.boot.service.GuestbookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MainController {
	
	private final GuestbookService service;
	
	@GetMapping("/")
	public String main() {
		return "Main";
	}
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
		log.info("list............" + pageRequestDTO);
		model.addAttribute("result",service.getList(pageRequestDTO));
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("register get...");
	}
	
	@PostMapping("/register")
	public String registerPost(GuestbookDto dto, RedirectAttributes redirectAttributes) {
		log.info("dto..." + dto);
		//새로 추가된 엔티티의 번호
		Long gno = service.register(dto);
		redirectAttributes.addFlashAttribute("msg",gno);
		return "redirect:/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("gno: " + gno);
		
		GuestbookDto dto = service.read(gno);
		
		model.addAttribute("dto", dto);
		
	}
	
	@PostMapping("/remove")
	public String remove(long gno, RedirectAttributes redirectAttributes) {
		log.info("gno: " + gno);
		
		service.remove(gno);
		
		redirectAttributes.addFlashAttribute("msg", gno);
		
		return "redirect:/list";
	}
	
	@PostMapping("/modify")
	public String modify(GuestbookDto dto,
						 @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
						 RedirectAttributes redirectAttributes) {
		log.info("post modify........................");
		log.info("dto: " +dto);
		
		service.modify(dto);
		
		redirectAttributes.addAttribute("page",requestDTO.getPage());
		redirectAttributes.addAttribute("gno",dto.getGno());
		
		return "redirect:/read";
	}
	
}
