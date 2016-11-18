package org.carrental.system.carrental.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	private int customer_id;
	private String customer_name;
	private int customer_contact;
	private String customer_email_id;
	private int customer_house_no;
	private String customer_city;
	private int customer_pincode;
	private String customer_response;
	
	public String getCustomer_response() {
		return customer_response;
	}

	public void setCustomer_response(String customer_response) {
		this.customer_response = customer_response;
	}

	public Customer(){
		
	}
	
	public Customer(int customer_id,String customer_name,int customer_contact){
		this.customer_id=customer_id;
		this.customer_name=customer_name;
		this.customer_contact=customer_contact;
	}


	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getCustomer_contact() {
		return customer_contact;
	}
	public void setCustomer_contact(int customer_contact) {
		this.customer_contact = customer_contact;
	}
	public String getCustomer_email_id() {
		return customer_email_id;
	}
	public void setCustomer_email_id(String customer_email_id) {
		this.customer_email_id = customer_email_id;
	}
	public int getCustomer_house_no() {
		return customer_house_no;
	}
	public void setCustomer_house_no(int customer_house_no) {
		this.customer_house_no = customer_house_no;
	}
	public String getCustomer_city() {
		return customer_city;
	}
	public void setCustomer_city(String customer_city) {
		this.customer_city = customer_city;
	}
	public int getCustomer_pincode() {
		return customer_pincode;
	}
	public void setCustomer_pincode(int customer_pincode) {
		this.customer_pincode = customer_pincode;
	}

}
