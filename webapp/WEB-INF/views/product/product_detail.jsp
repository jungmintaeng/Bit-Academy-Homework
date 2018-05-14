<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<% pageContext.setAttribute("newLine", "\n"); %>
<html>
<head>
  <title>비트닷컴 쇼핑몰</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${cPath}/assets/css/common.css" rel="stylesheet" type="text/css">
  <link href="${cPath}/assets/css/product-detail.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
  <style>
    @import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
  </style>
</head>
<script>
    $(function () {
        $(document).on('click', '.go-cart > a', function () {
            event.preventDefault();
            var queryParameter = $('#order-form').serialize();
            $.ajax({
                url: "${cPath}/buy/cart/add",
                type: "get",
                dataType: "json",
                data: queryParameter,
                success: function (response) {
                    if (parseInt(response.data) > 0) {
                        alert("장바구니에 추가되었습니다.");
                    }
                },
                error: function (xhr, status, e) {
                    console.log("err");
                    console.error(e);
                }
            });
            return false;
        });

        $(document).on('click', '.immediate-purchase > a', function () {
            event.preventDefault();
            var queryParameter = $('#order-form').serialize();
            location.href="${cPath}/buy/order?" + queryParameter;
            return false;
        });
    });
</script>
<body style="margin:0">
<div class="container">
  <jsp:include page="/WEB-INF/views/include/head.jsp"/>
  <jsp:include page="/WEB-INF/views/include/navigation.jsp"/>

  <h1 class="title">${product.productVo.name}</h1>
  <form id="order-form" action="${cPath}/buy/order" method="post">
    <div class="content">
      <div class="left">
        <c:forEach items="${product.imageVos}" var="img">
          <c:if test="${img.orderNo == 0}">
            <img src="${cPath}/uploads/${img.saveName}" alt="로고">
          </c:if>
        </c:forEach>
      </div>
      <div class="right">
        <table class="info">
          <tr>
            <td class="column">
              가격 :
            </td>
            <td class="column">
              <input type="hidden" name="p" value="${product.productVo.no}">
              <c:if test="${empty product.productVo.discountRate || product.productVo.discountRate <= 0}">
              <span class="price-original">
              <fmt:formatNumber value="${product.productVo.price}" pattern="#,###"/>원</span>
              </c:if>
              <c:if test="${product.productVo.discountRate > 0}">
              <span class="discount-price"><del>
                <fmt:formatNumber value="${product.productVo.price}" pattern="#,###"/>원</del></span>&nbsp;&nbsp;
                <span class="price-sale">
                <fmt:formatNumber value="${(1-product.productVo.discountRate / 100) * product.productVo.price}"
                                  pattern="#,###"/>원
                &nbsp;
                <span class="discount-rate">${product.productVo.discountRate}%
                  <span class="bold">↓</span>
                </span>
              </span>
              </c:if>
            </td>
          </tr>
          <tr>
            <td class="column">
              수량 :
            </td>
            <td class="column">
              <input name="q" type="number" value="1" min="1">
            </td>
          </tr>
          <tr>
            <td class="column">
              옵션 :
            </td>
            <td class="column">
              <c:if test="${empty optionList}">
                없음
              </c:if>
              <c:if test="${not empty optionList}">
                <c:forEach items="${optionList}" var="parentOption">
                  <select name="o">
                    <c:forEach items="${parentOption.optionDtoList}" var="childOption">
                      <option value="${childOption.no}">${childOption.name}</option>
                    </c:forEach>
                  </select>
                </c:forEach>
              </c:if>
            </td>
          </tr>
          <tr>
            <td class="immediate-purchase">
              <br><br><br>
              <a href="">바로 구매하기</a>
            </td>
            <td class="go-cart">
              <br><br><br>
              <a href="">장바구니 담기</a>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </form>

  <div class="product-detail">
    <div class="line"></div>
    <br><br>
    <c:forEach items="${product.imageVos}" var="img">
      <c:if test="${img.orderNo == 1}">
        <img src="${cPath}/uploads/${img.saveName}" alt="디테일 이미지">
        <br><br><br><br>
      </c:if>
    </c:forEach>
    <c:if test="${not empty product.productVo.description}">
      <h1 class="title text-left">상세 설명</h1>
      <p class="product-description">
          ${fn:replace(product.productVo.description, newLine , "<br>")}
      </p>
    </c:if>
    <c:forEach items="${product.imageVos}" var="img">
      <c:if test="${img.orderNo == 2}">
        <br><br><br>
        <img src="${cPath}/uploads/${img.saveName}" alt="디스크 정보">
      </c:if>
    </c:forEach>
  </div>
  <br><br><br><br><br><br>
  <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>