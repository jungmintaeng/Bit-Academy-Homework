<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blog.title }</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title }</h4>
					<p>
						${fn:replace(post.content, newLine, "<br/>") }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${ postList}" var="vo">
						<li><a 
							href="${pageContext.servletContext.contextPath }/${blog.userID}?c=${ empty params.c ? c : params.c}&p=${vo.no}">
						${vo.title}
						</a> <span>${vo.regDate }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
							<c:if test="${empty blog.saveName }">
								<img
									src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
							</c:if>
							<c:if test="${not empty blog.saveName }">
								<img
									src="${pageContext.request.contextPath }${logoURL}">
							</c:if>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="vo">
					<li><a href="${pageContext.servletContext.contextPath }/${blog.userID}?c=${vo.no}&p=0">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>