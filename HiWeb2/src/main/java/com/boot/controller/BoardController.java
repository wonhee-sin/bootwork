package com.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.boot.dto.FileDto;
import com.boot.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	BoardService service;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardLiset = service.getBoardList();
		model.addAttribute("boardList", boardLiset);
		
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Model model, Long seq) {
		service.updateCount(seq);
		Board board = service.getBoard(seq);
		
		model.addAttribute("board", board);
		
		return "board/getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		
		return "redirect:/board/getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		
		return "redirect:/board/getBoardList";
	}
	
	//글쓰기 페이지 처리
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글쓰기 폼 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile[] uploadFile, @AuthenticationPrincipal SecurityUser principal) throws IllegalStateException, IOException {
		
		//파일 업로드
				//MultipartFile[]를 파라미터로 객체 사용
				for(MultipartFile file : uploadFile) {
					if(!file.isEmpty()) {
						//FileDto 객체 생성
						FileDto dto = new FileDto(UUID.randomUUID().toString(),
								file.getOriginalFilename(), file.getContentType());
						
						//파일 생성 - File 클래스의 객체는 논리적인 파일 이름임
						File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
						//실제 물리적인 파일로 전달해서 저장
						file.transferTo(newFileName);
					}
				}
				
		board.setMember(principal.getMember());
		service.insertBoard(board);
		return "redirect:/board/getBoardList";
	}
}
