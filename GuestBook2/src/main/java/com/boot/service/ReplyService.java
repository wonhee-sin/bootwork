package com.boot.service;

import java.util.List;

import com.boot.dto.ReplyDTO;
import com.boot.entity.Board;
import com.boot.entity.Reply;

public interface ReplyService {

	default Reply dtoToEntity(ReplyDTO replyDTO) {
		Board board = Board.builder().bno(replyDTO.getBno()).build();
		
		Reply reply = Reply.builder()
				.rno(replyDTO.getRno())
				.text(replyDTO.getText())
				.replyer(replyDTO.getReplyer())
				.board(board)
				.build();
		return reply;
	}
	
	default ReplyDTO entityToDto(Reply reply) {
		ReplyDTO dto = ReplyDTO.builder()
				.rno(reply.getRno())
				.text(reply.getText())
				.replyer(reply.getReplyer())
				.regDate(reply.getRegDate())
				.modDate(reply.getModDate())
				.build();
		
		return dto;
	}
	
	Long register(ReplyDTO replyDTO);	//댓글의 등록
	
	List<ReplyDTO> getList(Long bno);	//특정 게시물의 댓글 목록
	
	void modify(ReplyDTO replyDTO);		//댓글 수정
	
	void remove(Long rno);				//댓글 삭제
}
