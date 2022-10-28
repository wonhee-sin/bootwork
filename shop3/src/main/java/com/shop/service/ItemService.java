package com.shop.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
//import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ItemService {
	private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    //상품 등록(상품, 이미지 리스트) 메서드
    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 등록
        Item item = itemFormDto.createItem();  //item 객체 생성
        itemRepository.save(item);

        //이미지 등록
        for(int i=0; i<itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)  //첫번째 이미지일 경우
                itemImg.setRepimgYn("Y"); //대표 이미지를 "Y"로 설정
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }
    
    //상품 조회
    @Transactional(readOnly = true)  //트랜잭션 읽기 전용
    public ItemFormDto getItemDtl(Long itemId){
    	//해당 상품의 이미지 조회
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        //조회한 ItemImg 엔티티를 ItemImgDto 객체로 만들어서 리스트에 추가
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        //상품의 아이디로 상품의 엔티티 조회.. 존재하지 않을 때는 EntityNotFoundException 발생
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }
    
    //상품 수정
    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
    	//상품 등록 화면으로부터 전달 받은 상품 아이디를 이용하여 상품 엔티티를 조회함
    	Item item = itemRepository.findById(itemFormDto.getId())
    			.orElseThrow(EntityNotFoundException::new);
    	item.updateItem(itemFormDto); //상품 엔티티 업데이트 함
    	
    	List<Long> itemImgIds = itemFormDto.getItemImgIds(); //상품 이미지 리스트를 조회
    	
    	//상품 이미지 아이디와 상품 이미지 파일 리스트를 파라미터로 전달함
    	for(int i=0; i<itemImgFileList.size(); i++) {
    		itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
    	}
    	
    	return item.getId();
    }
    
    //상품 데이터 조회
    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
    	return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }
    
    //메인 페이지 상품 데이터 조회
    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }
    
}










