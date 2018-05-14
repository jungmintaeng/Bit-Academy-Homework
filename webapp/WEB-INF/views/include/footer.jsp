<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<table class="no-drag" width="959" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
	<tr> 
		<td background="${cPath}/assets/images/footer_bg.gif" height="11"></td>
	</tr>
	<tr><td height="5"></td></tr>
	<tr> 
		<td> 
			<table width="1200" border="0" cellspacing="0" cellpadding="0">
				<tr> 
					<td valign="top">
						<a class="logo" href="${cPath}">
							<p>
								MUSIC<span>24</span>
							</p>
						</a>
						<div class="service-menu">
							<a href="${cPath}/qna/list">Q&A</a>
							<img src="${cPath}/assets/images/footer_line.gif">
							<a href="${cPath}/faq/list">FAQ</a>
						</div>
					</td>
					<td width="28"></td>
					<td> 
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<a href="${cPath}/info/company"><img src="${cPath}/assets/images/footer_menu01.gif" border="0"></a>
											<img src="${cPath}/assets/images/footer_line.gif">
											<a href="${cPath}/info/useinfo"><img src="${cPath}/assets/images/footer_menu02.gif" border="0"></a>
											<img src="${cPath}/assets/images/footer_line.gif">
											<a href="${cPath}/info/policy"><img src="${cPath}/assets/images/footer_menu03.gif" border="0"></a>
										</tr>
										<tr>
											&nbsp;
										</tr>
									</table>
								</td>
							</tr>
							<tr> 
								<td class="company-description">
									<strong>MUSIC 24(주)</strong>
									<br>
									<p>
										인천광역시 계양구 봉오대로 531번길 13-9<br>
										대표 : 신정민 &nbsp; 개인정보보호책임자 : 신정민 jungmin4025@gmail.com<br>
										사업자등록번호 : 223-12-2145124<br> 통신판매업신고 : 제 2010-12302호
										<span>Copyright ⓒ MUSIC24 Corp. All Rights Reserved</span>
									</p>
								</td>
							</tr>
						</table>
					</td>
					<td align="right" valign="bottom">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr> 
								<td align="right">
										<a href="${cPath}/"><img src="${cPath}/assets/images/footer_home.gif" border="0"></a>&nbsp
										<a href="javascript:event.preventDefault();$(document).animate('scrollTop', 0);return false;0"><img src="${cPath}/assets/images/footer_top.gif" border="0"></a>
								</td>
							</tr>
							<tr>
								<td>
									<table border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td><A HREF="http://www.ftc.go.kr/" target="_blank"><img src="${cPath}/assets/images/footer_pic1.gif" border="0"></A></td>
											<td><img src="${cPath}/assets/images/footer_line.gif" width="3" height="42"></td>
											<td><A HREF="http://www.sgic.co.kr/" target="_blank"><img src="${cPath}/assets/images/footer_pic2.gif" border="0"></a></td>
										</tr>
									</table>
								</td>
							<tr> 
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>