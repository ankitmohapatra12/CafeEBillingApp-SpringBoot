package com.secureApp.SecureApp.DTO;

import java.util.List;

import com.secureApp.SecureApp.entity.OrderedItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author Ankit Mohapatra
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillCreation {

	private String restaurant_name;
	private String restaurant_address;
	private String restaurant_phone;
	private String restaurant_secondary_phone;
	private long table_number;
	private String customer_name;
	private String customer_phone;
	private String customer_email;
	private List<OrderedItems> items;
	private int SGST;
	private int CGST;
	private double net_amount;
	private String cashier_name;
	private String mode_of_payment;
	@Override
	public String toString() {
		return "BillCreation [restaurant_name=" + restaurant_name + ", restaurant_address=" + restaurant_address
				+ ", restaurant_phone=" + restaurant_phone + ", restaurant_secondary_phone="
				+ restaurant_secondary_phone + ", table_number=" + table_number + ", customer_name=" + customer_name
				+ ", customer_phone=" + customer_phone + ", customer_email=" + customer_email + ", items=" + items
				+ ", SGST=" + SGST + ", CGST=" + CGST + ", net_amount=" + net_amount + ", cashier_name=" + cashier_name
				+ ", mode_of_payment=" + mode_of_payment + "]";
	}
	
	
	
}
