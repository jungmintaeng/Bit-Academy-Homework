package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		// @AuthUser가 붙어 있고 파라미터 타입이 UserVo라는 것을 확인 완료

		HttpServletRequest request = webRequest.getNativeRequest( HttpServletRequest.class );
		HttpSession session = request.getSession();

		if (session == null) {
			return null;
		}
		UserVo vo = (UserVo)session.getAttribute("authUser");
		return vo;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// @AuthUser가 붙어 있는지? (HandlerMethod의 Parameter에)
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

		// @AuthUser가 안붙어있는경우
		if (authUser == null) {
			return false;
		}

		// Type이 UserVo가 아닌 경우
		if (parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}
		return true;
	}

}
