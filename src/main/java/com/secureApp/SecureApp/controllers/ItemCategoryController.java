package com.secureApp.SecureApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.entity.ItemCategory;
import com.secureApp.SecureApp.entity.ShopLocationEntity;
import com.secureApp.SecureApp.service.ItemCategoryService;
import com.secureApp.SecureApp.service.ShopLocationService;
/**
 * @author Ankit Mohapatra
 * 
 */
@RestController
public class ItemCategoryController {
	
	
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@Autowired
	private ShopLocationService shopLocationService;
	
	
	//ADD ITEM CATEGORY DATA
	@PostMapping("/addItemCategory")
	public String addItemCategory(@RequestBody ItemCategory itemCategory) {
		
		String res = "";
		String result ="";
		String location = itemCategory.getShop_location();
		
		if(!itemCategory.getCategory().isEmpty()) {
			if(!itemCategory.getCategory().matches("a-zA-Z"))
			{
				return "Please enter a valid category !!";
			}
			if(itemCategory.getCategory().length()>30) {
				return "Length of category cannot exceeed 30 Characters !!";
			}
		}else { return "Category cannot be empty!"; }
		
		
		if(!itemCategory.getShop_location().isEmpty()) {
			if(!itemCategory.getShop_location().matches("a-zA-Z"))
			{
				return "Please enter a valid shop location";
			}
			if(itemCategory.getShop_location().length()>30) {
				return "Length of shop location cannot exceeed 30 Characters !!";
			}
		}else { return "shop location cannot be empty!"; }
		
		itemCategory.setCategory(itemCategory.getCategory().trim());
		itemCategory.setShop_location(itemCategory.getShop_location().trim());
		
		res = itemCategoryService.checkIfSameCategoryLocationExists(itemCategory.getCategory(),itemCategory.getShop_location());
		
		if(res.equalsIgnoreCase("EMPTY")) {
			ShopLocationEntity shoplocation = shopLocationService.checkIfShopLocationExists(itemCategory.getShop_location());
			if(shoplocation==null) {
			res = itemCategoryService.addItemCategory(itemCategory);
			result =  shopLocationService.addShopLocation(location);
			}
			
			else {
				itemCategory.setShop_location_id(shoplocation.getShop_location_id());
				res = itemCategoryService.addItemCategory(itemCategory);
			}
			
			if(res.equals("")) {
				return result;
			}
			
			if(result.equals("")) {
				return res+" !";
			}
		}
		else if(res.equalsIgnoreCase("failed"))
		{
			return "Some internal server error occured !";
		}
		
		else {
			return "Same Category and location already exists !";
		}
		
		
		return res+" and "+result+" !";
	}
}
