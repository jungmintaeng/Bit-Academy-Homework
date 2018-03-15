<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
<link href="/mysite/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<form method="POST" action="/mysite/guestbook">
					<input type="hidden" name="target" value="${param.target}">
					<input type='hidden' name='a' value='delete'>
					<table>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
							<td><input type="submit" value="확인"></td>
						</tr>
					</table>
				</form>
				<a href="/mysite/guestbook?a=list">목록으로 돌아가기</a>	
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="r" value="guestbook"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>