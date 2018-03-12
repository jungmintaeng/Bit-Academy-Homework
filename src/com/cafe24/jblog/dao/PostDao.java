package com.cafe24.jblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.vo.PostVo;

public class PostDao {
	/**
	 * 	private long no;
	private String title;
	private String content;
	private String reg_date;
	private long category_no;
	private long blog_no;
	 */
	public List<PostVo> getList(){
		List<PostVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PostVo vo = null;
		long no, category_no, blog_no;
		String title, content, reg_date;
		
		try {
			String sql = "SELECT NO, TITLE, CONTENT, REG_DATE, CATEGORY_NO, BLOG_NO"
					+ "		FROM POST";
			
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				no = rs.getLong(1);
				title = rs.getString(2);
				content = rs.getString(3);
				reg_date = rs.getString(4);
				category_no = rs.getLong(5);
				blog_no = rs.getLong(6);
				
				vo = new PostVo(no, title, content, reg_date, category_no, blog_no);
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
	
	public boolean insert(PostVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			String insertSQL = "INSERT INTO POST (NO, TITLE, CONTENT, REG_DATE, CATEGORY_NO, BLOG_NO)"
					+ "				VALUES (?,?,?,DATE_FORMAT(now(), '%Y-%m-%d'),?,?)";
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setLong(1, vo.getNo());
			pst.setString(2, vo.getTitle());
			pst.setString(3, vo.getContent());
			pst.setLong(4, vo.getCategory_no());
			pst.setLong(5, vo.getBlog_no());
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
