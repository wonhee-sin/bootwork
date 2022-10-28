package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.ItemImage;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
	
	List<ItemImage> findByItemIdOrderByIdAsc(Long itemId);
	
	ItemImage findByItemIdAndRepresentImageYN(Long itemId, String representImageYN);

}
