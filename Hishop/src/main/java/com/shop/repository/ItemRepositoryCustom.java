package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.dto.ItemSearchDTO;
import com.shop.dto.MainItemDTO;
import com.shop.entity.Item;

public interface ItemRepositoryCustom {
	
	//검색과 페이지 처리 리스트
	Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable);
	
	//메인 페이지에서 보여줄 상품 리스트
	Page<MainItemDTO> getMainItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable);
}
