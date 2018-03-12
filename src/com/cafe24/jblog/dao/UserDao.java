package com.cafe24.jblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.vo.UserVo;

public class UserDao {
	/**
	 * 	private long no;
	private String id;
	private String password;
	private String name;
	 */
	public List<UserVo> getList(){
		List<UserVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserVo vo = null;
		long no;
		String id, password, name;
		
		try {
			String sql = "SELECT NO, ID , PASSWORD, NAME"
					+ "		FROM USER";
			
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				no = rs.getLong(1);
				id = rs.getString(2);
				password = rs.getString(3);
				name = rs.getString(4);
				
				vo = new UserVo(no, id, password, name);
				vos.add(vo);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst != null && !pst.isClosed())
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vos;
	}
	
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			String insertSQL = "INSERT INTO USER(NO, ID, PASSWORD, NAME)"
					+ "				VALUES(?,?, PASSWORD(?) ,?)";
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setLong(1, vo.getNo());
			pst.setString(2, vo.getId());
			pst.setString(3, vo.getPassword());
			pst.setString(4, vo.getName());
			result = pst.executeUpdate() > 0;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst != null && !pst.isClosed())
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
