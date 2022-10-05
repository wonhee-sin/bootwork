package com.boot.domain;

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
@Setter
@Getter
@Entity
public class Board {

	@Id@GeneratedValue
	private Long seq;
	
	private String title;
	//private String writer;
	private String content;
	
	@Column(updatable=false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private Date createDate;
	
	@Column(updatable=false, columnDefinition = "bigint DEFAULT 0" )
	private Long cnt = 0L;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
}
