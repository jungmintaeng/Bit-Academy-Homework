package com.cafe24.jblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.vo.CategoryVo;

public class CategoryDao {
	
	/**
	 * 	private long no;
	private String name;
	private long blog_no;
	 */
	public List<CategoryVo> getList(){
		List<CategoryVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CategoryVo vo = null;
		long no, blog_no;
		String name;
		
		try {
			String sql = "SELECT NO, NAME, BLOG_NO"
					+ "		FROM CATEGORY";
			
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				no = rs.getLong(1);
				name = rs.getString(2);
				blog_no = rs.getLong(3);
				
				vo = new CategoryVo(no, name, blog_no);
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
	
	public boolean insert(CategoryVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			String insertSQL = "INSERT INTO CATEGORY(NO, NAME, BLOG_NO)"
					+ "				VALUES (?,?,?)";
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setLong(1, vo.getNo());
			pst.setString(2, vo.getName());
			pst.setLong(3, vo.getBlog_no());
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
