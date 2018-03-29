package com.cafe24.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.PostRepository;
import com.cafe24.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getPostList(Long no){
		return postRepository.getList(no);
	}
	
	public List<PostVo> getPostList(Long blogNo, Long categoryNo){
		Map<String, Long> map = new HashMap<>();
		map.put("blogNo", blogNo);
		map.put("categoryNo", categoryNo);
		return postRepository.getList(map);
	}
	
	public PostVo getPostByNo(Long no) {
		return postRepository.get(no);
	}
	
	public boolean addPost(PostVo vo) {
		return postRepository.insert(vo) == 1;
	}
}
