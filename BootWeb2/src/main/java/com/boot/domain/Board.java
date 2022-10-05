package com.boot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Board {
	@Id @GeneratedValue
	private Long seq;
	
	private String title;
	
	@Column(updatable=false)
	private String writer;
	private String content;
	
	@Column(insertable=false, updatable=false, columnDefinition="timestamp default current_timestamp")
	private Date createDate;
	
	@Column(insertable=false, updatable=false, columnDefinition="bigint default 0")
	private Long cnt;
	
}
