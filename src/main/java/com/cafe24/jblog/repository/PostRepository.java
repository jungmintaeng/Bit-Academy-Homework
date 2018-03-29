package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession session;
	
	public List<PostVo> getList(Long no){
		return session.selectList("post.getList", no);
	}
	
	public List<PostVo> getList(Map<String, Long> map){
		return session.selectList("post.getListOfCategory", map);
	}
	
	public PostVo get(Long no) {
		return session.selectOne("post.getPostByNo", no);
	}
	
	public int insert(PostVo vo) {
		return session.insert("post.insert", vo);
	}
}
