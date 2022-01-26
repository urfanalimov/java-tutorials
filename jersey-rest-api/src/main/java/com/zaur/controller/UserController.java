package com.zaur.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaur.dto.UserDto;
import com.zaur.service.UserService;

@Path("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		try {
			UserDto user = UserService.getUser(Long.parseLong(id));
			return Response.ok(user).build();
		} catch (Exception e) {
			logger.error("Error when get user", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/me")
	public String get() {
		return "Me is";
	}
}
