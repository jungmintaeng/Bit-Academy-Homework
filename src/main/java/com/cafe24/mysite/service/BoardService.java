package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mvc.util.Page;
import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.CommentVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private CommentDao commentDao;
	
	public List<BoardVo> getAllArticle(Long page, String kwd){
		return boardDao.getList(page, kwd);
	}
	
	public BoardVo getArticleByNo(Long no) {
		return boardDao.get(no);
	}
	
	public boolean addArticle(BoardVo vo) {
		boardDao.insert(vo);
		return false;
	}
	
	public boolean deleteArticle(Long no) {
		boardDao.delete(no);
		return false;
	}
	
	public boolean modifyArticle(BoardVo vo) {
		return boardDao.update(vo);
	}
	
	public boolean replyArticle(BoardVo vo, Long parent_no) {
		return boardDao.insertReply(vo, parent_no);
	}
	
	public List<CommentVo> getArticleComments(Long article_no) {
		return commentDao.getList(article_no);
	}
	
	public boolean addComment(CommentVo vo) {
		return commentDao.insert(vo);
	}
	
	public boolean deleteComment(Long no) {
		return commentDao.delete(no);
	}
	
	public void addHits(Long no) {
		boardDao.updateHits(no);
	}
	
	public Page generatePageObject(Long pageNo, String kwd) {
		Page page = new Page(pageNo, kwd);
		if(page.isCurPageValid()) {
			return page;
		}
		return new Page(1, kwd);
	}
}
