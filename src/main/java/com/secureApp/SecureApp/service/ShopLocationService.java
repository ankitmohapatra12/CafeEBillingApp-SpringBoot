package com.secureApp.SecureApp.service;

import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.entity.ShopLocationEntity;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public interface ShopLocationService {


	String addShopLocation(String location);

	ShopLocationEntity checkIfShopLocationExists(String shop_location);

}
