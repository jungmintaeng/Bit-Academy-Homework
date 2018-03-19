package com.cafe24.mvc.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		//forwarding
		RequestDispatcher rd = 
				request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		response.sendRedirect(url);
	}
	
	public static void alert(HttpServletRequest request, HttpServletResponse response, String msg, String redirectURL) throws IOException {
		response.setContentType( "text/html; charset=utf-8" );
		response.getWriter().print(
				"<script type='text/javascript'>" + 
				"		alert('" + msg +  "');" + 
				"	location.href='" + redirectURL + "';" +
				"</script>");
	}
	
	public static String checkParameter(String target, String defaultValue) {
		return target == null ? defaultValue : target;
	}
	
	public static Long checkParameter(String target, Long defaultValue) {
		if(target == null || !isNumeric(target)) {
			return defaultValue;
		}
		
		return Long.parseLong(target);
	}
	
	public static boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public static void selfClose(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
		response.setContentType( "text/html; charset=utf-8" );
		response.getWriter().print(
				"<script type='text/javascript'>" + 
				"		alert('" + msg +  "');" + 
				"	self.close();" +
				"</script>");
	}
}
