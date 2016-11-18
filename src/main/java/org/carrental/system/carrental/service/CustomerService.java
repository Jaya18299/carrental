package org.carrental.system.carrental.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.carrental.system.carrental.dao.DatabaseConnection;
import org.carrental.system.carrental.exception.DataNotFoundException;
import org.carrental.system.carrental.model.Customer;


public class CustomerService {

	public Response getAllCustomer() {

		List<Customer> customerList= new ArrayList<Customer>();
		try{
			Connection con =getConnection();
			String sql="select * from customer";
			java.sql.PreparedStatement pst=con.prepareStatement(sql);
			java.sql.ResultSet result=pst.executeQuery();
			System.out.println("Query executed ");
			while (result.next()) {
				int customer_id=result.getInt("customer_id");
				String customer_name=result.getString("customer_name");
				int customer_contact=result.getInt("customer_contact");
				String customer_email_id=result.getString("customer_email_id");
				int customer_house_no=result.getInt("customer_house_no");
				String customer_city=result.getString("customer_city");
				int customer_pincode=result.getInt("customer_pincode");

				Customer customer=new Customer(customer_id, customer_name, customer_contact);

				System.out.println(" Object created ");
				customer.setCustomer_email_id(customer_email_id);
				customer.setCustomer_house_no(customer_house_no);
				customer.setCustomer_city(customer_city);
				customer.setCustomer_pincode(customer_pincode);
				System.out.println("  customer name "+customer_name);
				System.out.println("  customer pincode "+customer_pincode);
				System.out.println(" List of customers "+customerList.size());

				customerList.add(customer);
			}
			pst.close();
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		GenericEntity<List<Customer>> list = new GenericEntity<List<Customer>>(customerList) {};
		return Response.status(Status.CREATED).entity(list).build();
		//return customerList;
	}

	public Response getCustomerById(int customer_id, UriInfo uriInfo) throws DataNotFoundException {
		Customer customer = null;
		try{
			Connection con =getConnection();
			Statement stmt=con.createStatement();
			String sql = "select * from customer where customer_id='" + customer_id + "'";
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				String customer_name=result.getString("customer_name");
				int customer_contact=result.getInt("customer_contact");
				String customer_email_id=result.getString("customer_email_id");
				int customer_house_no=result.getInt("customer_house_no");
				String customer_city=result.getString("customer_city");
				int customer_pincode=result.getInt("customer_pincode");

				customer=new Customer(customer_id, customer_name, customer_contact);
				customer.setCustomer_email_id(customer_email_id);
				customer.setCustomer_house_no(customer_house_no);
				customer.setCustomer_city(customer_city);
				customer.setCustomer_pincode(customer_pincode);

			}else{
				throw new DataNotFoundException("No data found for customer Id:"+customer_id);
			}
			stmt.close();
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.status(Status.CREATED).entity(customer).build();
	}

	public Response addCustomer(Customer customer){
		try{
			Connection con =getConnection();
			Statement stmt=con.createStatement();

			String customer_name=customer.getCustomer_name();
			int customer_contact=customer.getCustomer_contact();
			String customer_email_id=customer.getCustomer_email_id();
			int customer_house_no=customer.getCustomer_house_no();
			String customer_city=customer.getCustomer_city();
			int customer_pincode=customer.getCustomer_pincode();
			String sql = "insert into customer (customer_name,customer_contact,customer_email_id,customer_house_no,customer_city,customer_pincode) values('" + customer_name + "','" + customer_contact
					+ "','" + customer_email_id 
					+ "','" + customer_house_no 
					+ "','" + customer_city  
					+ "','" + customer_pincode + "')";

			int result = stmt.executeUpdate(sql);
			if (result > 0) {
				System.out.println("Customer " + customer_name +  " has been created");
				customer.setCustomer_response("Customer " + customer_name +  " has been created");
			}
			
			System.out.println("Added customer successfully");
			stmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.status(Status.CREATED).entity(customer).build();
	}

	public Response updateCustomer(int customer_id,Customer customer){ 
		try{
			Connection con =getConnection();
			Statement stmt=con.createStatement();

			String customer_name=customer.getCustomer_name();
			int customer_contact=customer.getCustomer_contact();
			String customer_email_id=customer.getCustomer_email_id();
			int customer_house_no=customer.getCustomer_house_no();
			String customer_city=customer.getCustomer_city();
			int customer_pincode=customer.getCustomer_pincode();

			String sql = "update customer set customer_name='" + customer_name + "', customer_contact='" + customer_contact 
					+ "', customer_email_id ='"+ customer_email_id 
					+ "', customer_house_no ='"+ customer_house_no 
					+ "', customer_city ='"+ customer_city 
					+ "', customer_pincode ='"+ customer_pincode  
					+ "' where customer_id ='" + customer_id + "'";
			int result = stmt.executeUpdate(sql);
			if (result > 0) {
				System.out.println("Customer " + customer_name +  " has been created");
				customer.setCustomer_response("Customer " + customer_name +  " has been created");

			}
			stmt.close();
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return Response.status(Status.CREATED).entity(customer).build();
	}

	public Response removeCustomer(int customer_id) throws DataNotFoundException {
		String res = "";
		try{
			Connection con =getConnection();
			Statement stmt=con.createStatement();

			String sql = "delete from customer where customer_id='" + customer_id + "'";

			int result = stmt.executeUpdate(sql);
			if (result > 0) {
				res = "Customer with customer_id " + customer_id + " has been deleted";
				System.out.println("Customer with customer_id " + customer_id + "has been deleted");
			}
			else{
				throw new DataNotFoundException("No data found for customer Id: "+customer_id); 
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return Response.status(Status.CREATED).entity(res).build();
	}


	private Connection getConnection() {
		DatabaseConnection con= new DatabaseConnection();
		return con.getConnection();
	}
}
