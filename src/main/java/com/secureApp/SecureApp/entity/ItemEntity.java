package com.secureApp.SecureApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Ankit Mohapatra
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="menu_items")
public class ItemEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long item_id;

	    @Column(nullable = false,length=50)
	    private String item_name;
	    
	    @Column(nullable = false,length=10)
	    private String item_type;
	    
	    @Column(nullable = false)
	    private double item_price;
	   
	    @Column(nullable = false)
	    private long category_id;
	    
	    
	    
	  

	    
	    
	    
	    
		

}
