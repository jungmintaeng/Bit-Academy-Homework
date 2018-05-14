package com.cafe24.bitmall.controller;

import com.cafe24.bitmall.service.UserService;
import com.cafe24.bitmall.vo.UserVo;
import com.cafe24.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Join Methods
     */
    @RequestMapping(value = "/join/agree", method = RequestMethod.GET)
    public String agree() {
        return "user/member_agree";
    }

    @RequestMapping(value = "/join/form", method = RequestMethod.POST)
    public String form(
            @RequestParam(value = "agree") String agree
    ) {
        if("ok".equals(agree))
            return "user/member_join";

        return "user/member_agree";
    }

    @RequestMapping(value = "/join/result")
    public String result(
            @ModelAttribute UserVo vo
    ) {
        vo.setRole("user");
        if(userService.join(vo)){
            return "user/member_join_success";
        }

        return "user/member_join";
    }

    /**
     * Login Methods
     */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/member_login";
    }

    /**
     * Modify Methods
     */

    @Auth
    @RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
    public String modify(
            @PathVariable("no") Long no,
            Model model
    ){
        model.addAttribute("user", userService.getUserByNo(no));
        return "user/member_modify";
    }

    @Auth
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(
            @ModelAttribute UserVo vo,
            Model model
    ){
        if(userService.modify(vo))
            return "user/member_modified";
        model.addAttribute("user", vo);
        return "user/member_modify";
    }

    /**
     * Admin Login
     */

    @RequestMapping(value = "/login/admin", method = RequestMethod.GET)
    public String adminLogin() {
        return "admin/login";
    }
}
