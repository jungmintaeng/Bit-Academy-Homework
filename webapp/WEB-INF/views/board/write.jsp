<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<c:if test="${not empty no}">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글보기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td>${article.title}</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<div class="view-content">${article.content}</div>
							</td>
						</tr>
					</table>
				</c:if>
				<c:set var="write" value="write"/>
				<c:set var="reply" value="reply"/>
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath}/board/${empty no? write : reply}">
					<table class="tbl-ex">
						<tr>
							<th colspan="2"><c:if test="${empty no}">
									글쓰기
								</c:if> <c:if test="${not empty no}">
									<input type="hidden" name="parent_no" value="${no}">
									답글 쓰기
								</c:if></th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content"></textarea></td>
						</tr>
					</table>
					<input type='hidden' name="writer_no" value="${authUser.no}">
					<div class="bottom">
						<c:if test="${empty no}">
							<a href="${pageContext.servletContext.contextPath}/board/list">취소</a>
							<input type="submit" value="등록">
						</c:if>
						<c:if test="${not empty no}">
							<a href="${pageContext.servletContext.contextPath}/board/view?no=${no}">취소</a>
							<input type="submit" value="등록">
						</c:if>

					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="r" value="board"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>