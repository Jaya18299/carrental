package org.carrental.system.carrental.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.carrental.system.carrental.dao.DatabaseConnection;
import org.carrental.system.carrental.exception.DataNotFoundException;
import org.carrental.system.carrental.model.Car;

import javax.ws.rs.core.Response.Status;


public class CarService {
	
	public Response getAllCar(){
		List<Car> carList= new ArrayList<Car>();
		try{
			Connection con=getConnection();
			Statement stmt= con.createStatement();
			String sql = "select * from car";
			ResultSet result=stmt.executeQuery(sql);
			System.out.println("query executed");
			while (result.next()) {
				int car_id=result.getInt("car_id");
				String car_name=result.getString("car_name");
				String car_type=result.getString("car_type");
				int car_basecharge=result.getInt("car_basecharge");
				String car_status=result.getString("car_status");
				System.out.println("got data");
				
				Car car=new Car(car_id,car_name,car_type);
				car.setCar_basecharge(car_basecharge);
				car.setCar_status(car_status);
				
				System.out.println("Got car object");
				
				carList.add(car);
				System.out.println("added in car list");
				
			}
			stmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		GenericEntity<List<Car>> list = new GenericEntity<List<Car>>(carList) {};
		Response res=Response.status(Status.CREATED).entity(list).build();
		return res;
	}
	
	public Response getCarDetailById(int car_id, UriInfo uriInfo) throws DataNotFoundException{
		Car car=null;
		try{
			Connection con=getConnection();
			Statement stmt= con.createStatement();
			System.out.println("Car id in the service "+car_id);
			String sql = "select * from car where car_id='" + car_id + "'";
			ResultSet result=stmt.executeQuery(sql);
			System.out.println("query executed");
			if (result.next()) {
				String car_name=result.getString("car_name");
				String car_type=result.getString("car_type");
				int car_basecharge=result.getInt("car_basecharge");
				String car_status=result.getString("car_status");
				car=new Car(car_id,car_name,car_type);
				car.setCar_basecharge(car_basecharge);
				car.setCar_status(car_status);
				
			}else{
				throw new DataNotFoundException("No data found for car Id:"+car_id);
			}
			stmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		//addlink(uriInfo, car);
		return Response.status(Status.CREATED).entity(car).build();
	}
	
	public Response addCar(Car car){
		System.out.println("Inside the service added car");
		try{
			Connection con=getConnection();
			Statement stmt= con.createStatement();
			
			String car_name=car.getCar_name();
			String car_type=car.getCar_type();
			int car_basecharge=car.getCar_basecharge();
			String car_status=car.getCar_status();
			
			String sql = "insert into car (car_name,car_type,car_basecharge,car_status) values('" + car_name + "','" + car_type
					+ "','" + car_basecharge 
					+ "','" + car_status + "')";
			int result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				car.setResponseMessage("Car " + car_name +  " has been added succesfully");
				System.out.println("Car " + car_name +  " has been added succesfully");
			}
			stmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Response.status(Status.CREATED).entity(car).build();
	}
	
	public Response updateCar(int car_id, Car car) {
		try{
			Connection con=getConnection();
			Statement stmt= con.createStatement();
			
			String car_name=car.getCar_name();
			String car_type=car.getCar_type();
			int car_basecharge=car.getCar_basecharge();
			String car_status=car.getCar_status();
			
			String sql = "update car set car_name='" + car_name + "', car_type='" + car_type 
					+ "', car_basecharge ='"+ car_basecharge 
					+ "', car_status ='"+ car_status 
					+ "' where car_id ='" + car_id + "'";
			int result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				System.out.println("Car " + car_name +  " has been updated succesfully");
				car.setResponseMessage("Car " + car_name +  " has been updated succesfully");
			}
			stmt.close();
			con.close();
			
		}catch(Exception e){
			Response.status(Status.BAD_REQUEST).entity(car).build();
			e.printStackTrace();
		}
		return Response.status(Status.CREATED).entity(car).build();
		
	}
	
	public Response deleteCar(int car_id) throws DataNotFoundException {
		String res="";
		try{
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String sql = "delete from car where car_id='" + car_id + "'";
			int result = stmt.executeUpdate(sql);
			
			if (result > 0) {
				res = "Car with Id " + car_id + " has been deleted";
				System.out.println("Car with Id " + car_id + " has been deleted");
			}
			else{
				throw new DataNotFoundException("No data found for car Id:"+car_id);
			}
			stmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.status(Status.CREATED).entity(res).build();
	}

	private Connection getConnection() {
		DatabaseConnection con=new DatabaseConnection();
		return con.getConnection();
	}

}
