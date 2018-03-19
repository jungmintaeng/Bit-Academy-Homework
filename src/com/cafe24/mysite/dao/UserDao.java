package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cafe24.mysite.vo.UserVo;

public class UserDao {
	/**
	 * 회원 정보 수정 시 미리 세팅할 유저 정보
	 * @param no
	 * @return
	 */
	public UserVo get(Long no) {
		UserVo result = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = WebDBAccess.getInstance().getConnection();
			String sql = "SELECT NAME, GENDER"
					+ "		FROM USERS"
					+ "		WHERE NO=?";
			
			pst = conn.prepareStatement(sql);
			pst.setLong(1,  no);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				result = new UserVo();
				result.setName(rs.getString(1));
				result.setGender(rs.getString(2));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
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
	
	/**
	 * Login시 가져올 유저 정보
	 * @param email - UserID
	 * @param password - UserPassword
	 * @return UserVo
	 */
	public UserVo get(String email, String password) {
		UserVo result = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Long no;
		String name;
		
		try {
			conn = WebDBAccess.getInstance().getConnection();
			String sql = "SELECT NO, NAME"
					+ "		FROM USERS"
					+ "		WHERE EMAIL=?"
					+ "					AND PASSWORD=PASSWORD(?)";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				result = new UserVo();
				no = rs.getLong(1);
				name = rs.getString(2);
				result.setNo(no);
				result.setName(name);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
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
	
	public boolean isEmailExists(String email) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = WebDBAccess.getInstance().getConnection();
			String sql = "SELECT EXISTS(" + 
					"	SELECT NO" + 
					"	FROM USERS" + 
					"	WHERE EMAIL=?" + 
					")";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1,  email);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				result = rs.getBoolean(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
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
	
	/**
	 * 회원 가입 시 회원 정보를 DB에 넣는 메소드
	 * @param vo
	 * @return
	 */
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = WebDBAccess.getInstance().getConnection();
			String sql = "INSERT INTO USERS(NO, NAME, EMAIL, PASSWORD, GENDER, JOIN_DATE)"
					+ "	  SELECT *"
					+ "	  	FROM (SELECT NULL, ?,?,PASSWORD(?), ?, NOW()"
					+ "			) tmp"
					+ "		WHERE NOT EXISTS("
					+ "			SELECT EMAIL FROM USERS WHERE EMAIL=?"
					+ "		) LIMIT 1";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, vo.getName());
			pst.setString(2, vo.getEmail());
			pst.setString(3, vo.getPassword());
			pst.setString(4, vo.getGender());
			pst.setString(5, vo.getEmail());
			
			result = pst.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
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
	
	/**
	 * 회원의 정보를 수정하는 메소드
	 */
	public boolean update(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = WebDBAccess.getInstance().getConnection();
			if(vo.getPassword() == null || vo.getPassword() == "") {
				String sql = "UPDATE USERS"
						+ "		SET NAME=?, GENDER=?"
						+ "		WHERE NO=?";
				
				pst = conn.prepareStatement(sql);
				pst.setString(1,  vo.getName());
				pst.setString(2,  vo.getGender());
				pst.setLong(3,  vo.getNo());
				
				result = pst.executeUpdate() > 0;
			}else {
				String sql = "UPDATE USERS"
						+ "		SET NAME=?, PASSWORD=PASSWORD(?), GENDER=?"
						+ "		WHERE NO=?";
				
				pst = conn.prepareStatement(sql);
				pst.setString(1,  vo.getName());
				pst.setString(2,  vo.getPassword());
				pst.setString(3,  vo.getGender());
				pst.setLong(4,  vo.getNo());
				
				result = pst.executeUpdate() > 0;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
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
