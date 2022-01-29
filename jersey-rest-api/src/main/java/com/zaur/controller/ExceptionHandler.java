package com.zaur.controller;

import java.sql.SQLException;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaur.dto.ErrorDto;

public class ExceptionHandler {

	public static final SQLException ENTITY_NOT_FOUND = new SQLException("Entity Not Found");

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public static ErrorDto handle(Exception e) {
		logger.error("", e);
		ErrorDto error = new ErrorDto();
		if (e instanceof SQLException) {
			error.setStatus(Status.BAD_REQUEST);
			error.setCode("0001");
			if (e.getMessage().equals(ENTITY_NOT_FOUND.getMessage())) {
				error.setMessage(e.getMessage());
			} else {
				error.setMessage("Error Processing Request");
			}
		} else {
			error.setStatus(Status.INTERNAL_SERVER_ERROR);
			error.setCode("0002");
			error.setMessage("Unknown Exception");
		}

		return error;
	}
}
