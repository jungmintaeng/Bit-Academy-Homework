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
	<link href="${cPath}/assets/css/product-list.css" rel="stylesheet" type="text/css">
	<link href="${cPath}/assets/css/product.css" rel="stylesheet" type="text/css">
	<style>
		@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	</style>
</head>
<body style="margin:0">
<div class="container">
	<jsp:include page="/WEB-INF/views/include/head.jsp"/>
	<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>

	<div class="category-product">
		<h1 class="title">${category.name}</h1>
		<p class="category-description">${category.description}</p>
		<ul class="product-list">
			<c:forEach items="${productList}" var="item">
				<li>
					<a href="${cPath}/product/detail/${item.productVo.no}">
						<c:forEach items="${item.imageVos}" var="img">
							<c:if test="${img.orderNo == 0}">
								<div class="cd-case-container">
									<span></span>
									<img src="${cPath}/uploads/${img.saveName}"><br>
								</div>
								<br>
							</c:if>
						</c:forEach>
						<span class="product-name">${item.productVo.name}</span>
					</a>
					<br>
					<c:if test="${empty item.productVo.discountRate || item.productVo.discountRate <= 0}">
						<br><span class="price-original">
            <fmt:formatNumber value="${item.productVo.price}" pattern="#,###"/>원</span>
					</c:if>
					<c:if test="${item.productVo.discountRate > 0}">
						<br><span class="discount-price"><del>
            <fmt:formatNumber value="${item.productVo.price}" pattern="#,###"/>원</del></span>
						<br>
						<span class="price-sale">
                <fmt:formatNumber value="${(1-item.productVo.discountRate / 100) * item.productVo.price}"
																	pattern="#,###"/>원
            &nbsp;&nbsp;
            <span class="discount-rate">${item.productVo.discountRate}% <span class="bold">↓</span></span>
            </span>
					</c:if>
					<br>
					<c:if test="${item.productVo.new_}">
						<img src="${cPath}/assets/images/i_new.gif" align="absmiddle" vspace="1">
					</c:if>
					<br>
					<c:if test="${item.productVo.hit_}">
						<img src="${cPath}/assets/images/i_hit.gif" align="absmiddle" vspace="1">
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>