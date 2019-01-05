package edu.seecs.dropwizard;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/person")
@Api(value = "/IoT", description = "IoT Case study")
public class DeviceResources {

	@SuppressWarnings("unused")
	private final Validator validator_;
	private DBHandler db;

	public DeviceResources(Validator validator) {
		this.validator_ = validator;
		this.db = new DBHandler();
	}

	@GET
	@Path("/{personID}")
	@ApiOperation(value = "Get device", notes = "Get device", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("personID") int personID) {
		Person p = new Person();
		p = db.get(personID);
		return Response.ok(p).build();

	}

	@POST
	@ApiOperation(value = "Add device.", notes = "Add device", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(@Valid Person p) {
		db.save(p);
		return Response.ok().build();
	}

	@PUT
	@ApiOperation(value = "Update device.", notes = "Update device", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Updated device"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(@Valid Person p) {
		db.update(p);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{personID}")
	@ApiOperation(value = "Delete device", notes = "Delete device", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Device deleted"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response del(@PathParam("personID") int personID) {
		db.delete(personID);
		return Response.ok().build();
	}

}
