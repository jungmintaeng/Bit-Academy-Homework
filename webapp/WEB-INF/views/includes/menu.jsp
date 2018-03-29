<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<h1 class="logo">JBlog</h1>
<ul class="menu">
	<c:if test="${empty authUser}">
		<li><a href="${pageContext.servletContext.contextPath}/user/auth">로그인</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/user/join">회원가입</a></li>
	</c:if>
	<c:if test="${not empty authUser}">
		<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}">내블로그</a></li>
	</c:if>
</ul>