package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogRepository;
import com.cafe24.jblog.repository.UserRepository;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	public UserVo getUser(String id, String password) {
		return userRepository.get(id, password);
	}
	
	public boolean join(UserVo vo) {
		boolean result = userRepository.insert(vo) == 1;
		BlogVo blogVo = new BlogVo();
		blogVo.setUserNo(vo.getNo());
		blogRepository.insert(blogVo);
		return result;
	}
}
