<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="navigation">
	<ul>
		<c:choose>
			<c:when test="${param.r == 'main'}">
				<li class="selected"><a href="/mysite/main">메인 페이지</a></li>
				<li><a href="/mysite/guestbook">방명록</a></li>
				<li><a href="/mysite/board">게시판</a></li>
			</c:when>
			<c:when test="${param.r == 'guestbook'}">
				<li><a href="/mysite/main">메인 페이지</a></li>
				<li class="selected"><a href="/mysite/guestbook">방명록</a></li>
				<li><a href="/mysite/board">게시판</a></li>
			</c:when>
			<c:when test="${param.r == 'board'}">
				<li><a href="/mysite/main">메인 페이지</a></li>
				<li><a href="/mysite/guestbook">방명록</a></li>
				<li class="selected"><a href="/mysite/board">게시판</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/mysite/main">메인 페이지</a></li>
				<li><a href="/mysite/guestbook">방명록</a></li>
				<li><a href="/mysite/board">게시판</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>