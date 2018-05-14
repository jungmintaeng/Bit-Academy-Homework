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
				<!-- 시작 : 다른 웹페이지 삽입할 부분                                                                                                                                                            -->
				<!-------------------------------------------------------------------------------------------->

				<!---- 화면 우측(로그인) S -------------------------------------------------->
				<table border="0" cellpadding="0" cellspacing="0" width="1200">
					<tr><td height="13"></td></tr>
					<tr>
						<td height="30" align="center">
							<img src="${cPath}/assets/images/login_title.gif" width="747" height="30" border="0">
						</td>
					</tr>
					<tr><td height="47"></td></tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="1200">
					<tr>
						<td width="747" align="center" valign="top">
							<table border="0" cellpadding="0" cellspacing="8" width="523" bgcolor="E9E9E9">
								<tr>
									<td height="210" align="center" bgcolor="white">
										<table border="0" height="120" cellpadding="0" cellspacing="0" width="440">
											<tr>
												<td width="120" align="center"><img src="${cPath}/assets/images/login_image1.gif" width="110" height="90" border="0"></td>
												<td width="320">
													<table border="0" cellpadding="0" cellspacing="0" width="320">
														<tr>
															<td height="35">
																<p style="padding-left:10px;"><img src="${cPath}/assets/images/login_image2.gif" width="220" height="21" border="0"></p>
															</td>
														</tr>
													</table>
													<table border="0" cellpadding="0" cellspacing="0" width="320">
														<!-- form2 시작 ------>
														<form name = "form2" method = "post" action = "${cPath}/user/auth">
															<input type="hidden" name="role" value="user">
															<tr>
																<td width="220" height="25">
																	<p style="padding-left:10px;">
																		<img align="absmiddle" src="${cPath}/assets/images/login_id.gif" width="40" height="13" border="0">
																		<input type="text" autocomplete="off" name="id" size="20" maxlength="12" class="cmfont1">
																	</p>
																</td>
																<td width="100" rowspan="2">
																	<input type="image" align="absmiddle" src="${cPath}/assets/images/b_login.gif" width="50" border="0">
																</td>
															</tr>
															<tr>
																<td width="220" height="25">
																	<p style="padding-left:10px;">
																		<img align="absmiddle" src="${cPath}/assets/images/login_pw.gif" width="40" height="13" border="0">
																		<input type="password" name="password" size="20" maxlength="12" class="cmfont1">
																	</p>
																</td>
															</tr>
														</form>
														<!--form2 끝 ------>
													</table>
												</td>
											</tr>
										</table>
										<table border="0" cellpadding="0" cellspacing="0" width="512">
											<tr><td height="15"></td></tr>
											<tr><td height="2" bgcolor="E9E9E9"></td></tr>
											<tr><td height="15"></td></tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<!-------------------------------------------------------------------------------------------->
				<!-- 끝 : 다른 웹페이지 삽입할 부분                                                                                                                                                              -->
				<!-------------------------------------------------------------------------------------------->

			</td>
		</tr>
	</table>
	<br><br><br>
	<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>