package com.secureApp.SecureApp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.ItemCategory;
/**
 * @author Ankit Mohapatra
 * 
 */
@Transactional
@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

	
	@Query(value= "SELECT * from item_category c where c.category = ?1 and c.shop_location = ?2",nativeQuery = true)
	List<ItemCategory> checkIfSameCategoryLocationExists(String itemCategory,String shop_location);

	@Query(value= "SELECT category_id,category,shop_location,shop_location_id from item_category c where isDeleted = 0",nativeQuery = true)
	List<ItemCategory> getAllCategories();

}
