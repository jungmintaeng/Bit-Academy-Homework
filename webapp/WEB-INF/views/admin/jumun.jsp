<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
	<title>쇼핑몰 관리자 홈페이지</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${cPath}/assets/css/font.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
	<script type="text/javascript" src="${cPath}/assets/js/ejs/ejs.js"></script>
</head>
<script type="text/javascript">
    $(function(){
        $(document).on('click', '.delete-btn', function (event) {
            event.preventDefault();
            var $a = $(this);
            var targetNo = $a.attr("data-no");
            $.ajax({
                url: "${cPath}/admin/order/delete/" + targetNo,
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
        });
		});
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
	<br>
	<jsp:include page="/WEB-INF/views/include/admin-menu.jsp"/>
	<hr width='900' size='3'>

	<table id="order_list" width="900" border="1" cellspacing="0" cellpadding="0" bordercolordark="white" bordercolorlight="black">
		<tr bgcolor="#CCCCCC" height="23">
			<td width="70"  align="center">주문번호</td>
			<td width="70"  align="center">주문일</td>
			<td width="250" align="center">상품명</td>
			<td width="40"  align="center">제품수</td>
			<td width="70"  align="center">총금액</td>
			<td width="65"  align="center">주문자</td>
			<td width="50"  align="center">결제</td>
			<td width="135" align="center">주문상태</td>
			<td width="50"  align="center">삭제</td>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
				<td width="70"  align="center">${item.orderVo.code}</td>
				<td width="70"  align="center">${item.orderVo.orderDate}</td>
				<c:if test="${not empty item.orderedProductList}">
					<td width="250" align="center">${item.orderedProductList.get(0).productName}</td>
				</c:if>
				<c:if test="${empty item.orderedProductList}">
					<td width="250" align="center"></td>
				</c:if>
				<td width="40"  align="center">${fn:length(item.orderedProductList)}</td>
				<td width="70"  align="center">${item.price}</td>
				<td width="65"  align="center">${item.orderVo.oName}</td>
				<td width="50"  align="center">${item.orderVo.paymentMethodName}</td>
				<td width="135" align="center">
					${item.orderVo.state}
				</td>
				<td width="50"  align="center">
					<a href="" data-no="${item.orderVo.no}" class="delete-btn">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
</div>
<%--<table width="800" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" class="cmfont" align="center">
			<img src="${cPath}/assets/images/admin/i_prev.gif" align="absmiddle" border="0"> 
			<font color="#FC0504"><b>1</b></font>&nbsp;
			<a href="jumun.jsp?page=2&sel1=&sel2=&text1=&day1_y=&day1_m=&day1_d=&day2_y=&day2_m=&day2_d="><font color="#7C7A77">[2]</font></a>&nbsp;
			<a href="jumun.jsp?page=3&sel1=&sel2=&text1=&day1_y=&day1_m=&day1_d=&day2_y=&day2_m=&day2_d="><font color="#7C7A77">[3]</font></a>&nbsp;
			<img src="${cPath}/assets/images/admin/i_next.gif" align="absmiddle" border="0">
		</td>
	</tr>
</table>--%>
</body>
</html>