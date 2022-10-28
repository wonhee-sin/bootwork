package com.shop.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.shop.entity.ItemImage;
import com.shop.repository.ItemImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemImageService {

	//application.properties에 설정한 itemimagelocation 프로퍼티 값을읽어옴
	@Value("${itemImageLocation}")
	private String itemImageLocation;
	
	private final ItemImageRepository repository;
	
	private final FileService fileService;
	
	//상품 이미지 저장
	public void saveItemImage(ItemImage itemImage, MultipartFile itemImageFile) throws Exception {
		String originImageName = itemImageFile.getOriginalFilename();
		String imageName = "";
		String imageUrl = "";
		
		//파일 업로드
		if(!StringUtils.isEmpty(originImageName)) {
			imageName = fileService.uploadFile(itemImageLocation, originImageName, itemImageFile.getBytes());
			imageUrl = "/images/item/" +  imageName;
		}
		
		//상품 이미지 정보 저장
		itemImage.updateItemImage(imageName,originImageName, imageUrl);
		repository.save(itemImage);
	}
	
    //상품 이미지 수정
    public void updateItemImg(Long itemImageId, MultipartFile itemImageFile) throws Exception{
    	//상품 이미지 아이디를 이용하여 상품 이미지 엔티티를 조회
        if(!itemImageFile.isEmpty()){
            ItemImage savedItemImg = repository.findById(itemImageId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImageName())) {
                fileService.deleteFile(itemImageLocation+"/"+
                        savedItemImg.getImageName());
            }

            //업데이트한 상품 이미지 파일을 업로드함
            String originImageName = itemImageFile.getOriginalFilename();
            String imageName = fileService.uploadFile(itemImageLocation, originImageName, itemImageFile.getBytes());
            String imageUrl = "/images/item/" + imageName;
            savedItemImg.updateItemImage( imageName, originImageName, imageUrl);
        }
    }
}
