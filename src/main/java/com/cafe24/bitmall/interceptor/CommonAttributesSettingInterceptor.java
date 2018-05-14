package com.cafe24.bitmall.interceptor;

import com.cafe24.bitmall.service.CategoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonAttributesSettingInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ApplicationContext ac =
                WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        CategoryService categoryService = ac.getBean(CategoryService.class);
        try{
            request.setAttribute("categoryList", categoryService.getList());
        }catch (Exception e){
            //throw new CategoryRepositoryException("Exception Occurred in Interceptor");
        }
        return true;
    }
}
