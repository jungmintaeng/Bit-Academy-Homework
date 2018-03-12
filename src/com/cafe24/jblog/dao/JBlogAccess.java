package com.cafe24.jblog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JBlogAccess implements DBAccessable{
	private static JBlogAccess instance = null;
	
	private JBlogAccess() {
		// Make Constructor Private
	}
	
	public static JBlogAccess getInstance() {
		synchronized(JBlogAccess.class) {
			if(instance == null) {
				instance = new JBlogAccess();
			}
			return instance;
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/jblog", "jblog", "jblog");
		return conn;
	}
}
