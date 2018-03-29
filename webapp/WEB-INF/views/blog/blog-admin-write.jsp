<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin_menu.jsp">
					<c:param name="r" value="write"></c:param>
				</c:import>
				<form action="${pageContext.servletContext.contextPath }/${authUser.id}/admin/write" method="post">
					<input type="hidden" name="blogNo" value="${blog.no }">
					<table class="admin-cat-write">
						<tr>
							<td class="t">제목</td>
							<td><input type="text" size="60" name="title"> <select
								name="categoryNo">
								<c:forEach items="${categoryList}" var="vo">
								<option value="${not empty vo.no? vo.no : 0}">${vo.name }</option>
								</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td class="t">내용</td>
							<td><textarea name="content"></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="s"><input type="submit" value="포스트하기"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>