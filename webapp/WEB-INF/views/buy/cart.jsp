<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
  <title>비트닷컴 쇼핑몰</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link href="${cPath}/assets/css/common.css" rel="stylesheet" type="text/css">
  <link href="${cPath}/assets/css/cart.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
  <style>
    @import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
  </style>
</head>
<script>
    $(function () {
        $(document).on('click', '.edit-button', function () {
            var $tr = $(this).parent().parent();
            event.preventDefault();
            var q = $tr.find('input[type="number"]').val();
            if (q < 1)
                return false;
            var queryParameter = "c=" + $tr.attr('data-no') +
                "&q=" + q;
            $.ajax({
                url: "${cPath}/buy/cart/modify",
                type: "get",
                dataType: "json",
                data: queryParameter,
                success: function (response) {
                    if (parseInt(response.data) > 0) {
                        var price = $tr.find('.price').text();
                        price = price.substring(0, price.length - 1).replace(",", "");
                        $tr.find('.total-price').text(q * parseInt(price) + "원");
                        alert("수정되었습니다.");
                    }
                },
                error: function (xhr, status, e) {
                    console.log("err");
                    console.error(e);
                }
            });
            return false;
        });

        $(document).on('click', '.delete-button', function () {
            var $tr = $(this).parent().parent();
            event.preventDefault();
            var queryParameter = "no=" + $tr.attr('data-no');
            $.ajax({
                url: "${cPath}/buy/cart/delete",
                type: "get",
                dataType: "json",
                data: queryParameter,
                success: function (response) {
                    if (parseInt(response.data) > 0) {
                        $tr.remove();
                    }
                },
                error: function (xhr, status, e) {
                    console.log("err");
                    console.error(e);
                }
            });
            return false;
        });

        $(document).on('click', '#delete-all-button', function () {
            $.ajax({
                url: "${cPath}/buy/cart/delete/all",
                type: "get",
                dataType: "json",
                data: "",
                success: function (response) {
                    if (parseInt(response.data) > 0) {
                        $('.deletable').remove();
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
<body style="margin:0">
<div class="container">
  <jsp:include page="/WEB-INF/views/include/head.jsp"/>
  <jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
  <br><br>
  <h1 class="title">장바구니</h1>
  <table class="cart-list" border="1px">
    <tr class="table-head">
      <td>상품</td>
      <td>수량</td>
      <td>가격</td>
      <td>합계</td>
      <td>삭제</td>
    </tr>
    <c:forEach items="${cartList}" var="item">
      <tr data-no="${item.cartVo.no}" class="deletable">
        <td>
          <a href="${cPath}/product/detail/${item.cartVo.productNo}">
            <img src="${cPath}/uploads/${item.cartVo.saveName}">
            <div class="info-container">
              <span class="product-name">${item.cartVo.productName}</span>
              <c:if test="${not empty item.optionList}">
                <br>
                <span class="option-list">[ 옵션 ]
						<c:forEach items="${item.optionList}" var="option">
              ${option.name}
            </c:forEach>
            </span>
              </c:if>
            </div>
          </a>
        </td>
        <td>
          <input type="number" value="${item.cartVo.quantity}" min="1">
        </td>
        <td class="price">
          <fmt:formatNumber pattern="#,###" value="${item.cartVo.price * (100-item.cartVo.discountRate) / 100}"/>원
        </td>
        <td class="total-price">
          <fmt:formatNumber pattern="#,###"
                            value="${item.cartVo.price * (100-item.cartVo.discountRate) / 100 * item.cartVo.quantity}"/>원
        </td>
        <td>
          <a href="#" class="edit-button">수정하기</a><br><br>
          <a href="#" class="delete-button">삭제하기</a>
        </td>
      </tr>
    </c:forEach>
  </table>

  <a href="${cPath}/buy/order?p=0&o=0&q=0" class="right no-drag">주문하기</a>
  <a href="${cPath}/" class="right no-drag">쇼핑 계속 하기</a>
  <a href="" id="delete-all-button" class="right no-drag">장바구니 비우기</a>

  <br><br><br>
  <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>