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
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script type="text/javascript">
function readURL(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    reader.readAsDataURL(input.files[0]);
	    reader.onload = function(e) {
		      $('#logoImg').attr('src', e.target.result);
		    }
	  }
}
$(document).ready(function(){
	$('#logoInput').change(function() {
		  readURL(this);
	});
});
</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin_menu.jsp">
					<c:param name="r" value="basic"></c:param>
				</c:import>
				<form
					action="${pageContext.servletContext.contextPath }/${authUser.name}/admin/basic"
					method="post" enctype="multipart/form-data">
					<input type="hidden" name="no" value="${blog.no }"> <input
						type="hidden" name="logoNo" value="${blog.logoNo }">
					<table class="admin-config">
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="title"
								value="${blog.title }"></td>
						</tr>
						<tr>
							<td class="t">로고이미지</td>
							<c:if test="${empty blog.saveName }">
								<td><img id="logoImg"
									src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"></td>
							</c:if>
							<c:if test="${not empty blog.saveName }">
								<td><img id="logoImg"
									src="${pageContext.request.contextPath }${logoURL}"></td>
							</c:if>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input id="logoInput" type="file" name="logo-file"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input type="submit" value="기본설정 변경"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>