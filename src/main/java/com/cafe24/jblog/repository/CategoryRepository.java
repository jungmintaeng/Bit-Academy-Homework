package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getList(Long no){
		return sqlSession.selectList("category.getList", no);
	}
	
	public int insert(CategoryVo vo) {
		return sqlSession.insert("category.insert",vo);
	}
	
	public int delete(Long no) {
		return sqlSession.delete("category.delete", no);
	}
	
	public Long countNull(Long no) {
		return sqlSession.selectOne("category.nullCount", no);
	}
}