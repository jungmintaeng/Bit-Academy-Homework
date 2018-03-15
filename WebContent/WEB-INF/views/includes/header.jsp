<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <% 
    	UserVo authUser = (UserVo)session.getAttribute("authUser");
    %>
		<div id="header">
			<h1><a href="/mysite/main">MySite</a></h1>
			<ul>
				<c:if test="${empty authUser}">
					<li><a href="/mysite/user?a=loginform">로그인</a></li>
				</c:if>
				<c:if test="${not empty authUser}">
					<li><a href="/mysite/user?a=logout">로그아웃</a></li>
				</c:if>
				<c:if test="${empty authUser}">
					<li><a href="/mysite/user?a=joinform">회원가입</a></li>
				</c:if>
				<c:if test="${not empty authUser}">
					<li><a href="/mysite/user?a=modifyform">회원정보수정</a></li>
					<li>${authUser.name}님 안녕하세요 ^^;</li>
				</c:if>
			</ul>
		</div>