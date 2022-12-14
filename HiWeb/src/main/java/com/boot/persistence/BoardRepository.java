package com.boot.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>,
QuerydslPredicateExecutor<Board>{

	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable pageable);
	
	//์กฐํ์
	@Modifying
	@Query("UPDATE Board b SET b.cnt = b.cnt + 1 WHERE b.seq = :seq")
	void updateCount(Long seq);
	
}
