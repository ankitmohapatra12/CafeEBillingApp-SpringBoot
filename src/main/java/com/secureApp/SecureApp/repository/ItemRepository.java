package com.secureApp.SecureApp.repository;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.ItemEntity;
/**
 * @author Ankit Mohapatra
 * 
 */
@Repository
@Transactional
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	
	@Query(value= "SELECT * from menu_items c where c.isDeleted=0",nativeQuery = true)
	Collection<ItemEntity> findAllNotDeleted();
	
	@Query(value= "SELECT * from menu_items c where c.isDeleted=1",nativeQuery = true)
	Collection<ItemEntity> findAllDeleted();

	@Modifying
	@Query(value = "update menu_items c set c.isDeleted=1 where c.item_id IN (:item_ids)",nativeQuery = true)
	int archiveItems(@Param("item_ids") List<Long> item_ids);
	

	@Modifying
	@Query(value = "update menu_items c set c.isDeleted=0 where c.item_id IN (:item_ids)",nativeQuery = true)
	int enableItems(@Param("item_ids") List<Long> item_ids);

}
