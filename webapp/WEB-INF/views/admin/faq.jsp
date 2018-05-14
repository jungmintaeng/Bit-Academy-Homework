<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<c:set var="cnt" value="${fn:length(list)} "/>
<html>
<head>
  <title>쇼핑몰 관리자 홈페이지</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${cPath}/assets/css/font.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
  <script type="text/javascript" src="${cPath}/assets/js/ejs/ejs.js"></script>
</head>
<script>
    var renderItem = new EJS({
        url: "${cPath}/assets/js/ejs/template/faq_admin_list.ejs"
    });

    var render = function (vo) {
        vo.editURL = "${cPath}/admin/faq/edit/" + vo.no;
        vo.deleteURL = "${cPath}/admin/faq/delete/" + vo.no;
        var rendered = renderItem.render(vo);
        $("#faq_list").append(rendered);
    }

    $(function () {
        var vo = {};
        <c:forEach var="item" items="${list}" varStatus="status">
        vo.no = ${item.no};
        vo.index = parseInt("${cnt}") - parseInt("${status.index}");
        vo.title = "${item.title}";
        vo.content = "${item.content}";
        render(vo);
        </c:forEach>
    });
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
  <br>
  <c:import url="/WEB-INF/views/include/admin-menu.jsp"/>
  <hr width='900' size='3'>
  <table width="900" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="left" height="50" valign="bottom">&nbsp 자료수 : <font color="#FF0000">${cnt}</font></td>
      <td align="right" height="50" valign="bottom">
        <a href="${cPath}/admin/faq/new"><input type="button" value="신규입력"></a> &nbsp
      </td>
    </tr>
    <tr>
      <td height="5" colspan="2"></td>
    </tr>
  </table>

  <table id="faq_list" width="900" border="1" cellspacing="0" cellpadding="4" bordercolordark="white"
         bordercolorlight="black">
    <tr bgcolor="#CCCCCC" height="20">
      <td width="50" align="center"><font color="#142712">번호</font></td>
      <td width="450" align="center"><font color="#142712">제목</font></td>
      <td width="100" align="center"><font color="#142712">수정/삭제</font></td>
    </tr>
  </table>
  <br>
</div>
</body>
</html>