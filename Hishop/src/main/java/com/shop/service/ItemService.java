package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.ItemDTO;
import com.shop.dto.ItemImageDTO;
import com.shop.dto.ItemSearchDTO;
import com.shop.dto.MainItemDTO;
import com.shop.entity.Item;
import com.shop.entity.ItemImage;
import com.shop.repository.ItemImageRepository;
import com.shop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	private final ItemImageService itemImageService;
	
	private final ItemImageRepository itemImageRepository;
	
	//상품 등록(상품, 이미지 리스트) 메서드
	public Long saveItem(ItemDTO itemDTO, List<MultipartFile> itemImageFileList) throws Exception {
		
		//상품 등록
		Item item = itemDTO.createItem();	//item 객체 생성
		itemRepository.save(item);
		
		//이미지 등록
		for(int i=0; i<itemImageFileList.size(); i++) {
			ItemImage itemImage = new ItemImage();
			itemImage.setItem(item);
			
			if(i == 0) {	//첫번째 이미지일 경우
				itemImage.setRepresentImageYN("Y");		//대표 이미지를 Y로 설정
			}else {
				itemImage.setRepresentImageYN("N");
			}
			
			itemImageService.saveItemImage(itemImage, itemImageFileList.get(i));
		}
		
		return item.getId();
	}
	
	//상품 조회 
	@Transactional(readOnly = true)	//트랜젝션 읽기 전용
	public ItemDTO getItemDTL(Long ItemId) {
		//해당 상품의 이미지 조회
		List<ItemImage> itemImageList = itemImageRepository.findByItemIdOrderByIdAsc(ItemId);
		//조회한 itemimage 엔티티를 dto객체로 만들어서 리스트에 추가
		List<ItemImageDTO> itemImageDTOList = new ArrayList<>();
		for(ItemImage itemImage : itemImageList) {
			ItemImageDTO itemImageDTO = ItemImageDTO.of(itemImage);
			itemImageDTOList.add(itemImageDTO);
		}
		
		//상품의 아이디로 상품의 엔티티조회.. 존재하지 않을 때는 EntityNotFoundException 발생
		Item item = itemRepository.findById(ItemId)
				.orElseThrow(EntityNotFoundException::new);
		ItemDTO itemDTO = ItemDTO.of(item);
		itemDTO.setItemImageDTOList(itemImageDTOList);
		return itemDTO;
	}
	
	//상품 수정
	public Long updateItem(ItemDTO itemDTO, List<MultipartFile> itemImageFileList) throws Exception {
		//상품 등록 화면으로부터 전달 받은 상품 아이디를 이용하여 상품 엔티티를 조회함
		Item item = itemRepository.findById(itemDTO.getId())
				.orElseThrow(EntityNotFoundException::new);
		item.updateItem(itemDTO);	//상품 엔티티 업데이트 함
		
		List<Long> itemImageIds = itemDTO.getItemImageIds();	//상품 이미지 리스트를 조회
		
		//상품 이미지 아이디와 상품 이미지 파일 리스트를 파라미터로 전달함
		for(int i=0; i<itemImageFileList.size(); i++) {
			itemImageService.updateItemImg(itemImageIds.get(i), itemImageFileList.get(i));
		}
		
		return item.getId();
	}
	
	//상품 데이터 조회
	@Transactional(readOnly = true)
	public Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable){
		return itemRepository.getAdminItemPage(itemSearchDTO, pageable);
	}
	
	//메인 페이지 상품 데이터 조회
	@Transactional(readOnly = true)
	public Page<MainItemDTO> getMainItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable){
		return itemRepository.getMainItemPage(itemSearchDTO, pageable);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
