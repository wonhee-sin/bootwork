package com.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.boot.domain.Board;
import com.boot.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Override
	public List<Board> getBoardList() {
		
		List<Board> boardList = boardRepo.findAll(Sort.by(Sort.Direction.DESC, "seq"));
		
		return boardList;
	}

	@Override
	public Board getBoard(Long seq) {
		Board board = boardRepo.findById(seq).get();
		return board;
	}

	@Override
	public void updateBoard(Board board) {
		boardRepo.save(board);
		
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
		
	}

	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
		
	}
	
	@Transactional
	@Override
	public void updateCount(Long seq) {
		boardRepo.updateCount(seq);;
		
	}

}
