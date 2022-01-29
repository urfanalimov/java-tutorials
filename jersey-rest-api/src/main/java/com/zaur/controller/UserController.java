package com.zaur.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaur.dto.ErrorDto;
import com.zaur.dto.UserDto;
import com.zaur.service.UserService;

@Path("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
		try {
			logger.info("Got request for retrieving user {}", id);
			UserDto user = UserService.getUser(id);
			return Response.ok(user).build();
		} catch (Exception e) {
			ErrorDto error = ExceptionHandler.handle(e);
			return Response.status(error.getStatus()).entity(error).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(UserDto user) {
		try {
			logger.info("Got request for creating user {}", user);
			UserDto createdUser = UserService.createUser(user);
			return Response.ok(createdUser).build();
		} catch (Exception e) {
			ErrorDto error = ExceptionHandler.handle(e);
			return Response.status(error.getStatus()).entity(error).build();
		}
	}

	@GET
	@Path("/me")
	public String get() {
		return "Me is";
	}
}
