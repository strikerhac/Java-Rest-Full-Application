package edu.seecs.dropwizard;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
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
@Api(value = "/person", description = "person Case study")
public class PersonResources {

	@SuppressWarnings("unused")
	private final Validator validator_;
	private DBHandler db;

	public PersonResources(Validator validator) {
		this.validator_ = validator;
		this.db = new DBHandler();
	}

	@GET
	@Path("/{personID}")
	@ApiOperation(value = "Get person", notes = "Get person", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("personID") int personID, @HeaderParam("Authorization") String encodedString) {
		if (db.authorize(encodedString)) {
			Person p = new Person();
			p = db.get(personID);
			if (p != null)
				return Response.ok(p).build();
			else
				return Response.status(Response.Status.NOT_FOUND).entity("person not found").build();
		} else
			return Response.status(Response.Status.UNAUTHORIZED).entity("incorrect username or password").build();
	}

	@POST
	@ApiOperation(value = "Add person", notes = "Add person", response = String.class)
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

	@DELETE
	@Path("/{personID}")
	@ApiOperation(value = "Delete person", notes = "Delete person", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Device deleted"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response del(@PathParam("personID") int personID, @HeaderParam("Authorization") String encodedString) {

		if (db.authorize(encodedString)) {
			db.delete(personID);
			return Response.ok().build();
		} else
			return Response.status(Response.Status.UNAUTHORIZED).entity("incorrect username or password").build();
	}

}
