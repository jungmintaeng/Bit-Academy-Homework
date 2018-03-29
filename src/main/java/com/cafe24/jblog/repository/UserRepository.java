package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(String id, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		return sqlSession.selectOne("user.getUserByIdAndPassword", map);
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}
}
