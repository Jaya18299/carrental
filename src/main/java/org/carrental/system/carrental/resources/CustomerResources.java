package org.carrental.system.carrental.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.carrental.system.carrental.exception.DataNotFoundException;
import org.carrental.system.carrental.model.Customer;
import org.carrental.system.carrental.service.CustomerService;


@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class CustomerResources {
	
	CustomerService customerService= new CustomerService();

	@GET
	public Response getCustomers(){
		return customerService.getAllCustomer();
	}
	
	@GET
	@Path("/{customer_id}")
	public Response getCustomerById(@PathParam("customer_id")int customer_id,@Context UriInfo uriInfo) throws DataNotFoundException {
		
		return customerService.getCustomerById(customer_id, uriInfo);
	}
	
	@POST
	public Response addCustomer(Customer customer){
		return customerService.addCustomer(customer);
	}
	
	@PUT
	@Path("/{customer_id}")
	public Response updateCustomer(@PathParam("customer_id")int customer_id,Customer customer){
		return customerService.updateCustomer(customer_id, customer);
	}
	
	@DELETE
	@Path("/{customer_id}")
	public Response removeCustomer(@PathParam("customer_id")int customer_id) throws DataNotFoundException {
		return customerService.removeCustomer(customer_id);
		
	}
	
}
