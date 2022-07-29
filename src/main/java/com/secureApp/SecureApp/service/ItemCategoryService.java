package com.secureApp.SecureApp.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.DTO.ItemDto;
import com.secureApp.SecureApp.entity.ItemCategory;
import com.secureApp.SecureApp.entity.ItemEntity;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public interface ItemCategoryService {

	



	String addItemCategory(ItemCategory itemCategory);

	

	String checkIfSameCategoryLocationExists(String category, String shop_location);


    List<ItemCategory> findAllCategory();







	Collection<ItemEntity> findAllItems();
	
	Collection<ItemEntity> findAllArchieved();



	



	String archieveItems(List<Long> item_ids);



	String enableItems(List<Long> item_ids);



	String saveItem(ItemDto itemDto, long category_id);

}
