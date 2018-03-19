package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.CommentVo;

public class CommentDao {
	
	public List<CommentVo> getList(long article_no){		
		List<CommentVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CommentVo vo = null;
		
		try {
			String sql = "SELECT COMMENT.NO, ARTICLE_NO, WRITER_NO, USERS.NAME WRITER_NAME, DATE_FORMAT(REG_DATE, '%Y-%m-%d %h:%i:%s'), CONTENT"
					+ "	  	FROM COMMENT"
					+ "		JOIN USERS ON COMMENT.WRITER_NO=USERS.NO"
					+ "	   WHERE ARTICLE_NO=?"
					+ "	ORDER BY COMMENT.NO DESC";

			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			pst.setLong(1, article_no);
			rs = pst.executeQuery();

			while(rs.next()) {
				vo = new CommentVo();
				vo.setNo(rs.getLong(1));
				vo.setArticle_no(rs.getLong(2));
				vo.setWriter_no(rs.getLong(3));
				vo.setWriter_name(rs.getString(4));
				vo.setReg_date(rs.getString(5));
				vo.setContent(rs.getString(6));
				vos.add(vo);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println("comment select list err");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null && !rs.isClosed())
					rs.close();
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
				if(conn != null && !conn.isClosed())
					conn.close();
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
			String insertSQL = "INSERT INTO COMMENT(NO, ARTICLE_NO, WRITER_NO, REG_DATE, CONTENT)"
					+ "			VALUES (NULL, ?, ?, NOW(), ?)";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setLong(1, vo.getArticle_no());
			pst.setLong(2,  vo.getWriter_no());
			pst.setString(3, vo.getContent());
			result = pst.executeUpdate() > 0;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("insert err");
			e.printStackTrace();
		} finally {
			try {
				if(pst != null && !pst.isClosed())
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean delete(long target) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String insertSQL = "DELETE FROM COMMENT WHERE NO=?";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setLong(1, target);
			result = pst.executeUpdate() > 0;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null && !pst.isClosed())
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
