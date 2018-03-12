package com.cafe24.jblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.jblog.vo.CommentVo;

public class CommentDao {
	/**
	 * 	private long no;
	private long user_no;
	private long post_no;
	private String content;
	private String reg_date;
	 */
	public List<CommentVo> getList(){
		List<CommentVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CommentVo vo = null;
		long no, user_no, post_no;
		String content, reg_date;
		
		try {
			String sql = "SELECT NO, USER_NO, POST_NO, CONTENT, REG_DATE"
					+ "		FROM COMMENT";
			
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				no = rs.getLong(1);
				user_no = rs.getLong(2);
				post_no = rs.getLong(3);
				content = rs.getString(4);
				reg_date = rs.getString(5);
				
				vo = new CommentVo(no, user_no, post_no, content, reg_date);
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
	
	public boolean insert(CommentVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			String insertSQL = "INSERT INTO COMMENT (NO, USER_NO, POST_NO, CONTENT, REG_DATE)"
					+ "				VALUES (?,?,?,?, date_format(now(), '%Y-%m-%d'))";
			conn = JBlogAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setLong(1, vo.getNo());
			pst.setLong(2, vo.getUser_no());
			pst.setLong(3, vo.getPost_no());
			pst.setString(4, vo.getContent());
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
