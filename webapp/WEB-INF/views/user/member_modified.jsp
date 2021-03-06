<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
	<title>비트닷컴 쇼핑몰</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${cPath}/assets/css/common.css" rel="stylesheet" type="text/css">
	<style>
		@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	</style>
</head>
<body style="margin:0">
<div class="container">
	<jsp:include page="/WEB-INF/views/include/head.jsp"/>
	<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>

	<table width="1200" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr><td height="10" colspan="2"></td></tr>
		<tr>
			<td height="100%" valign="top">
			</td>
			<td width="10"></td>
			<td valign="top">

				<!-------------------------------------------------------------------------------------------->
				<!-- 시작 : 다른 웹페이지 삽입할 부분                                                       -->
				<!-------------------------------------------------------------------------------------------->

				<table border="0" cellpadding="0" cellspacing="0" width="1200">
					<tr><td height="13"></td></tr>
					<tr>
						<td height="30" align="center">
							<img src="${pageContext.servletContext.contextPath }/assets/images/login_title.gif" width="747" height="30" border="0">
						</td>
					</tr>
					<tr><td height="13"></td></tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="1200" class="cmfont">
					<tr>
						<td><img src="${pageContext.servletContext.contextPath }/assets/images/member_edit.gif" border="0"></td>
					</tr>
					<tr><td height="15"></td></tr>
				</table>

				<table border="0" cellpadding="5" cellspacing="1" width="1200" bgcolor="cccccc">
					<tr>
						<td align="center" bgcolor="efefef">
							<table border="0" cellpadding="0" cellspacing="5" width="100%" bgcolor="white">
								<tr>
									<td align="center">
										<table border="0" cellpadding="0" cellspacing="0" width="1200">
											<tr>
												<td height="30">
													<font size="3" color="898989"><b>Congratulation !!!</b></font>
												</td>
											</tr>
											<tr><td colspan="2" height="10"></td></tr>
											<tr><td colspan="2" bgcolor="E6DDD5"></td></tr>
											<tr><td colspan="2" height="10"></td></tr>
										</table>
										<table border="0" cellpadding="0" cellspacing="0" width="1200" class="cmfont">
											<tr>
												<td height="30">
													<font color="898989">수정완료</font><br><br><br>
												</td>
											</tr>
										</table>

									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<!-------------------------------------------------------------------------------------------->
				<!-- 끝 : 다른 웹페이지 삽입할 부분                                                         -->
				<!-------------------------------------------------------------------------------------------->

			</td>
		</tr>
	</table>

	<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>