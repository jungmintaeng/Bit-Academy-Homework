package com.cafe24.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/board")
public class AuthUserAccessFilter implements Filter {

    public AuthUserAccessFilter() {
		// Noting
    }

	public void destroy() {
		// Noting
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/*		HttpSession session =  ((HttpServletRequest)request).getSession(false);
		System.out.println(session.getAttribute("authUser"));
		if(session == null || session.getAttribute("authUser") == null){
			WebUtil.alert((HttpServletRequest)request, (HttpServletResponse)response, "인증되지 않은 유저입니다.", "/mysite/main");
			return;
		}*/
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// Noting
	}

}
