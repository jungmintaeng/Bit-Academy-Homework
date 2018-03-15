package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WebDBAccess implements DBAccessable{
	private static WebDBAccess instance = null;
	
	private WebDBAccess() {
		// Make Constructor Private
	}
	
	public static WebDBAccess getInstance() {
		synchronized(WebDBAccess.class) {
			if(instance == null) {
				instance = new WebDBAccess();
			}
			return instance;
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/webdb", "webdb", "webdb");
		return conn;
	}
}
