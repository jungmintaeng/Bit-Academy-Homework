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
	<link href="${cPath}/assets/css/cart.css" rel="stylesheet" type="text/css">
	<style>
		@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	</style>
	<script type="text/javascript" src="${cPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body style="margin:0">
<div class="container">
	<jsp:include page="/WEB-INF/views/include/head.jsp"/>
	<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>

	<h1 class="title">결제</h1>

	<table width="959" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr><td height="10" colspan="2"></td></tr>
		<tr>
			<td height="100%" valign="top">

			</td>
			<td width="10"></td>
			<td valign="top">

				<!-------------------------------------------------------------------------------------------->
				<!-- 시작 : 다른 웹페이지 삽입할 부분                                                       -->
				<!-------------------------------------------------------------------------------------------->

				<!--  현재 페이지 자바스크립  -------------------------------------------->
				<script language="javascript">

            function Check_Value()
            {
                if (form2.pay_method[0].checked)
                {
                    if (!form2.card_kind.value) {
                        alert("카드종류를 선택하세요.");	form2.card_kind.focus();	return;
                    }
                    if (!form2.card_no1.value || form2.card_no1.value.length!=4) {
                        alert("카드번호를 입력하세요.");	form2.card_no1.focus();	return;
                    }
                    if (!form2.card_no2.value || form2.card_no2.value.length!=4) {
                        alert("카드번호를 입력하세요.");	form2.card_no2.focus();	return;
                    }
                    if (!form2.card_no3.value || form2.card_no3.value.length!=4) {
                        alert("카드번호를 입력하세요.");	form2.card_no3.focus();	return;
                    }
                    if (!form2.card_no4.value || form2.card_no4.value.length!=4) {
                        alert("카드번호를 입력하세요.");	form2.card_no4.focus();	return;
                    }
                    if (!form2.card_year.value) {
                        alert("카드기간 년도를 선택하세요.");	form2.card_year.focus();	return;
                    }
                    if (!form2.card_month.value) {
                        alert("카드기간 월을 선택하세요.");	form2.card_month.focus();	return;
                    }
                    if (!form2.card_pw.value) {
                        alert("카드 암호 뒷의 2자리를 선택하세요.");	form2.card_pw.focus();	return;
                    }
                }
                else
                {
                    if (!form2.bank_kind.value) {
                        alert("입금할 은행을 선택하세요.");	form2.bank_kind.focus();	return;
                    }
                    if (!form2.bank_sender.value) {
                        alert("입금자 이름을 입력하세요.");	form2.bank_sender.focus();	return;
                    }
                }

                form2.submit();
            }

            function PaySel(n)
            {
                if (n == 0) {
                    form2.card_kind.disabled = false;
                    form2.card_no1.disabled = false;
                    form2.card_no2.disabled = false;
                    form2.card_no3.disabled = false;
                    form2.card_no4.disabled = false;
                    form2.card_year.disabled = false;
                    form2.card_month.disabled = false;
                    form2.card_pw.disabled = false;
                    form2.bank_kind.disabled = true;
                    form2.bank_sender.disabled = true;
                }
                else {
                    form2.card_kind.disabled = true;
                    form2.card_no1.disabled = true;
                    form2.card_no2.disabled = true;
                    form2.card_no3.disabled = true;
                    form2.card_no4.disabled = true;
                    form2.card_year.disabled = true;
                    form2.card_month.disabled = true;
                    form2.card_pw.disabled = true;
                    form2.bank_kind.disabled = false;
                    form2.bank_sender.disabled = false;
                }
            }

				</script>

				<table class="cart-list" border="1px">
					<tr class="table-head">
						<td>상품</td>
						<td>수량</td>
						<td>가격</td>
						<td>합계</td>
					</tr>
					<c:set var="sum" value="0"/>
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

				<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
					<tr height="3" bgcolor="#CCCCCC"><td></td></tr>
				</table>
				<br><br>
				<br><br>

				<!-- form2 시작  -->
				<form name="form2" method="post"action="${cPath}/buy/order/pay">
					<!-- 결재방법 선택 및 입력 -->
					<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
						<tr height="3" bgcolor="#CCCCCC"><td></td></tr>
						<tr height="10"><td></td></tr>
					</table>

					<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
						<tr>
							<td align="left" valign="top" width="150" STYLE="padding-left:45;padding-top:5"><font size=2 color="#B90319"><b>결제 방법</b></font></td>
							<td align="center" width="560">

								<table width="560" border="0" cellpadding="0" cellspacing="0" class="cmfont">
									<tr height="25">
										<td width="150"><b>결재방법 선택</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											<input type="radio" name="pay_method" onclick="javascript:PaySel(0);" checked>카드 &nbsp;
											<input type="radio" name="pay_method" onclick="javascript:PaySel(1);">무통장
										</td>
									</tr>
								</table>

							</td>
						</tr>
						<tr height="10"><td></td></tr>
					</table>

					<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
						<tr height="1" bgcolor="#CCCCCC"><td></td></tr>
						<tr height="10"><td></td></tr>
					</table>
					<!-- 카드 -->
					<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
						<tr>
							<td align="left" valign="top" width="150" STYLE="padding-left:45;padding-top:5"><font size=2 color="#B90319"><b>카드</b></font></td>
							<td align="center" width="560">
								<table width="560" border="0" cellpadding="0" cellspacing="0" class="cmfont">
									<tr height="25">
										<td width="150"><b>카드종류</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											<select name="card_kind" class="cmfont1">
												<option value="국민카드">국민카드</option>
												<option value="신한카드">신한카드</option>
												<option value="우리카드">우리카드</option>
												<option value="하나카드">하나카드</option>
											</select>
										</td>
									</tr>
									<tr height="25">
										<td width="150"><b>카드번호</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											<input type="text" autocomplete="off" name="card_no1" size="4" maxlength="4" value="" class="cmfont1"> -
											<input type="text" autocomplete="off" name="card_no2" size="4" maxlength="4" value="" class="cmfont1"> -
											<input type="text" autocomplete="off" name="card_no3" size="4" maxlength="4" value="" class="cmfont1"> -
											<input type="text" autocomplete="off" name="card_no4" size="4" maxlength="4" value="" class="cmfont1">
										</td>
									</tr>
									<tr height="25">
										<td width="150"><b>카드기간</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											<input type="text" autocomplete="off" name="card_month" size="2" maxlength="2" value="" class="cmfont1"> 월 /
											<input type="text" autocomplete="off" name="card_year"  size="2" maxlength="2" value="" class="cmfont1"> 년
										</td>
									</tr>
									<tr height="25">
										<td width="150"><b>카드비밀번호(뒷2자리)</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											**<input type="password" name="card_pw" size="2" maxlength="2" value="" class="cmfont1">
										</td>
									</tr>
									<tr height="25">
										<td width="150"><b>할부</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											<select name="card_halbu" class="cmfont1">
												<option value="0">일시불</option>
												<option value="3">3 개월</option>
												<option value="6">6 개월</option>
												<option value="9">9 개월</option>
												<option value="12">12 개월</option>
											</select>
										</td>
									</tr>
								</table>

							</td>
						</tr>
						<tr height="10"><td></td></tr>
					</table>

					<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
						<tr height="1" bgcolor="#CCCCCC"><td></td></tr>
						<tr height="10"><td></td></tr>
					</table>
					<!-- 무통장 -->
					<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
						<tr>
							<td align="left" valign="top" width="150" style="padding-left:45;padding-top:5"><font size=2 color="#B90319"><b>무통장 입금</b></font></td>
							<td align="center" width="560">
								<table width="560" border="0" cellpadding="0" cellspacing="0" class="cmfont">
									<tr height="25">
										<td width="150"><b>은행선택</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											<select name="bank_kind"  class="cmfont1" disabled>
												<option value="국민은행 000-00000-0000">국민은행 000-00000-0000</option>
												<option value="신한은행 000-00000-0000">신한은행 000-00000-0000</option>
											</select>
										</td>
									</tr>
									<tr height="25">
										<td width="150"><b>입금자 이름</b></td>
										<td width="20"><b>:</b></td>
										<td width="390">
											<input type="text" autocomplete="off" name="bank_sender" size="15" maxlength="20" value="" class="cmfont1" disabled>
										</td>
									</tr>
								</table>

							</td>
						</tr>
						<tr height="10"><td></td></tr>
					</table>

					<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
						<tr height="3" bgcolor="#CCCCCC"><td></td></tr>
						<tr height="10"><td></td></tr>
					</table>
				</form>

				<table width="710" border="0" cellpadding="0" cellspacing="0" class="cmfont">
					<tr>
						<td align="center">
							<img src="${pageContext.servletContext.contextPath }/assets/images/b_order3.gif" onclick="javascript:form2.submit();" style="cursor:hand">
						</td>
					</tr>
					<tr height="20"><td></td></tr>
				</table>

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