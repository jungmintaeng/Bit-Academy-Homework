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

	<table width="1200" border="0" cellspacing="0" cellpadding="0" align="center" style="margin: 0 auto;">
		<tr><td height="10" colspan="2"></td></tr>
		<tr>
			<td height="100%" valign="top">
			</td>
			<td width="10"></td>
			<td valign="top">

				<!-------------------------------------------------------------------------------------------->
				<!-- 시작 : 다른 웹페이지 삽입할 부분                                                       -->
				<!-------------------------------------------------------------------------------------------->

				<table border="0" cellpadding="0" cellspacing="0" width="747">
					<tr><td height="13"></td></tr>
					<tr>
						<td height="30"><img src="${pageContext.servletContext.contextPath }/assets/images/faq_title.gif" width="746" height="30" border="0"></td>
					</tr>
					<tr><td height="13"></td></tr>
				</table>

				<table border="0" cellpadding="0" cellspacing="0" width="690">
					<tr>
						<td height="30"><img src="${pageContext.servletContext.contextPath }/assets/images/faq_title1.gif" border="0"></td>
					</tr>
				</table>

				<table border="0" cellpadding="0" cellspacing="0" width="690" class="cmfont">
					<tr><td colspan="2" height="3" bgcolor="#FFCC00"></td></tr>
					<tr bgcolor="#FFF5D0" >
						<td width="90" height="25" align="center">번호</td>
						<td align="center">제목</td>
					</tr>

					<tr><td height="1" bgcolor="#DEDCDD"colspan="4"></td></tr>
					<tr height="25" bgcolor="#FFFFFF">
						<td width="90" align="center"><font color="#686868">1</font></td>
						<td><a href="faq_read.jsp?no=1"><font color="#686868">자주 하는 질문 1</font></a></td>
					</tr>
					<tr><td height="1" bgcolor="#DEDCDD"colspan="4"></td></tr>
					<tr height="25" bgcolor="#FFFFFF">
						<td width="90" align="center"><font color="#686868">2</font></td>
						<td><a href="faq_read.jsp?no=1"><font color="#686868">자주 하는 질문 2</font></a></td>
					</tr>

					<tr><td height="1" bgcolor="#DEDCDD"colspan="4"></td></tr>
					<tr height="25" bgcolor="#FFFFFF">
						<td width="90" align="center"></td>
						<td></td>
					</tr>
					<tr><td height="1" bgcolor="#DEDCDD"colspan="4"></td></tr>
					<tr height="25" bgcolor="#FFFFFF">
						<td width="90" align="center"></td>
						<td></td>
					</tr>
					<tr><td height="1" bgcolor="#DEDCDD"colspan="4"></td></tr>
					<tr height="25" bgcolor="#FFFFFF">
						<td width="90" align="center"></td>
						<td></td>
					</tr>
					<tr><td height="1" bgcolor="#DEDCDD"colspan="4"></td></tr>
					<tr height="25" bgcolor="#FFFFFF">
						<td width="90" align="center"></td>
						<td></td>
					</tr>
					<tr><td height="1" bgcolor="#DEDCDD"colspan="4"></td></tr>
					<tr height="25" bgcolor="#FFFFFF">
						<td width="90" align="center"></td>
						<td></td>
					</tr>

					<tr><td colspan="2" height="2" bgcolor="#FFCC00"></td></tr>
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