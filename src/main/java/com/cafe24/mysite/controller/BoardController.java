package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.CommentVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list")
	public String list(
			@RequestParam(value="page", required=true, defaultValue="1") Long pageNo,
			@RequestParam(value="kwd", required=true, defaultValue="") String kwd,
			Model model
			) {
		System.out.println("page : " + pageNo + " kwd : " + kwd);
		System.out.println(boardService.generatePageObject(pageNo, kwd));
		model.addAttribute("list", boardService.getAllArticle(pageNo, kwd));
		model.addAttribute("pageObj", boardService.generatePageObject(pageNo, kwd));
		return "board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo) {
		boardService.addArticle(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(
				@RequestParam(value="no", required=true, defaultValue="0") Long no
				) {
		boardService.deleteArticle(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam(value="no", required=true, defaultValue="0") Long no) {
		boardService.getArticleByNo(no);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo) {
		boardService.modifyArticle(vo);
		return "redirect:/board/view?no=" + vo.getNo();
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply(
				@RequestParam(value="no", required=true, defaultValue="0") Long parent_no,
				Model model
			) {
		model.addAttribute("original", boardService.getArticleByNo(parent_no));
		return "board/write";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(
				@ModelAttribute BoardVo vo,
				@RequestParam(value="parent_no", required=true, defaultValue="0") Long parent_no
			) {
		boardService.replyArticle(vo, parent_no);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/view")
	public String view(
				@RequestParam(value="no", required=true, defaultValue="0") Long no,
				Model model
			) {
		model.addAttribute("article", boardService.getArticleByNo(no));
		model.addAttribute("comment_list", boardService.getArticleComments(no));
		boardService.addHits(no);
		return "board/view";
	}
	
	@RequestMapping(value="/comment_add")
	public String commentAdd(
				@ModelAttribute CommentVo vo
			) {
		boardService.addComment(vo);
		return "redirect:/board/view?no=" + vo.getArticle_no();
	}
	
	@RequestMapping(value="/comment_delete")
	public String commentDelete(
				@RequestParam(value="no", required=true, defaultValue="0") Long no,
				@RequestParam(value="article_no", required=true, defaultValue="0") Long article_no
			) {
		boardService.deleteComment(no);
		return "redirect:/board/view?no=" + article_no;
	}
}
