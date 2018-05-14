package com.cafe24.bitmall.controller;

import com.cafe24.bitmall.vo.QnaVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/qna")
public class QnaController {
    @RequestMapping(value = "/list")
    public String list(){
        return "qna/qa";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String _new(){
        return "qna/qa_new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String _new(
            @ModelAttribute QnaVo vo
    ){
        //TODO : QNA insert
        return "qna/qa_new";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(){
        return "qna/qa_modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(
            @ModelAttribute QnaVo vo
            ){
        //TODO : QNA update
        return "qna/qa_modify";
    }

    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public String reply(){
        return "qna/qa_reply";
    }

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public String reply(
            @ModelAttribute QnaVo vo
    ){
        //TODO : qna insert
        return "qna/qa_reply";
    }

    @RequestMapping(value = "/delete")
    public String delete(
            @RequestParam Long no
    ){
        //TODO : qna delete
        return "";
    }

    @RequestMapping(value = "/view")
    public String view(){
        return "qna/qa_read";
    }
}
