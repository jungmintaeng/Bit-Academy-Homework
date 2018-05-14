<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="cPath" value="${pageContext.servletContext.contextPath}"/>
<c:set var="orderProductList" value="${orderProductList}" scope="session"/>
<c:set var="sum" value="0"/>
<html>
<head>
	<title>비트닷컴 쇼핑몰</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
  <script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
  <link href="${cPath}/assets/css/common.css" rel="stylesheet" type="text/css">
  <link href="${cPath}/assets/css/cart.css" rel="stylesheet" type="text/css">
  <style>
    @import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
  </style>
</head>
<script language="javascript">

    function Check_Value() {
        if (!form2.o_name.value) {
            alert("주문자 이름이 잘 못 되었습니다.");	form2.oName.focus();	return false;
        }
        if (!form2.oTelNumber.value) {
            alert("전화번호가 잘 못 되었습니다.");	form2.oTelNumber.focus();	return false;
        }
        if (!form2.oPhoneNumber.value) {
            alert("핸드폰이 잘 못 되었습니다.");	form2.oPhoneNumber.focus();	return false;
        }
        if (!form2.oEmail.value) {
            alert("이메일이 잘 못 되었습니다.");	form2.oEmail.focus();	return false;
        }
        if (!form2.oZipcode.value) {
            alert("우편번호가 잘 못 되었습니다.");	form2.oZipcode.focus();	return false;
        }
        if (!form2.oAddress.value) {
            alert("주소가 잘 못 되었습니다.");	form2.oAddress.focus();	return false;
        }

        if (!form2.rName.value) {
            alert("받으실 분의 이름이 잘 못 되었습니다.");	form2.rName.focus();	return false;
        }
        if (!form2.rTelNumber.value) {
            alert("전화번호가 잘 못 되었습니다.");	form2.rTelNumber.focus();	return false;
        }
        if (!form2.rPhoneNumber.value) {
            alert("핸드폰이 잘 못 되었습니다.");	form2.rPhoneNumber.focus();	return false;
        }
        if (!form2.rEmail.value) {
            alert("이메일이 잘 못 되었습니다.");	form2.rEmail.focus();	return false;
        }
        if (!form2.rZipcode.value) {
            alert("우편번호가 잘 못 되었습니다.");	form2.rZipcode.focus();	return false;
        }
        if (!form2.rAddress.value) {
            alert("주소가 잘 못 되었습니다.");	form2.rAddress.focus();	return false;
        }

        return true;
    }

    function SameCopy(str) {
        if (str == "Y") {
            form2.rName.value = form2.oName.value;
            form2.rZipcode.value = form2.oZipcode.value;
            form2.rAddress.value = form2.oAddress.value;
            form2.rTelNumber.value = form2.oTelNumber.value;
            form2.rPhoneNumber.value = form2.oPhoneNumber.value;
            form2.rEmail.value = form2.oEmail.value;
        }
        else {
            form2.rName.value = "";
            form2.rZipcode.value = "";
            form2.rAddress.value = "";
            form2.rTelNumber.value = "";
            form2.rPhoneNumber.value = "";
            form2.rEmail.value = "";
        }
    }

