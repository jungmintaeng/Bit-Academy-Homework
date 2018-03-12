package com.cafe24.jblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.vo.BlogVo;

public class BlogDao {
	/**
	 * 	private long no;
	private String title;
	private String imageUrl;
	private long manager_no;
	 */
	
	public List<BlogVo> getList(){
		List<BlogVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BlogVo vo = null;
		long no, manager_no;
		String title, imageUrl;
		
		try {
			String sql = "SELECT NO, TITLE, IMAGE, MANAGER_NO"
					+ "		FROM BLOG";
			
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				no = rs.getLong(1);
				title = rs.getString(2);
				imageUrl = rs.getString(3);
				manager_no = rs.getLong(4);
				vo = new BlogVo(no, title, imageUrl, manager_no);
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
	
	public boolean insert(BlogVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			String insertSQL = "INSERT INTO BLOG(NO, TITLE, IMAGE, MANAGER_NO)"
					+ "				VALUES(?,?,?,?)";
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setLong(1, vo.getNo());
			pst.setString(2, vo.getTitle());
			pst.setString(3, vo.getImageUrl());
			pst.setLong(4, vo.getManager_no());
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
