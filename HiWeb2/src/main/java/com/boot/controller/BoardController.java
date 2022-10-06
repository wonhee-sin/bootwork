package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardLiset = service.getBoardList();
		model.addAttribute("boardList", boardLiset);
		
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Model model, Long seq) {
		service.updateCount(seq);
		Board board = service.getBoard(seq);
		
		model.addAttribute("board", board);
		
		return "board/getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		
		return "redirect:/board/getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		
		return "redirect:/board/getBoardList";
	}
	
	//글쓰기 페이지 처리
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글쓰기 폼 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		service.insertBoard(board);
		return "redirect:/board/getBoardList";
	}
}
