package com.zaur.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Connection pooling should be used for better performance.
 *
 */
public class ConnectionManager {

	private static final String url = System.getenv("DB_URL");
	private static final String user = System.getenv("DB_USER");
	private static final String password = System.getenv("DB_PASSWORD");

	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

	public static Connection connect() throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("Error when closing connection", e);
		}
	}
}
