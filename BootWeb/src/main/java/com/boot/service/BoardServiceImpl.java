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
	private BoardRepository boardRepo;
	
	@Override
	public List<Board> getBoardList() {
		//findAll() 사용
		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "seq"));
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
		/*Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());*/
		boardRepo.save(board);	
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
		
	}

	@Transactional
	@Override
	public void updateCnt(Long seq) {
		boardRepo.updateCnt(seq); 
		
	}

}
