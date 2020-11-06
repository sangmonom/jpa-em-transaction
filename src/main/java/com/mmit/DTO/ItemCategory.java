package com.mmit.DTO;

import com.mmit.entity.Item;

public class ItemCategory {
	private Item item;
	private String CategoryName;
	
	public ItemCategory(Item item, String categoryName) {
		super();
		this.item = item;
		CategoryName = categoryName;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
}
