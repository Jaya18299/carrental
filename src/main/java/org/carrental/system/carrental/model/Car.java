package org.carrental.system.carrental.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {

	private int car_id;
	private String car_name;
    private String car_type;
    private int car_basecharge;
    private String car_status;
    private String responseMessage;
    
    
    public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Car(){
    	
    }
    
    public Car(int car_id,String car_name,String car_type){
    	this.car_id=car_id;
    	this.car_name=car_name;
    	this.car_type=car_type;
    }
    
    
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public int getCar_basecharge() {
		return car_basecharge;
	}
	public void setCar_basecharge(int car_basecharge) {
		this.car_basecharge = car_basecharge;
	}
	public String getCar_status() {
		return car_status;
	}
	public void setCar_status(String car_status) {
		this.car_status = car_status;
	}
}
