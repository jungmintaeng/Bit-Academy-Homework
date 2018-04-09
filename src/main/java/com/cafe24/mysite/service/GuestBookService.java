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
	
	public boolean deleleteGuestBook(GuestBookVo vo) {
		return guestBookDao.delete(vo);
	}
	
	public boolean deleteGuestBookByNo(Long no) {
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		return guestBookDao.delete(vo);
	}
	
	public boolean addGuestBook(GuestBookVo vo) {
		return guestBookDao.insert(vo);
	}
	
	public GuestBookVo addAndReturnGuestBook(GuestBookVo vo) {
		if(addGuestBook(vo)) {
			return guestBookDao.getByNo(vo);
		}
		
		return null;
	}
	
	public List<GuestBookVo> getLimitedGuestBookList(Long lastNo){
		return guestBookDao.getLimitedList(lastNo);
	}
}
