package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.BoardVo;

public class BoardDao {
	private static final int ARTICLES_PER_PAGE = 10;
	
	public List<BoardVo> getList(int pageNo){
		List<BoardVo> vos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BoardVo vo = null;
		long no, writer_no, hits, group_no, order_no, depth;
		String writer_name, title, content, reg_date;
		
		try {
			String sql = "SELECT ARTICLE.NO, WRITER_NO, NAME, TITLE, CONTENT, HITS, DATE_FORMAT(REG_DATE, '%Y-%m-%d %H:%m:%s'), GROUP_NO, ORDER_NO, DEPTH"
					+ "		FROM ARTICLE"
					+ "		JOIN USERS ON ARTICLE.WRITER_NO=USERS.NO"
					//+ "		GROUP BY GROUP_NO"
					+ "		ORDER BY ORDER_NO ASC"
					+ "		LIMIT ?,?";

			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, ARTICLES_PER_PAGE * (pageNo - 1));
			pst.setInt(2, ARTICLES_PER_PAGE);
			rs = pst.executeQuery();

			while(rs.next()) {
				no = rs.getLong(1);
				writer_no = rs.getLong(2);
				writer_name = rs.getString(3);
				title = rs.getString(4);
				content = rs.getString(5);
				hits = rs.getLong(6);
				reg_date = rs.getString(7);
				group_no = rs.getLong(8);
				order_no = rs.getLong(9);
				depth = rs.getLong(10);
				
				vo = new BoardVo();
				vo.setNo(no);
				vo.setWriter_no(writer_no);
				vo.setWriter_name(writer_name);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setHits(hits);
				vo.setReg_date(reg_date);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
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
	
	public BoardVo get(long no) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BoardVo vo = null;
		long writer_no, hits, group_no, order_no, depth;
		String writer_name, title, content, reg_date;
		
		try {
			String sql = "SELECT ARTICLE.NO, WRITER_NO, NAME, TITLE, CONTENT, HITS, DATE_FORMAT(REG_DATE, '%Y-%m-%d %H:%m:%s'), GROUP_NO, ORDER_NO, DEPTH"
					+ "		FROM ARTICLE"
					+ "		JOIN USERS ON ARTICLE.WRITER_NO=USERS.NO"
					+ "		WHERE ARTICLE.NO=?";

			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(sql);
			pst.setLong(1, no);
			rs = pst.executeQuery();

			if(rs.next()) {
				no = rs.getLong(1);
				writer_no = rs.getLong(2);
				writer_name = rs.getString(3);
				title = rs.getString(4);
				content = rs.getString(5);
				hits = rs.getLong(6);
				reg_date = rs.getString(7);
				group_no = rs.getLong(8);
				order_no = rs.getLong(9);
				depth = rs.getLong(10);
				
				vo = new BoardVo();
				vo.setNo(no);
				vo.setWriter_no(writer_no);
				vo.setWriter_name(writer_name);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setHits(hits);
				vo.setReg_date(reg_date);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
			}
			
			return vo;
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
		return vo;
	}
	
	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String insertSQL = "INSERT INTO ARTICLE(NO, WRITER_NO, TITLE, CONTENT, HITS, REG_DATE, GROUP_NO, ORDER_NO, DEPTH)"
					+ "				VALUES (?, ?, ?, ?, 0, now(), "
					+ "IFNULL ((SELECT DISTINCT MAX(GROUP_NO) FROM ARTICLE A), 0) + 1, 1, 0)";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			
			pst.setNull(1,  Types.INTEGER);
			pst.setLong(2,  vo.getWriter_no());
			pst.setString(3,  vo.getTitle());
			pst.setString(4,  vo.getContent());
			
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
	
	public boolean insertReply(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String insertSQL = "INSERT INTO ARTICLE(NO, WRITER_NO, TITLE, CONTENT, HITS, REG_DATE, GROUP_NO, ORDER_NO, DEPTH)"
					+ "				VALUES (?, ?, ?, ?, ?, DATE_FORMAT(NOW(), '%Y-%m-%d %H:%m:%s'), ?, ?, ?)";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(insertSQL);
			
			pst.setNull(1,  Types.INTEGER);
			pst.setLong(2,  vo.getWriter_no());
			pst.setString(3,  vo.getTitle());
			pst.setString(4,  vo.getContent());
			pst.setLong(5,  vo.getHits());
			pst.setLong(6,  vo.getGroup_no());
			pst.setLong(7,  vo.getOrder_no());
			pst.setLong(8,  vo.getDepth());
			
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
	
	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String updateSQL = "UPDATE ARTICLE SET TITLE=?, CONTENT=?"
					+ "				WHERE NO=?";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(updateSQL);
			
			pst.setString(1,  vo.getTitle());
			pst.setString(2,  vo.getContent());
			pst.setLong(3,  vo.getNo());
			
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
	
	public void updateOrderNo(long startNo) {
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String updateSQL = "UPDATE ARTICLE SET ORDER_NO=ORDER_NO+1"
					+ "					WHERE ORDER_NO >= ?";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(updateSQL);
			pst.setLong(1,  startNo);
			pst.executeUpdate();
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
	}
	
	public boolean updateHits(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String updateSQL = "UPDATE ARTICLE SET HITS=HITS+1"
					+ "					WHERE NO=?";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(updateSQL);
			pst.setLong(1,  no);
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
	
	public boolean delete(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			String deleteSQL = "DELETE FROM ARTICLE"
					+ "				  WHERE NO=?";
			conn = WebDBAccess.getInstance().getConnection();
			pst = conn.prepareStatement(deleteSQL);
			pst.setLong(1,  no);
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
