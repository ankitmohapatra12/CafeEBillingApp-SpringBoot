package com.secureApp.SecureApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.DTO.ItemDto;
import com.secureApp.SecureApp.entity.ItemEntity;
import com.secureApp.SecureApp.service.ItemCategoryService;
/**
 * @author Ankit Mohapatra
 * 
 */
@RestController
public class ItemController {
	
	@Autowired
	private ItemCategoryService categoryService;
	
	
	
	@PostMapping("/addItem/{category_id}")
	public String createItem(@PathVariable("category_id") long category_id,
			@RequestBody ItemDto itemDto) {
		ItemEntity item = new ItemEntity();
		item.setItem_name(itemDto.getItem_name());
		item.setItem_price(itemDto.getItem_price());
		item.setItem_type(itemDto.getItem_type());
		item.setCategory_id(category_id);
		
		String res = categoryService.saveItem(itemDto,category_id);
		
		if(res.equalsIgnoreCase("failed")) {
			return "Failed to save item";
		}
		
		return "Successfully saved item !";
	}
	
}
