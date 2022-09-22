package com.boot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.domain.BoardVO;
import com.boot.service.BoardService;

@RestController	//view(jsp)를 만들지 않고 문자열이 그대로 출력됨
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	public BoardController() {
		System.out.println("===> BoardController 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return service.Hello(name);
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = service.getBoard();
		
		return board;
	}
	//컬렉션(리스트) 리턴
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList(){
		List<BoardVO> boardList = service.getBoardList();
		
		return boardList;
	}
		
	
}
