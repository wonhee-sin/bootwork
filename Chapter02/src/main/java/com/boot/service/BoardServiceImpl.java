package com.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Override
	public String Hello(String name) {
		return "hello: " + name;
	}

	@Override
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목...");
		board.setWriter("tester");
		board.setContent("teest contents");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("테스트 제목..." + i);
			board.setWriter("tester");
			board.setContent(i + "test contents");
			board.setCreateDate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		
		return boardList;
	}

}
