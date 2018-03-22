package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.CommentVo;

@Repository
public class CommentDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommentVo> getList(Long article_no){		
		return sqlSession.selectList("comment.getList", article_no);
	}
	
	public int insert(CommentVo vo) {
		return sqlSession.insert("comment.insert", vo);
	}
	
	public int delete(Long no) {
		return sqlSession.delete("comment.delete", no);
	}
}
