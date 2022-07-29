package com.secureApp.SecureApp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ankit Mohapatra
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ordered_items")
public class OrderedItems {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long order_id;

	    @Column(length=50)
	    private String order_item_name;
	    
	    @Column(length=10)
	    private String order_item_type;
	    
	   
	    private double order_item_price;
	    
	   
	    private int order_item_qty;
	    
	    @JsonIgnore
	    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	    @JoinColumn(name = "bill_id")
	    private BillCreationEntity bill;

		@Override
		public String toString() {
			return "OrderedItems [order_id=" + order_id + ", order_item_name=" + order_item_name + ", order_item_type="
					+ order_item_type + ", order_item_price=" + order_item_price + ", order_item_qty=" + order_item_qty
					+ ", bill=" + bill + "]";
		}
	
	    
}