</script>
<body style="margin:0">
<div class="container">
  <jsp:include page="/WEB-INF/views/include/head.jsp"/>
  <jsp:include page="/WEB-INF/views/include/navigation.jsp"/>

  <table width="959" border="0" cellspacing="0" cellpadding="0">
    <tr><td height="10" colspan="2"></td></tr>
    <tr>
      <td height="100%" valign="top">

      </td>
      <td width="10"></td>
      <td valign="top">

        <!-------------------------------------------------------------------------------------------->
        <!-- 시작 : 다른 웹페이지 삽입할 부분                                                       -->
        <!-------------------------------------------------------------------------------------------->

        <table class="cart-list" border="1px">
          <tr class="table-head">
            <td>상품</td>
            <td>수량</td>
            <td>가격</td>
            <td>합계</td>
          </tr>
          <c:forEach items="${orderProductList}" var="item">
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
                <input type="number" value="${item.cartVo.quantity}" min="1" readonly>
              </td>
              <td class="price">
                <fmt:formatNumber pattern="#,###" value="${item.cartVo.price * (100-item.cartVo.discountRate) / 100}"/>원
              </td>
              <td class="total-price">
                <fmt:formatNumber pattern="#,###"
                                  value="${item.cartVo.price * (100-item.cartVo.discountRate) / 100 * item.cartVo.quantity}"/>원
              </td>
            </tr>
            <c:set var="sum" value="${sum + item.cartVo.price * (100-item.cartVo.discountRate) / 100 * item.cartVo.quantity}"/>
          </c:forEach>
        </table><br><br><p style="float:right;">주문 금액 : <fmt:formatNumber pattern="#,###"
                                                  value="${sum}"/>원</p>

        <!-- form2 시작  -->
        <form name="form2" method="post" action="${cPath}/buy/order" onsubmit="return Check_Value()">
          <input type="hidden" name="oNo" value="${authUser.no}">
          <table width="710" border="0" cellpadding="0" cellspacing="0" style="margin:0 50px 0 250px;">
            <tr>
              <td align="left" valign="top" width="150" STYLE="padding-left:45;padding-top:5">
                <font size="2" color="#B90319"><b>주문자 정보</b></font>
              </td>
              <td align="center" width="560">

                <table width="560" border="0" cellpadding="0" cellspacing="0">
                  <tr height="25">
                    <td width="150"><b>주문자 성명</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="oName" size="20" maxlength="10" value="${authUser.name}" required>
                    </td>
                  </tr>
                  <tr height="25">
                    <td width="150"><b>전화번호</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="oTelNumber" value="" required>
                    </td>
                  </tr>
                  <tr height="25">
                    <td width="150"><b>휴대폰번호</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="oPhoneNumber" value="" required>
                    </td>
                  </tr>
                  <tr height="25">
                    <td width="150"><b>E-Mail</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="oEmail" size="50" maxlength="50" value="" required>
                    </td>
                  </tr>
                  <tr height="50">
                    <td width="150"><b>주소</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="oZipcode" value="" required>
                      <input type="text" autocomplete="off" name="oAddress" size="50" maxlength="200" value="" required><br>
                    </td>
                  </tr>
                </table>

              </td>
            </tr>
            <tr height="10"><td></td></tr>
          </table>

          <!-- 배송지 정보 -->
          <table width="710" border="0" cellpadding="0" cellspacing="0" style="margin:0 50px 0 250px;">
            <tr height="3" bgcolor="#CCCCCC"><td></td></tr>
            <tr height="10"><td></td></tr>
          </table>

          <table width="710" border="0" cellpadding="0" cellspacing="0" style="margin:0 50px 0 250px;">
            <tr>
              <td align="left" valign="top" width="150" STYLE="padding-left:45;padding-top:5"><font size=2 color="#B90319"><b>배송지 정보</b></font></td>
              <td align="center" width="560">

                <table width="560" border="0" cellpadding="0" cellspacing="0">
                  <tr height="25">
                    <td width="150"><b>주문자정보와 동일</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="radio" name="same" onclick="SameCopy('Y')">예 &nbsp;
                      <input type="radio" name="same" onclick="SameCopy('N')">아니오
                    </td>
                  </tr>
                  <tr height="25">
                    <td width="150"><b>받으실 분 성명</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="rName" size="20" maxlength="10" value="" required>
                    </td>
                  </tr>
                  <tr height="25">
                    <td width="150"><b>전화번호</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="rTelNumber" value="" required>
                    </td>
                  </tr>
                  <tr height="25">
                    <td width="150"><b>휴대폰번호</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="rPhoneNumber" value="" required>
                    </td>
                  </tr>
                  <tr height="25">
                    <td width="150"><b>E-Mail</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="rEmail" size="50" maxlength="50" value="" required>
                    </td>
                  </tr>
                  <tr height="50">
                    <td width="150"><b>주소</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <input type="text" autocomplete="off" name="rZipcode" value="" required>
                      <input type="text" autocomplete="off" name="rAddress" size="50" maxlength="200" value="" required><br>
                    </td>
                  </tr>
                  <tr height="50">
                    <td width="150"><b>배송시요구사항</b></td>
                    <td width="20"><b>:</b></td>
                    <td width="390">
                      <textarea name="requirement" cols="60" rows="3" required></textarea>
                    </td>
                  </tr>
                </table>

              </td>
            </tr>
            <tr height="10"><td></td></tr>
          </table>

          <table width="710" border="0" cellpadding="0" cellspacing="0" style="margin:0 50px 0 250px;">
            <tr height="3" bgcolor="#CCCCCC"><td></td></tr>
            <tr height="10"><td></td></tr>
          </table>

          <table width="710" border="0" cellpadding="0" cellspacing="0" style="margin:0 50px 0 250px;">
            <tr>
              <td align="center">
                <input type="image" src="${cPath}/assets/images/b_order3.gif">
              </td>
            </tr>
            <tr height="20"><td></td></tr>
          </table>

        </form>

        <!-------------------------------------------------------------------------------------------->
        <!-- 끝 : 다른 웹페이지 삽입할 부분                                                         -->
        <!-------------------------------------------------------------------------------------------->

      </td>
    </tr>
  </table>
  <br><br>

  <jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</div>
</body>
</html>