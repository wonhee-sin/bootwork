package com.boot.service;

import java.util.List;

import com.boot.domain.Board;

public interface BoardService {
	
	//목록보기
	List<Board> getBoardList();
	
	//게시글 등록
	void insertBoard(Board board);
	
	Board getBoard(long seq);
	
	//게시글 업데이트
	void updateBoard(Board board);
	
	void deleteBoard(Board board);
	
	void updateCnt(Long seq);
}
