package com.boot.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude="member")
@Getter
@Setter
@Entity
public class Board implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long seq;
	
	private String title;
	
	private String content;
	
	@Column(updatable=false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createDate;
	
	@Column(updatable=false, columnDefinition = "bigint DEFAULT 0")
	private Long cnt = 0L;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false)
	private Member member;
	
}
