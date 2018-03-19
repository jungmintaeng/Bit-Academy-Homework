<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
<style>
</style>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd}"> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" varStatus="status" var="vo">
						<tr>
							<td>${pageObj.itemCount - ((pageObj.curPage - 1) * pageObj.items_per_page) - status.index}</td>
							<td style="text-align:left;padding-left:${vo.depth * 20}px;">
								<c:if test="${vo.depth > 0}">
									<img alt="reply" src="/mysite/assets/images/reply.png">
								</c:if> <a href="/mysite/board?a=view&target=${vo.no}&kwd=${kwd}">${fn:replace(vo.title, newLine, "<br/>")}</a>
							</td>
							<td>${vo.writer_name}</td>
							<td>${vo.hits}</td>
							<td>${vo.reg_date}</td>
							<td><c:if
									test="${not empty authUser && authUser.no == vo.writer_no}">
									<a href="/mysite/board?a=delete&target=${vo.no}" class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:if test="${pageObj.left}">
							<li><a href="/mysite/board?a=list&page=${pageObj.startNo - pageObj.pages_per_context}">◀</a></li>
						</c:if>
						<c:if test="${not pageObj.left}">
							<li>◀</li>
						</c:if>
						<c:forEach begin="${pageObj.startNo}" end="${pageObj.endNo}"
							var="i" step="1">
							<c:choose>
								<c:when
									test="${i==pageObj.curPage && i > 0 && pageObj.totalPage >= i}">
									<li class="selected">${i}</li>
								</c:when>
								<c:when test="${i > 0 && pageObj.totalPage >= i}">
									<li><a href="/mysite/board?a=list&page=${i}&kwd=${kwd}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li>${i}</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${pageObj.right}">
							<li><a href="/mysite/board?a=list&page=${pageObj.startNo + pageObj.pages_per_context}">▶</a></li>
						</c:if>
						<c:if test="${not pageObj.right}">
							<li>▶</li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser}">
						<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="r" value="board"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>