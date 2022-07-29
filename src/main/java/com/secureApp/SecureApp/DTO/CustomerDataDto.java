package com.secureApp.SecureApp.DTO;
/**
 * @author Ankit Mohapatra
 * 
 */
public class CustomerDataDto {
	
	private String customer_id;
	private String customer_name;
	private String customer_email;
	private String customer_phone;
	private String customer_username;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_username() {
		return customer_username;
	}
	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}
	public CustomerDataDto(String customer_id, String customer_name, String customer_email, String customer_phone,
			String customer_username) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_phone = customer_phone;
		this.customer_username = customer_username;
	}
	public CustomerDataDto() {
		super();
	}
	@Override
	public String toString() {
		return "CustomerDataDto [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_email="
				+ customer_email + ", customer_phone=" + customer_phone + ", customer_username=" + customer_username
				+ "]";
	}
	
	
	
}
