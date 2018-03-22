package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> getAllGuestBooks(){
		return guestBookDao.getList();
	}
	
	public boolean deleleteGuestBook(GuestBookVo inputVo) {
		if(inputVo.getNo() == null || inputVo.getPassword() == null) {
			return false;
		}
		GuestBookVo user = guestBookDao.getRowByNo(inputVo.getNo());
		if(user == null) {
			return false;
		}
		if(inputVo.getPassword().equals(user.getPassword())) {	// 패스워드 일치
			return guestBookDao.delete(inputVo.getNo());
		}
		return false;
	}
	
	public boolean addGuestBook(GuestBookVo vo) {
		return guestBookDao.insert(vo);
	}
}
