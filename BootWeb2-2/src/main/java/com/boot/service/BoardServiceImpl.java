package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Override
	public List<Board> getBoardList() {
		return (List<Board>) boardRepo.findAll() ;
	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
		
	}

	@Override
	public Board getBoard(long seq) {
		return boardRepo.findById(seq).get();
	}

	@Override
	public void updateBoard(Board board) {
		boardRepo.save(board);
		
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
		
	}

}
