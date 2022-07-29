package com.secureApp.SecureApp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.ShopLocationEntity;
/**
 * @author Ankit Mohapatra
 * 
 */
@Repository
@Transactional
public interface ShopLocationRepository extends JpaRepository<ShopLocationEntity, Integer> {

	
	@Query(value= "SELECT shop_location_id,shop_location from "
			+ "shop_location c where c.isDeleted = 0 and c.shop_location = ?",nativeQuery = true)
	ShopLocationEntity findByShopLocation(String shop_location);

}
