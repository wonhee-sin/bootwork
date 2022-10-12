package com.boot.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDTO;
import com.boot.dto.PageResultDTO;
import com.boot.entity.Guestbook;
import com.boot.entity.QGuestbook;
import com.boot.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {
	
	private final GuestBookRepository repository;
	
	@Override
	public Long register(GuestbookDto dto) {
		log.info("DTO---------------------");
		log.info(dto);
		Guestbook entity = dtoToEntity(dto);
		log.info(entity);
		repository.save(entity);
		return entity.getGno();
	}

	@Override
	public PageResultDTO<GuestbookDto, Guestbook> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		
		BooleanBuilder booleanBuilder = getSearch(requestDTO);
		
		Page<Guestbook> result = repository.findAll(booleanBuilder, pageable);
		
		Function<Guestbook, GuestbookDto> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<>(result, fn);
	}
	
	

	@Override
	public GuestbookDto read(Long gno) {
		Optional<Guestbook> result = repository.findById(gno);
		
		return result.isPresent()? entityToDto(result.get()): null;
	}

	@Override
	public void modify(GuestbookDto dto) {
		Optional<Guestbook> result = repository.findById(dto.getGno());
		
		if(result.isPresent()) {
			Guestbook entity = result.get();
			
			entity.changeTitle(dto.getTitle());
			entity.changeContent(dto.getContent());
			
			repository.save(entity);
		}
		
	}

	@Override
	public void remove(Long gno) {
		repository.deleteById(gno);
		
	}
	
	
private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
	String type = requestDTO.getType();
	
	BooleanBuilder booleanBuilder = new BooleanBuilder();
	
	if(type == null || type.trim().length() == 0) {
		return booleanBuilder;
	}
	
	QGuestbook qGuestbook = QGuestbook.guestbook;
	
	String keyword = requestDTO.getKeyword();
	
	BooleanExpression expression = qGuestbook.gno.gt(0L);
	
	booleanBuilder.and(expression);
	

	
	BooleanBuilder conditionBuilder = new BooleanBuilder();
	
	if(type.contains("t")) {
		conditionBuilder.or(qGuestbook.title.contains(keyword));
	}
	if(type.contains("c")) {
		conditionBuilder.or(qGuestbook.content.contains(keyword));
	}
	if(type.contains("w")) {
		conditionBuilder.or(qGuestbook.writer.contains(keyword));
	}
	
	booleanBuilder.and(conditionBuilder);
	
	return booleanBuilder;
}

}


