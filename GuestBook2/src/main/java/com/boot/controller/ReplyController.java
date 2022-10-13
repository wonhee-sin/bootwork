package com.boot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dto.ReplyDTO;
import com.boot.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replies/")
@RequiredArgsConstructor
public class ReplyController {
	
	private final ReplyService replyService;
	
	//@PathVariable - 경로를 변수로 처리해줌
	@GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno ){
		
		return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
	}
	
	//@RequestBody - 데이터를 자동으로 해당 타입의 객체로 매핑함
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
		Long rno = replyService.register(replyDTO);
		
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		replyService.remove(rno);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
		replyService.modify(replyDTO);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
