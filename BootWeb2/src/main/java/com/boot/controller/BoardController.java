package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/")
	public String home() {
		return "/index";
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "getBoardList";
	}
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member) {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		service.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getboard(Long seq, Model model) {
		service.updateCnt(seq);
		Board board = service.getBoard(seq);
		model.addAttribute("board", board);
		
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
