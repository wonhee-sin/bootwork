package com.boot.service;

import com.boot.dto.BoardDTO;
import com.boot.dto.PageRequestDTO;
import com.boot.dto.PageResultDTO;
import com.boot.entity.Board;
import com.boot.entity.Member;

public interface BoardService {

	
	//게시글 등록
	Long register(BoardDTO dto);
	
	//게시글 목록 보기
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	
	//dto에서 Entity로 변환
	default Board dtoToEntity(BoardDTO dto) {
		//회원 생성
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		
		//게시글 작성
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		
		
		return board;
	}
	
	//Entity에서 dto로 변환
	default BoardDTO entityToDto(Board board, Member member, Long replyCount) {
		
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
		
		return boardDTO;
		
	}
}
