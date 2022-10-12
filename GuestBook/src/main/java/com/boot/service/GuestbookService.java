package com.boot.service;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDTO;
import com.boot.dto.PageResultDTO;
import com.boot.entity.Guestbook;


public interface GuestbookService {

	//게시글 등록
	Long register(GuestbookDto dto);
	
	PageResultDTO<GuestbookDto, Guestbook> getList(PageRequestDTO requestDTO);
	
	GuestbookDto read(Long gno);
	
	void modify(GuestbookDto dto);
	
	void remove(Long gno);
	
	
	
	
	default Guestbook dtoToEntity(GuestbookDto dto) {
		Guestbook entity = Guestbook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		return entity;
	}
	
	default GuestbookDto entityToDto(Guestbook entity) {
		GuestbookDto dto = GuestbookDto.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		return dto;
	}
}
