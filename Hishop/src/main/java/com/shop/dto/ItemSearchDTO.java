package com.shop.dto;

import lombok.Setter;

import com.shop.constant.ItemSellStatus;

import lombok.Getter;

@Getter
@Setter
public class ItemSearchDTO {

	private String searchDateType;	//현재 시간과 등록일ㅇ르 비교해서 상품 조회
	
	private ItemSellStatus searchSellStatus;	//상품 판매 상태를 기준으로 조회
	
	private String searchBy;	//상품 조회시 어떤 유형으로 조회할지 선택
	
	private String searchQuery = ""; //조회할 검색어 저장할 변수
}
