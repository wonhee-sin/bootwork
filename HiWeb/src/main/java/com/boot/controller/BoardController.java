package com.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.domain.Search;
import com.boot.dto.FileDto;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Board board, Model model, Search search) {
		if(search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if(search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		Page<Board> boardList = service.getBoardList(search);
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "board/insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile[] uploadFile,
			@AuthenticationPrincipal SecurityUser principal) throws IllegalStateException, IOException {
		//파일 업로드
		//MultipartFile 배열을 파라미터로 전달
		for(MultipartFile file : uploadFile){
			if(!file.isEmpty()) {
				FileDto dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
				
				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
				//전달된 내용을 실제 물리적은 파일로 저장해 줌
				file.transferTo(newFileName);
			}
		}
	
		//로그인 성공한 Member 객체를 가지고 있는 SecurityUser 객체를
		//매개 변수로 받음. 연관된 Member 엔티티를 Board 엔티티에 설정함
		board.setMember(principal.getMember());
		service.insetBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCount(seq);
		Board board = service.getBoard(seq);
		model.addAttribute(board);
		return "board/getBoard";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList";
	}
}
