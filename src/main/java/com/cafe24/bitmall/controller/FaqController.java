package com.cafe24.bitmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class FaqController {
    @RequestMapping(value = "/list")
    public String list(){
        return "faq/faq";
    }

    @RequestMapping(value = "/view")
    public String view(){
        return "faq/faq_read";
    }
}
