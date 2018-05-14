<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
  <title>쇼핑몰 관리자 홈페이지</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${cPath}/assets/css/font.css" rel="stylesheet" type="text/css">
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

  <form name="form1" method="post" action="${cPath}/admin/faq/edit" onsubmit="return checkVal()">

    <table width="900" border="1" cellspacing="0" bordercolordark="white" bordercolorlight="black">
      <input type="hidden" name="no" value="${faq.no}">
      <tr>
        <td width="100" height="20" bgcolor="#CCCCCC" align="center">
          <font color="#142712">글제목</font>
        </td>
        <td width="500" height="20" bgcolor="#F2F2F2">
          &nbsp <input type="text" autocomplete="off" name="title" value="${faq.title}" size="67">
        </td>
      </tr>
      <tr>
        <td width="100" height="20" bgcolor="#CCCCCC" align="center">
          <font color="#142712">글내용</font>
        </td>
        <td width="500" height="20" bgcolor="#F2F2F2">
          &nbsp <textarea name="content" rows="7" cols="65">${faq.content}</textarea>
        </td>
      </tr>
    </table>
    <br>
    <table width="900" border="0" cellspacing="0" cellpadding="7">
      <tr>
        <td align="center">
          <input type="submit" value="등 록 하 기"> &nbsp;&nbsp;
          <a href="${cPath}/admin/faq/list"><input type="button" value="이 전 화 면"></a>
        </td>
      </tr>
    </table>

  </form>
</div>
</body>
</html>
