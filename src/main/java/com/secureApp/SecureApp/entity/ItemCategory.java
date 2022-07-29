package com.secureApp.SecureApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author Ankit Mohapatra
 * 
 */

@Entity
@Table(name="item_category")
public class ItemCategory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long category_id;
	
	
	private String category;
	private String shop_location;
	
	
	private int shop_location_id;

//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<ItemEntity> items;
	


	
	
	
	
	
	public long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getShop_location_id() {
		return shop_location_id;
	}
	public void setShop_location_id(int shop_location_id) {
		this.shop_location_id = shop_location_id;
	}
	public String getShop_location() {
		return shop_location;
	}
	public void setShop_location(String shop_location) {
		this.shop_location = shop_location;
	}
	
	public ItemCategory(long category_id, String category, String shop_location, int shop_location_id) {
		super();
		this.category_id = category_id;
		this.category = category;
		this.shop_location = shop_location;
		this.shop_location_id = shop_location_id;
		
	}
	public ItemCategory() {
		super();
	}
	@Override
	public String toString() {
		return "ItemCategory [category_id=" + category_id + ", category=" + category + ", shop_location="
				+ shop_location + ", shop_location_id=" + shop_location_id + "]";
	}
	
}
