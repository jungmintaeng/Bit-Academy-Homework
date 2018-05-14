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
	<link href="${cPath}/assets/css/common.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<script>
    var deleteProduct = function (event) {
        event.preventDefault();
        var $a = $(this);
        var targetNo = $a.attr("data-no");
        $.ajax({
            url: "${cPath}/admin/product/delete/" + targetNo,
            type: "get",
            dataType: "json",
            data: "",
            success: function (response) {
                if (parseInt(response.data) > 0) {
                    $a.parent().parent().remove();
                }
            },
            error: function (xhr, status, e) {
                console.log("err");
                console.error(e);
            }
        });
        return false;
    }

    $(function () {
        $(document).on('click', '.deleteBtn', deleteProduct);
    });
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
	<br>
	<jsp:include page="/WEB-INF/views/include/admin-menu.jsp"/>
	<hr width='900' size='3'>
	<form name="form1" method="get" action="">
		<table width="900" border="0" cellspacing="0" cellpadding="0">
			<tr height="40">
				<td align="left"  width="150" valign="bottom">&nbsp 제품수 : <font color="#FF0000">${fn:length(list)}</font></td>
				<td align="right" width="550" valign="bottom">
					<select name="sel1">
						<option value="0" >상품상태</option>
						<option value="1" >판매중</option>
						<option value="2" >판매중지</option>
						<option value="3" >품절</option>
					</select> &nbsp
					<select name="sel2">
						<option value="0" >아이콘선택</option>
						<option value="1" >New</option>
						<option value="2" >Hit</option>
						<option value="3" >Sale</option>
					</select> &nbsp
					<select name="sel3">
						<option value="0" >분류선택</option>
						<option value="1" >바지</option>
						<option value="2" >코트</option>
						<option value="3" >브라우스</option>
					</select> &nbsp
					<select name="sel4">
						<option value="1" selected>제품이름</option>
						<option value="2" >제품번호</option>
					</select>
					<input type="text" autocomplete="off" name="text1" size="10" value="">&nbsp
				</td>
				<td align="left" width="120" valign="bottom">
					<input type="submit" value="검색">
					&nbsp;
					<a href="${cPath}/admin/product/new"><input type="button" value="새상품"></a>
				</td>
			</tr>
			<tr><td height="5"></td></tr>
		</table>
	</form>

	<table width="900" border="1" cellspacing="0" bordercolordark="white" bordercolorlight="black">
		<tr bgcolor="#CCCCCC" height="23">
			<td width="100" align="center">제품분류</td>
			<td width="100" align="center">제품코드</td>
			<td width="280" align="center">제품명</td>
			<td width="70"  align="center">판매가</td>
			<td width="50"  align="center">상태</td>
			<td width="120" align="center">이벤트</td>
			<td width="80"  align="center">수정/삭제</td>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr bgcolor="#F2F2F2" height="23">
				<td width="100">&nbsp ${item.categoryName}</td>
				<td width="100">&nbsp ${item.code}</td>
				<td width="280">${item.name}</td>
				<td width="70"  align="right">${item.price} &nbsp</td>
				<td width="50"  align="center">${item.state}</td>
				<td width="120" align="center">&nbsp
					<c:if test="${item.new_}">New</c:if>
					<c:if test="${item.hit_}">Hit</c:if>
					<c:if test="${item.discountRate > 0}">Sale(${discountRate * 100}%)</c:if> </td>
				<td width="80"  align="center">
					<a href="${cPath}/admin/product/edit/${item.no}">수정</a>/
					<a data-no="${item.no}" class="deleteBtn" href="">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>