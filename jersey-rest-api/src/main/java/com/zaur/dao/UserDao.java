package com.zaur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
