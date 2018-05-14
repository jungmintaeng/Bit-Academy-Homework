<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
  <title>쇼핑몰 관리자 홈페이지</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${cPath }/assets/css/font.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<script>
    var deleteUser = function (event) {
        event.preventDefault();
        var $a = $(this);
        var targetNo = $a.attr("data-no");
        $.ajax({
            url: "${cPath}/admin/member/delete/" + targetNo,
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
        $(document).on('click', '.deleteBtn', deleteUser);
    });
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
  <input type="hidden" name="no" value="${authUser.no}">
  <br>
  <jsp:include page="/WEB-INF/views/include/admin-menu.jsp"/>
  <hr width='900' size='3'>
  회원수 : ${fn:length(list)}
  <table width="900" border="1" cellspacing="0" bordercolordark="white" bordercolorlight="black">
    <tr bgcolor="#CCCCCC" height="23">
      <td width="100" align="center">ID</td>
      <td width="100" align="center">이름</td>
      <td width="100" align="center">전화</td>
      <td width="100" align="center">핸드폰</td>
      <td width="200" align="center">E-Mail</td>
      <td width="100" align="center">회원구분</td>
      <td width="100" align="center">수정/삭제</td>
    </tr>

    <c:forEach var="item" items="${list }" varStatus="status">

      <tr bgcolor="#F2F2F2" height="23">
        <td width="100">&nbsp ${item.id }</td>
        <td width="100">&nbsp ${item.name }</td>
        <td width="100">&nbsp ${item.telNumber }</td>
        <td width="100">&nbsp ${item.phoneNumber }</td>
        <td width="200">&nbsp ${item.email }</td>
        <td width="100" align="center">${item.role }</td>
        <td width="100" align="center">
          <c:if test="${item.role != 'admin'}">
            <a href="${cPath}/admin/member/edit/${item.no}">수정</a>/
            <a class="deleteBtn" data-no="${item.no}" href="">삭제</a>
          </c:if>
          <c:if test="${item.role == 'admin'}">
            불가
          </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>