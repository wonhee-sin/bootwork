package com.boot.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDto {
	private long id;
	private String itemNm;
	private int price;
	private String itemDetail;
	private LocalDateTime regTime;
	
	public ItemDto() {}
	
	public ItemDto(long id, String itemNm, int price, String itemDetail, LocalDateTime regTime) {
		this.id = id;
		this.itemNm = itemNm;
		this.price = price;
		this.itemDetail = itemDetail;
		this.regTime = regTime;
	}
}
