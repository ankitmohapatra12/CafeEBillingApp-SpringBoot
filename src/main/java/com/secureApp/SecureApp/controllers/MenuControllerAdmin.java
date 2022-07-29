package com.secureApp.SecureApp.controllers;
/**
 * @author Ankit Mohapatra
 * 
 */
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secureApp.SecureApp.entity.ItemCategory;
import com.secureApp.SecureApp.entity.ItemEntity;
import com.secureApp.SecureApp.service.ItemCategoryService;

@RestController
public class MenuControllerAdmin {
	
	
	@Autowired
	private ItemCategoryService categoryService;
	
	
	
	
	
	@GetMapping("/getMenuCategories")
	public ResponseEntity<Collection<ItemCategory>> getAllCategories(){
		
		return new ResponseEntity<>(categoryService.findAllCategory(),HttpStatus.OK);
	}
	
	

	
	@GetMapping("/getMenuItems")
	public ResponseEntity<Collection<ItemEntity>> getAllItems(){
		
		return new ResponseEntity<>(categoryService.findAllItems(),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getArchievedMenuItems")
	public ResponseEntity<Collection<ItemEntity>> getAllItemsArchieved(){
		
		return new ResponseEntity<>(categoryService.findAllArchieved(),HttpStatus.OK);
	}
	

	@GetMapping(value="/archieveMenuItems", params = "item_ids")
	public String archievedItems(@RequestParam List<Long> item_ids){
		
		String message = categoryService.archieveItems(item_ids);
		return message;
	}
	
	
	
	@GetMapping(value="/enableMenuItems", params = "item_ids")
	public String enableMenuItems(@RequestParam List<Long> item_ids){
		
		String message = categoryService.enableItems(item_ids);
		return message;
	}
	
}
