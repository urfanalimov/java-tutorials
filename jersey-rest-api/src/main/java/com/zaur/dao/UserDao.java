package com.zaur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaur.controller.ExceptionHandler;
import com.zaur.dto.UserDto;

public class UserDao {

	public static UserDto get(Connection conn, Long id) throws Exception {
		UserDto retVal = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from t_user where id=?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				retVal = new UserDto();
				retVal.setId(rs.getLong("id"));
				retVal.setFirstName(rs.getString("first_name"));
				retVal.setLastName(rs.getString("last_name"));
			} else {
				throw ExceptionHandler.ENTITY_NOT_FOUND;
			}
			return retVal;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

	public static UserDto create(Connection conn, UserDto user) throws Exception {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("INSERT INTO t_user(first_name, last_name) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			int id = statement.executeUpdate();
			user.setId(new Long(id));
			return user;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}
}
