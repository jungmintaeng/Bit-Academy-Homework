package com.cafe24.bitmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/util")
public class UtilController {
    @RequestMapping(value = "/zipcode")
    public String zipCode(){
        return "util/zipcode";
    }
}
