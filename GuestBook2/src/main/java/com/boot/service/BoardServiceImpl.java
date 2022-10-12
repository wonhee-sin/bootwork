package com.boot.service;

import org.springframework.stereotype.Service;

import com.boot.dto.BoardDTO;
import com.boot.dto.PageRequestDTO;
import com.boot.dto.PageResultDTO;
import com.boot.entity.Board;
import com.boot.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	
	private final BoardRepository boardRepo;	//생성자 주입
	
	//게시글 등록
	@Override
	public Long register(BoardDTO dto) {
		Board board = dtoToEntity(dto);
		boardRepo.save(board);
		
		return board.getBno();
	}
	
	//목록 보기
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		
		return null;
	}

}
