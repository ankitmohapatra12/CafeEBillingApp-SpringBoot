package com.secureApp.SecureApp.DTO;

import java.util.Set;

import com.secureApp.SecureApp.entity.ItemEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @author Ankit Mohapatra
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryItemDto {

	
	private String category;
	private String shop_location;
	
	
	private int shop_location_id;


    private Set<ItemEntity> items;
}
