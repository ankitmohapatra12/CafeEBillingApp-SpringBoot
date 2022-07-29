package com.secureApp.SecureApp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.DTO.ItemDto;
import com.secureApp.SecureApp.entity.ItemCategory;
import com.secureApp.SecureApp.entity.ItemEntity;
import com.secureApp.SecureApp.repository.ItemCategoryRepository;
import com.secureApp.SecureApp.repository.ItemRepository;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	private static final Logger log = LoggerFactory.getLogger(ItemCategoryServiceImpl.class);
	
	
	@Autowired
	private ItemCategoryRepository itemCategoryRepo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	
	@Override
	public String addItemCategory(ItemCategory itemCategory) {
		
		try {
			itemCategoryRepo.save(itemCategory);
		}catch(Exception e) {
			log.error("ItemCategoryServiceImpl::addItemCategory()",e.toString());
			return "Failed to save category details";
		}
		return "Successfully saved Category";
	}


	@Override
	public String checkIfSameCategoryLocationExists(String itemCategory, String shop_location) {
		List<ItemCategory> itemCat = new ArrayList<>();
		try {
			itemCat = itemCategoryRepo.checkIfSameCategoryLocationExists(itemCategory,shop_location);
			
			if(!itemCat.isEmpty()) {
				return "Exist";
			}
		}catch(Exception e) {
			log.error("ItemCategoryServiceImpl::checkIfSameCategoryLocationExists()",e.toString());
			return "Failed";
		}
		return "Empty";
	}


	@Override
	public List<ItemCategory> findAllCategory() {
		List<ItemCategory> itemCat = new ArrayList<>();
		try {
		itemCat =  itemCategoryRepo.getAllCategories();
		}catch(Exception e) {
			log.error("ItemCategoryServiceImpl::findAllCategory()",e.toString());
			return null;
		}
		
		return itemCat;
	}


	@Override
	public String saveItem(ItemDto itemDto,long category_id) {
		
		if(!itemDto.getItem_name().isEmpty()) {
			if(!itemDto.getItem_name().matches("a-zA-Z"))
			{
				return "Please enter a valid item name !!";
			}
			if(itemDto.getItem_name().length()>30) {
				return "Length of item name cannot exceeed 30 Characters !!";
			}
		}else { return "Item name cannot be empty!"; }
		
		if(itemDto.getItem_price()!=0) {
			if(!itemDto.getItem_name().matches("a-zA-Z"))
			{
				return "Please enter a valid item name !!";
			}
			if(itemDto.getItem_price()<10) {
				return "item name cannot be less than 10 !!";
			}
		}else { return "Item name cannot be zero!"; }
		
		if(!itemDto.getItem_type().isEmpty()) {
			if(!itemDto.getItem_type().matches("0-9"))
			{
				return "Please enter a valid item type !!";
			}
			if(itemDto.getItem_type().length()>10) {
				return "Length of item type cannot exceeed 10 Characters !!";
			}
		}else { return "Item type cannot be empty!"; }
		
		if(itemDto.getItem_quantity()!=0) {
			
			if(itemDto.getItem_quantity()<1) {
				return "item quantity cannot be less than 0 !!";
			}
		}else { return "Item name cannot be zero!"; }
		
		
		ItemEntity item = new ItemEntity();
		item.setItem_name(itemDto.getItem_name().trim());
		item.setItem_price(itemDto.getItem_price());
		item.setItem_type(itemDto.getItem_type().toLowerCase().trim());
		item.setCategory_id(category_id);
		try {
			itemRepo.save(item);
		}
		catch(Exception e) {
			log.error("ItemCategoryServiceImpl::findAllCategory()",e.toString());
			return "FAILED";
		}
		
		return "SUCCESS";
	}


	@Override
	public Collection<ItemEntity> findAllItems() {
		Collection<ItemEntity> items= null;
		try {
			items = itemRepo.findAllNotDeleted();
		}
		catch(Exception e) {
			log.error("ItemCategoryServiceImpl::findAllCategory()",e.toString());
			return null;
		}
		return items;
	}


	@Override
	public Collection<ItemEntity> findAllArchieved() {
		Collection<ItemEntity> items= null;
		try {
			items = itemRepo.findAllDeleted();
		}
		catch(Exception e) {
			log.error("ItemCategoryServiceImpl::findAllCategory()",e.toString());
			return null;
		}
		return items;
	}


	@Override
	public String archieveItems(List<Long> item_ids) {
		String response ="";
	    try {
	    	int  i = itemRepo.archiveItems(item_ids);
	    	if(i>0) {
	    		response = "SUCCESS";
	    	}
	    	
	    }catch(Exception e ) {
	    	log.error("ItemCategoryServiceImpl::findAllCategory()",e.toString());
	    	return "FAILED";
	    }
		return response;
	}


	@Override
	public String enableItems(List<Long> item_ids) {
		String response ="";
	    try {
	    	int  i = itemRepo.enableItems(item_ids);
	    	
	    	if(i==item_ids.size()) {
	    		response = "SUCCESS";	
	    	}
	    	
	    }catch(Exception e ) {
	    	log.error("ItemCategoryServiceImpl::findAllCategory()",e.toString());
	    	return "FAILED";
	    }
		return response;
	}


	

}
