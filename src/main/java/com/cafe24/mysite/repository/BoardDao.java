package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final int ARTICLES_PER_PAGE = 10;
	
	public List<BoardVo> getList(Long pageNo, String kwd){
		kwd = "%" + kwd + "%";
		Map<String, Object> map = new HashMap<>();
		map.put("pageNo", pageNo);
		map.put("kwd", kwd);
		map.put("limit1", Long.valueOf(ARTICLES_PER_PAGE * (pageNo - 1)));
		map.put("limit2", Long.valueOf(ARTICLES_PER_PAGE));
		
		return sqlSession.selectList("board.getList", map);
	}
	
	public Long getCount(String kwd) {
		kwd = "%" + kwd + "%";
		return sqlSession.selectOne("board.getCount", kwd);
	}
	
	public BoardVo get(Long no) {
		return sqlSession.selectOne("board.getByNo", no);
	}
	
	public int insert(BoardVo vo) {
		return sqlSession.insert("board.insert", vo);
	}
	
	public int insertReply(BoardVo vo, Long parent_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("writer_no", vo.getWriter_no());
		map.put("title", vo.getTitle());
		map.put("content", vo.getContent());
		map.put("parent_no", parent_no);
		
		return sqlSession.insert("board.insertReply", map);
	}
	
	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}
	
	public int updateHits(Long no) {
		return sqlSession.update("board.updateHits", no);
	}
	
	public int updateOrderNo(Long parent_no) {
		return sqlSession.update("board.updateOrderNo", parent_no);
	}
	
	public int delete(Long no) {
		return sqlSession.delete("board.delete", no);
	}
}
