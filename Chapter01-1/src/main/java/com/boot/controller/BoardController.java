package com.boot.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.domain.BoardVO;

@RestController	//view(jsp)를 만들지 않고 문자열이 그대로 출력됨
public class BoardController {
	
	
	public BoardController() {
		System.out.println("===> BoardController 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("test title");
		board.setWriter("tester");
		board.setContent("한글확인");
		board.setCreateDate(new Date());
		board.setCnt(0);
		
		return board;
	}
	//컬렉션(리스트) 리턴
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList(){
		List<BoardVO> boardList = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("test title" + i);
			board.setWriter("tester");
			board.setContent("한글확인" + i);
			board.setCreateDate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		
		return boardList;
	}
		
	
}
