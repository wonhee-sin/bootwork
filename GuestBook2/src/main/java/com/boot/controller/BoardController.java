package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.BoardDTO;
import com.boot.dto.PageRequestDTO;
import com.boot.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;	//자동 주입을 위해 final
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		model.addAttribute("result", boardService.getList(pageRequestDTO));
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		//새로 추가된 엔티티의 번호
		Long bno = boardService.register(dto);
		redirectAttributes.addFlashAttribute("msg",bno);
		return "redirect:/board/list";
	}
	
	@GetMapping({"/read","/modify"})
	public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model) {
		BoardDTO boardDTO = boardService.get(bno);
		model.addAttribute("dto", boardDTO);
	}
	
	@PostMapping("/remove")
	public String remove(long bno, RedirectAttributes redirectAttributes) {
		boardService.removeWithReplies(bno);
		redirectAttributes.addFlashAttribute("msg", bno);
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO dto,
						 @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
						 RedirectAttributes redirectAttributes) {
		boardService.modify(dto);
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("type", requestDTO.getType());
		redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
		
		redirectAttributes.addAttribute("bno",dto.getBno());
		
		return "redirect:/board/read";
	}
	
}
