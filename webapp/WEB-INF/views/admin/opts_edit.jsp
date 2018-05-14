<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
	<title>쇼핑몰 관리자 홈페이지</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.servletContext.contextPath }/assets/css/font.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<script>
    var checkVal = function(){
        var $element = $('input[type=text], textarea');
        for(var ele in $element){
            if($(ele).val() == ""){
                alert("모든 입력이 이루어지지 않았습니다.");
                return false;
            }
        }

        return true;
    };
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
	<br>
	<jsp:include page="/WEB-INF/views/include/admin-menu.jsp"/>
	<hr width='900' size='3'>
	<form name="form1" method="post" action="${cPath}/admin/option/small/edit" onsubmit="return checkVal()">
		<input type="hidden" name="parentNo" value="${option.parentNo}">
		<input type="hidden" name="no" value="${option.no}">

		<table width="900" border="1" cellspacing="0" bordercolordark="white" bordercolorlight="black">
			<tr>
				<td width="100" height="20" bgcolor="#CCCCCC" align="center">
					<font color="#142712">소옵션번호</font>
				</td>
				<td width="400" height="20"  bgcolor="#F2F2F2">${option.no}</td>
			</tr>
			<tr>
				<td width="100" height="20" bgcolor="#CCCCCC" align="center">
					<font color="#142712">소옵션명</font>
				</td>
				<td width="400" height="20"  bgcolor="#F2F2F2">
					<input type="text" autocomplete="off" name="name" value="${option.name}" size="20" maxlength="20">
				</td>
			</tr>
		</table>
		<br>
		<table width="900" border="0" cellspacing="0" cellpadding="7">
			<tr>
				<td align="center">
					<input type="submit" value="수 정 하 기"> &nbsp;&nbsp
					<a href="${cPath}/admin/option/small/list/${option.parentNo}"><input type="button" value="이 전 화 면"></a>
				</td>
			</tr>
		</table>

	</form>
</div>
</body>
</html>
