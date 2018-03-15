package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBAccessable {
	public Connection getConnection() throws SQLException, ClassNotFoundException;
}
