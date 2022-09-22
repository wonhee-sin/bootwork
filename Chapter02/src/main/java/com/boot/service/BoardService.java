package com.boot.service;


import java.util.List;

import com.boot.domain.BoardVO;

public interface BoardService {
	
	String Hello(String name);
	
	BoardVO getBoard();
	
	List<BoardVO> getBoardList();
	
}
