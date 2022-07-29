package com.secureApp.SecureApp.entity;

import javax.persistence.Column;
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
@Table(name = "shop_location")
public class ShopLocationEntity {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shop_location_id;
	
	
	@Column(length = 150)
	private String shop_location;

	

	public ShopLocationEntity() {
		super();
	}










	@Override
	public String toString() {
		return "ShopLocationEntity [shop_location_id=" + shop_location_id + ", shop_location=" + shop_location
				+ "]";
	}





	public ShopLocationEntity(int shop_location_id, String shop_location) {
		super();
		this.shop_location_id = shop_location_id;
		this.shop_location = shop_location;

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
	
	
	
}
