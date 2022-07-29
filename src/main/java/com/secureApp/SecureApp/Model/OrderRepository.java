package com.secureApp.SecureApp.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secureApp.SecureApp.entity.OrderedItems;
/**
 * @author Ankit Mohapatra
 * 
 */
public interface OrderRepository extends JpaRepository<OrderedItems, Long> {

}
