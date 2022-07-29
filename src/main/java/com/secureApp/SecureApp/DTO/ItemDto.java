package com.secureApp.SecureApp.DTO;
/**
 * @author Ankit Mohapatra
 * 
 */
import lombok.Getter;

@Getter
public class ItemDto {

    private String item_name;
    
    private String item_type;

    private double item_price;
 
    private int item_quantity;

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public double getItem_price() {
		return item_price;
	}

	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}

	public int getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}

	public ItemDto(String item_name, String item_type, double item_price, int item_quantity) {
		super();
		this.item_name = item_name;
		this.item_type = item_type;
		this.item_price = item_price;
		this.item_quantity = item_quantity;
	}

	public ItemDto() {
		super();
	}
    
    
    
    
    
    
}
