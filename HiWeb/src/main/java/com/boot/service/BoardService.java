package com.boot.service;

import org.springframework.data.domain.Page;

import com.boot.domain.Board;
import com.boot.domain.Search;

public interface BoardService {

	Page<Board> getBoardList(Search search);	//게시글 목록(페이징 처리)
	
	void insetBoard(Board board);			//게시글 등록
	
	Board getBoard(Long seq);				//게시글 상세보기
	
	void updateBoard(Board board);			//게시글 수정
	
	void deleteBoard(Board board);			//게시글 삭제
	
	void updateCount(Long seq);				//조회수
	
}
