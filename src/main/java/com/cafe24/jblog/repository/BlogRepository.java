package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo get(String userName) {
		return sqlSession.selectOne("blog.getBlogByUserID", userName);
	}
	
	public int insert(BlogVo vo) {
		sqlSession.insert("blog.insert", vo);
		sqlSession.insert("blog.insertDefaultLogo", vo);
		return sqlSession.insert("blog.updateDefaultLogo", vo);
	}
	
	public int update(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
	}
	
	public int updateLogo(BlogVo vo) {
		return sqlSession.update("blog.uploadLogo", vo);
	}
}
