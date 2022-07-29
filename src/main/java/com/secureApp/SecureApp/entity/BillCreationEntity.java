package com.secureApp.SecureApp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author Ankit Mohapatra
 * 
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="all_bills")
public class BillCreationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bill_id;
	
	private String gstin_unique_bill_number;
	private String restaurant_name;
	private String restaurant_address;
	private String restaurant_phone;
	private String restaurant_secondary_phone;
	private long table_number;
	private String customer_name;
	private String customer_phone;
	private String customer_email;
	
	@Column(name="date_of_creation")
	private String date_of_creation;
	
	
	@OneToMany(mappedBy = "bill" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderedItems> items;
	
	private int SGST;
	private int CGST;
	private double net_amount;
	private String cashier_name;
	private String mode_of_payment;
	public long getBill_id() {
		return bill_id;
	}
	public void setBill_id(long bill_id) {
		this.bill_id = bill_id;
	}
	public String getGstin_unique_bill_number() {
		return gstin_unique_bill_number;
	}
	public void setGstin_unique_bill_number(String gstin_unique_bill_number) {
		this.gstin_unique_bill_number = gstin_unique_bill_number;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getRestaurant_address() {
		return restaurant_address;
	}
	public void setRestaurant_address(String restaurant_address) {
		this.restaurant_address = restaurant_address;
	}
	public String getRestaurant_phone() {
		return restaurant_phone;
	}
	public void setRestaurant_phone(String restaurant_phone) {
		this.restaurant_phone = restaurant_phone;
	}
	public String getRestaurant_secondary_phone() {
		return restaurant_secondary_phone;
	}
	public void setRestaurant_secondary_phone(String restaurant_secondary_phone) {
		this.restaurant_secondary_phone = restaurant_secondary_phone;
	}
	public long getTable_number() {
		return table_number;
	}
	public void setTable_number(long table_number) {
		this.table_number = table_number;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getDate_of_creation() {
		return date_of_creation;
	}
	public void setDate_of_creation(String date_of_creation) {
		this.date_of_creation = date_of_creation;
	}
	public List<OrderedItems> getItems() {
		return items;
	}
	public void setItems(List<OrderedItems> items) {
		this.items = items;
	}
	public int getSGST() {
		return SGST;
	}
	public void setSGST(int sGST) {
		SGST = sGST;
	}
	public int getCGST() {
		return CGST;
	}
	public void setCGST(int cGST) {
		CGST = cGST;
	}
	public double getNet_amount() {
		return net_amount;
	}
	public void setNet_amount(double net_amount) {
		this.net_amount = net_amount;
	}
	public String getCashier_name() {
		return cashier_name;
	}
	public void setCashier_name(String cashier_name) {
		this.cashier_name = cashier_name;
	}
	public String getMode_of_payment() {
		return mode_of_payment;
	}
	public void setMode_of_payment(String mode_of_payment) {
		this.mode_of_payment = mode_of_payment;
	}
	
	
	
	

	
}