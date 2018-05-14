<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>쇼핑몰 관리자 홈페이지</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${pageContext.servletContext.contextPath }/assets/css/font.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container" style="margin: 0 auto; width:900px;text-align: center;">
  <jsp:include page="/WEB-INF/views/include/admin-menu.jsp"/>
  <hr width='900' size='3'>
  <h1 width="100%">안녕하세요 관리자님 !</h1>
</div>
</body>
</html>
