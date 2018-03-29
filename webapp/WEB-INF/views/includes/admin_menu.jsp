<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<ul class="admin-menu">
	<c:if test="${not empty authUser && authUser.no == blog.userNo}">
		<c:if test="${param.r ==  'basic'}">
			<li class="selected">기본설정</li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/write">글작성</a></li>
		</c:if>
		<c:if test="${param.r ==  'category'}">
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
			<li class="selected">카테고리</li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/write">글작성</a></li>
		</c:if>
		<c:if test="${param.r ==  'write'}">
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
			<li class="selected">글작성</li>
		</c:if>
	</c:if>
</ul>