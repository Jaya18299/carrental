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
import org.carrental.system.carrental.model.Car;
import org.carrental.system.carrental.service.CarService;

	@Path("/cars")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public class CarResource {

		CarService carService= new CarService();
		
		@GET
		public Response getAllCar(){
			return carService.getAllCar();	
		}
		
		@GET
		@Path("/{car_id}")
		public Response getCarDetailById(@PathParam("car_id") int car_id,@Context UriInfo uriInfo) throws DataNotFoundException{
			System.out.println("Car id in the resource "+car_id);
			Response response = carService.getCarDetailById(car_id, uriInfo);
			return response;
		}
		
		@POST
		public Response addCar(Car car){
			System.out.println("Inside the resource added car");
			return carService.addCar(car);
		}
		
		@PUT
		@Path("/{car_id}")
		public Response updateCar(@PathParam("car_id") int car_id, Car car) {
			return carService.updateCar(car_id, car);
		}
		
		@DELETE
		@Path("/{car_id}")
		public Response deleteCar(@PathParam("car_id") int car_id) throws DataNotFoundException {
			return carService.deleteCar(car_id);
		}
		
}
