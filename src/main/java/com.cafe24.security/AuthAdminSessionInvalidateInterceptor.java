package com.cafe24.security;

import com.cafe24.bitmall.vo.UserVo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthAdminSessionInvalidateInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(authUser != null && "admin".equals(authUser.getRole())){
            session.removeAttribute("authUser");
            session.invalidate();
        }
        return true;
    }
}
