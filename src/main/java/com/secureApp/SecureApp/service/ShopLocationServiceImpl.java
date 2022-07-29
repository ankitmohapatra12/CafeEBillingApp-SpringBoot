package com.secureApp.SecureApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secureApp.SecureApp.entity.ShopLocationEntity;
import com.secureApp.SecureApp.repository.ShopLocationRepository;

/**
 * @author Ankit Mohapatra
 * 
 */
@Service
public class ShopLocationServiceImpl implements ShopLocationService{

	private static final Logger log = LoggerFactory.getLogger(ShopLocationServiceImpl.class);
	
	@Autowired
	private ShopLocationRepository shopLocationRepo;
	
	@Override
	public String addShopLocation(String shopLocation) {
		ShopLocationEntity sle = new ShopLocationEntity();
		sle.setShop_location(shopLocation);
		String result ="";
		try {
			shopLocationRepo.save(sle);
			result = "Shop added Successfully";
		}
		catch(Exception e) {
			log.error("ShopLocationServiceImpl::addShopLocation()",e.toString());
			return "Shop Failed to save";
		}
		return result;
	}

	@Override
	public ShopLocationEntity checkIfShopLocationExists(String shop_location) {
		ShopLocationEntity location=null;;
		try {
		location = shopLocationRepo.findByShopLocation(shop_location);
		}
		catch(Exception e) {
			log.error("ShopLocationServiceImpl::checkIfShopLocationExists()",e.toString());
		}
		return location;
		
	
	}

	
	
	
}
