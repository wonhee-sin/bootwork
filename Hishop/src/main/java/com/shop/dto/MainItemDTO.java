package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDTO {

	private Long id;
	
	private String itemName;
	
	private String itemDetail;
	
	private String imageUrl;
	
	private Integer price;
	
	@QueryProjection
	public MainItemDTO(Long id, String itemName, String itemDetail, String imageUrl, Integer price) {
		this.id = id;
		this.itemName = itemName;
		this.itemDetail = itemDetail;
		this.imageUrl = imageUrl;
		this.price = price;
	}
}
