package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.GuestBookVo;

public class GuestBookDao {
	/**
	 * 	private long no;
	private String name;
	private String password;
	private String content;
	private String reg_date;
	 */
	public List<GuestBookVo> getList(){
		List<GuestBookVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		GuestBookVo vo = null;
		long no;
		String name, password, content, reg_date;

		try {
			String sql = "SELECT NO, NAME, PASSWORD, CONTENT, DATE_FORMAT(REG_DATE, '%Y-%m-%d %H:%m:%s')"
					+ "		FROM GUESTBOOK"
					+ "		ORDER BY NO DESC";

			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()) {
				no = rs.getLong(1);
				name = rs.getString(2);
				password = rs.getString(3);
				content = rs.getString(4);
				reg_date = rs.getString(5);
				vo = new GuestBookVo(no, name, password, content, reg_date);
				vos.add(vo);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
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

	public GuestBookVo getRowByNo(long no) {
		GuestBookVo vo = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String name, password, content, reg_date;

		try {
			String sql = "SELECT NO, NAME, PASSWORD, CONTENT, DATE_FORMAT(REG_DATE, '%Y-%m-%d %H:%m:%s')"
					+ "		FROM GUESTBOOK"
					+ "		ORDER BY NO DESC";

			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			rs.last();
			long count = rs.getRow();
			if(count < 1)
				return null;
			rs.first();
			no = rs.getLong(1);
			name = rs.getString(2);
			password = rs.getString(3);
			content = rs.getString(4);
			reg_date = rs.getString(5);
			vo = new GuestBookVo(no, name, password, content, reg_date);
			return vo;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
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
	}

	public boolean insert(GuestBookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String insertSQL = "INSERT INTO GUESTBOOK(NO, NAME, PASSWORD, CONTENT, REG_DATE)"
					+ "				VALUES(?,?,?,?, NOW())";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			pst.setNull(1, Types.INTEGER);
			pst.setString(2, vo.getName());
			pst.setString(3, vo.getPassword());
			pst.setString(4, vo.getContent());
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

	public boolean delete(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String deleteSQL = "DELETE FROM GUESTBOOK WHERE NO = ?";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(deleteSQL);
			pst.setLong(1, no);
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
