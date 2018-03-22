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
<link
	href="${pageContext.servletContext.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:set var="commentCount" value="${fn:length(comment_list)}" />
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
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
				<h2>댓글</h2>
				<div class="comment">
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>작성자</th>
							<th>내용</th>
							<th>작성일</th>
							<th></th>
						</tr>
						<c:forEach items="${comment_list}" var="vo" varStatus="status">
							<tr>
								<td style="text-align: center; border-right: 2px groove black;">${commentCount - status.index}</td>
								<td style="text-align: center; border-right: 2px groove black;">${vo.writer_name}</td>
								<td style="text-align: left; border-right: 2px groove black;">${vo.content}</td>
								<td style="text-align: center;">${vo.reg_date}</td>
								<td><c:if
										test="${not empty authUser && authUser.no == vo.writer_no}">
										<a
											href="${pageContext.servletContext.contextPath}/board/comment_delete?no=${vo.no}&article_no=${vo.article_no }" 
											class="del">삭제</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</table>
					<c:if test="${not empty authUser}">
						<h3>댓글 작성</h3>
						<br />
						<form
							action="${pageContext.servletContext.contextPath}/board/comment_add"
							method="post">
							<input type="hidden" name="writer_no" value="${authUser.no}">
							<input type="hidden" name="article_no" value="${article.no}">
							작성자 : ${authUser.name} <br /> <br /> <label>내용 : </label><br />
							<br /> <input type="text" name="content" value="" size="60">&nbsp;&nbsp;
							<input type="submit" name="submitbtn" value="완료">
						</form>
					</c:if>
				</div>
				<div class="bottom">
					<a
						href="${pageContext.servletContext.contextPath}/board/list?kwd=${param.kwd}">글목록</a>
					<c:if
						test="${not empty authUser && authUser.no == article.writer_no}">
						<a
							href="${pageContext.servletContext.contextPath}/board/modify?no=${article.no}">글수정</a>
					</c:if>
					<c:if test="${not empty authUser}">
						<a
							href="${pageContext.servletContext.contextPath}/board/reply?no=${article.no}">답글
							달기</a>
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