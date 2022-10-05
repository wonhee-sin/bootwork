package com.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRe;
	
	@Override
	public List<Board> getBoardList() {
		return (List<Board>) boardRe.findAll(Sort.by(Sort.Direction.DESC, "seq"));
	}

	@Override
	public void insertBoard(Board board) {
		boardRe.save(board);
		
	}

	@Override
	public Board getBoard(long seq) {
		return boardRe.findById(seq).get();
	}

	@Override
	public void updateBoard(Board board) {
		boardRe.save(board);
		
	}

	@Override
	public void deleteBoard(Board board) {
		boardRe.delete(board);
		
	}

	@Override
	@Transactional
	public void updateCnt(Long seq) {
		boardRe.updateCnt(seq);
		
	}

}
