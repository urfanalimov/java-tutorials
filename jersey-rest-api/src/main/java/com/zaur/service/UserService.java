package com.zaur.service;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaur.dao.ConnectionManager;
import com.zaur.dao.UserDao;
import com.zaur.dto.UserDto;

public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public static UserDto getUser(Long id) throws Exception {
		logger.info("Getting user {}", id);
		Connection conn = null;
		try {
			conn = ConnectionManager.connect();
			return UserDao.get(conn, id);
		} finally {
			ConnectionManager.close(conn);
		}
	}

	public static UserDto createUser(UserDto user) throws Exception {
		logger.info("Creating user {}", user);
		Connection conn = null;
		try {
			conn = ConnectionManager.connect();
			return UserDao.create(conn, user);
		} finally {
			ConnectionManager.close(conn);
		}
	}
}
