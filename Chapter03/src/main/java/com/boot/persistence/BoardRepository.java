package com.boot.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	//쿼리 메소드 - 글 제목 검색
	List<Board> findByTitle(String searchKeyword);
	
	//특정 단어가 포함된 목록 검색
	List<Board> findByContentContaining(String searchKeyword);
	
	//제목 또는 내용에 특정 단어가 포함된 목록 검색
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	//글 제목에 특정 다너가 포함된 글 목록을 내림차순으로 조회
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(Pageable page);
	
	//List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
}
