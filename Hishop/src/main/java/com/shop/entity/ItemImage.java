package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "item")
public class ItemImage extends BaseEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_image_id")
	private Long id;
	
	private String imageName;
	
	private String originImageName;

	private String imageUrl;
	
	private String representImageYN;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	public void updateItemImage(String imageName, String originImageName,
			String imageUrl) {
		this.imageName = imageName;
		this.originImageName = originImageName;
		this.imageUrl = imageUrl;
	}
}
