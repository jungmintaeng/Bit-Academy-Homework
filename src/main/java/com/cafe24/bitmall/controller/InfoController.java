package com.cafe24.bitmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
    @RequestMapping(value = "/company")
    public String company(){
        return "info/company";
    }

    @RequestMapping(value = "/policy")
    public String policy(){
        return "info/policy";
    }

    @RequestMapping(value = "/useinfo")
    public String useInfo(){
        return "info/useinfo";
    }
}
