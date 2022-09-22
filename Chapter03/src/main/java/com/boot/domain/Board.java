package com.boot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
public class Board {

	@Id@GeneratedValue
	private Long seq;
	
	private String title;
	private String writer;
	private String content;
	
	private Date createDate;
	private Long cnt;
}
