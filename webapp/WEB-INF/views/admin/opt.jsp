<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<c:set var="cnt" value="${fn:length(list)}"/>
<html>
<head>
	<title>쇼핑몰 관리자 홈페이지</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.servletContext.contextPath }/assets/css/font.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<script>
	var deleteOption = function(){
      event.preventDefault();
      var $a = $(this);
	    var targetNo = $a.attr("data-no");
      $.ajax({
          url : "${cPath}/admin/option/delete/" + targetNo,
          type : "get",
          dataType : "json",
          data : "",
          success : function(response) {
              if(parseInt(response.data) > 0){
                  $a.parent().parent().remove();
              }
          },
          error : function(xhr, status, e) {
              console.log("err");
              console.error(e);
          }
      });
      return false;
	}

	$(function(){
	    $(document).on('click', '.deleteBtn', deleteOption);
	});
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
	<br>
	<c:import url="/WEB-INF/views/include/admin-menu.jsp"/>
	<hr width='900' size='3'>
	<table width="900" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left"  width="250" height="50" valign="bottom">&nbsp 옵션수 : <font color="#FF0000">${cnt}</font></td>
			<td align="right" width="200" height="50" valign="bottom">
				<a href="${cPath}/admin/option/new"><input type="button" value="신규입력"></a> &nbsp
			</td>
		</tr>
		<tr><td height="5" colspan="2"></td></tr>
	</table>

	<table width="900" border="1" cellspacing="0" cellpadding="4"  bordercolordark="white" bordercolorlight="black">
		<tr bgcolor="#CCCCCC" height="20">
			<td width="50"  align="center"><font color="#142712">번호</font></td>
			<td width="200" align="center"><font color="#142712">옵션명</font></td>
			<td width="100" align="center"><font color="#142712">수정/삭제</font></td>
			<td width="100" align="center"><font color="#142712">소옵션편집</font></td>
		</tr>
		<c:forEach var="item" items="${list}" varStatus="status">
			<tr bgcolor="#F2F2F2" height="20">
				<td width="50"  align="center">${status.index + 1}</td>
				<td width="200" align="left">${item.name}</td>
				<td width="100" align="center">
					<a href="${cPath}/admin/option/edit/${item.no}">수정</a>/
					<a class="deleteBtn" data-no="${item.no}" href="">삭제</a>
				</td>
				<td width="100" align="center"><a href="${cPath}/admin/option/small/list/${item.no}">소옵션편집</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
</div>
</body>
</html>