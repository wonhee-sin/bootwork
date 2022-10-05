package com.boot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boot.domain.Board;
import com.boot.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = new ArrayList<>();
		
		for(int i=0; i<=10; i++) {
			Board board = new Board();
			board.setSeq((long)i);
			board.setTitle("게시판 프로그램 테스트");
			board.setWriter("아기상어");
			board.setContent("게시판 프로그램 테스트입니다.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			boardList.add(board);
		}
		model.addAttribute("boardList", boardList);
		
		return "getBoardList";
	}
	
	@GetMapping("/getBoardList02")
	public String getBoardList02(Model model) {
		List<Board> b =  service.getBoardList();
		model.addAttribute("boardList", b);
		return "getBoardList02";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		service.insertBoard(board);
		return "redirect:getBoardList02";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCnt(seq);
		Board board = service.getBoard(seq);
		model.addAttribute(board);
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList02";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList02";
	}
}
